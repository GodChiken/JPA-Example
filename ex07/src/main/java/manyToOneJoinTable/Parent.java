package manyToOneJoinTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToOneJoinTableParent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Child> children = new ArrayList<>();
}
