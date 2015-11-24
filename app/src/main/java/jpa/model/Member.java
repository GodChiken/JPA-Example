package jpa.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "regiDate")),
        @AttributeOverride(name = "lastModifiedDate", column = @Column(name = "lastUpdateDate"))
})
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<Orders> orders = new ArrayList<>();

}