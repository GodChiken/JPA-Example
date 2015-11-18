package manyToManyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToManyEntityMember")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts;
}
