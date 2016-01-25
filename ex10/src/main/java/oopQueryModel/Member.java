package oopQueryModel;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-12-04.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "orders")
@Entity(name = "jpqlTestMember")
@NamedQueries({
        @NamedQuery(name = "Member.findByName", query = "select m from jpqlTestMember m where m.name = :name"),
        @NamedQuery(name = "Member.count", query = "select count(m) from jpqlTestMember m")
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Member.memberSQL",
                query = "select id, age, name, team_id from jpql_test_member where age > ?",
                resultClass = Member.class
        ),
        @NamedNativeQuery(
                name = "Member.memberWithOrderCount",
                query = "select jpql_test_member.*, sum(price) price_sum, count(0) order_count from jpql_test_member " +
                        "left join jpql_test_order on jpql_test_member.id = jpql_test_order.member_id " +
                        "left join jpql_test_product on jpql_test_order.product_id = jpql_test_product.id " +
                        "group by jpql_test_member.id",
                resultSetMapping = "memberWithOrderCount"
        )
})
@SqlResultSetMapping(name = "memberWithOrderCount",
        entities = {@EntityResult(entityClass = Member.class)},
        columns = {@ColumnResult(name = "order_count"),
                @ColumnResult(name = "price_sum")})
@NamedStoredProcedureQuery(
        name = "multiply",
        procedureName = "proc_multiply",
        parameters = {
                @StoredProcedureParameter(name = "inParam", mode = ParameterMode.INOUT, type = Integer.class),
                @StoredProcedureParameter(name = "outParam", mode = ParameterMode.INOUT, type = Integer.class)
        }
)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
