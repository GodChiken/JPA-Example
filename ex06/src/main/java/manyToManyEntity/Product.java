package manyToManyEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToManyEntityProduct")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private long id;
    private String name;
}
