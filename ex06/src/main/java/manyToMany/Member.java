package manyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manyToManyMember")
public class Member {
    @Id
    private String id;
    private String name;

    @ManyToMany
    @JoinTable(name = "memberProduct")
    private List<Product> productList = new ArrayList<>();
}
