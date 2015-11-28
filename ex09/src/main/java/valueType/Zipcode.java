package valueType;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
@Data
@Embeddable
public class Zipcode {
    private String zip;
    private String plusFour;
}
