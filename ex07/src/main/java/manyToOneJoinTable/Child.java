package manyToOneJoinTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToOneJoinTableChild")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(optional = false)
    @JoinTable(name = "manyToOneJoinTableParentChild",
            joinColumns = @JoinColumn(name = "childId"),
            inverseJoinColumns = @JoinColumn(name = "parentId"))
    private Parent parent;
}
