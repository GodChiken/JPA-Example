package manyToManyJoinTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Kim Donghoon on 2015-11-24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToManyJoinTableChild")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
