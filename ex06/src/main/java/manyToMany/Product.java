package manyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToManyProduct")
public class Product {
    @Id
    private String id;
    private String name;
}
