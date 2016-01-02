package oopQueryModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Kim Donghoon on 2015-12-04.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "jpqlTestProduct")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int price;
    private int stockAmount;
}
