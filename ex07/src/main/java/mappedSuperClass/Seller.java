package mappedSuperClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by Kim Donghoon on 2015-11-21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mappedSuperclassSeller")
public class Seller extends BaseEntity {
    private String shopName;
}
