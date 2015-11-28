package valueTypeCollectionToOneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "valueTypeCollectionToOneToManyAddressEntity")
public class AddressEntity {
    @Id
    private long id;

    @Embedded
    private Address address;
}
