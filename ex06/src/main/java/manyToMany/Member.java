package manyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToManyMember")
public class Member {
    @Id @Column(name = "memberId")
    private String id;
    private String name;

    @ManyToMany
    @JoinTable(name = "memberProduct", joinColumns = @JoinColumn(name = "memberId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        if(products.contains(product) == false){
            products.add(product);
            product.getMembers().add(this);
        }
    }
}
