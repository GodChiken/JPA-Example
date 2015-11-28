package valueTypeCollectionToOneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
