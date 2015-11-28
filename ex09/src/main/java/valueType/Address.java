package valueType;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
@Data
@Embeddable
public class Address {
    private String city;
    private String street;
    @Embedded
    private Zipcode zipcode;
}
