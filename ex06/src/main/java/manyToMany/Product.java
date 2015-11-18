package manyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToManyProduct")
public class Product {
    @Id @Column(name = "productId")
    private String id;
    private String name;
    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();
}
