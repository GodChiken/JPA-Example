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
@ToString(exclude = "members")
@Entity(name = "jpqlTestTeam")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
