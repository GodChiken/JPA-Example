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
@SqlResultSetMapping(name = "memberWithOrderCount",
        entities = {@EntityResult(entityClass = Member.class)},
        columns = {@ColumnResult(name = "order_count"),
                @ColumnResult(name = "price_sum")})
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
