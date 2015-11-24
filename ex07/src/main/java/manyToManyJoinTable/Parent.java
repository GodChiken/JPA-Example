package manyToManyJoinTable;

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
@Entity(name = "manyToManyJoinTableParent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "manyToManyJoinTableParentChild",
            joinColumns = @JoinColumn(name = "parentId"),
            inverseJoinColumns = @JoinColumn(name = "childId"))
    private List<Child> children = new ArrayList<>();
}
