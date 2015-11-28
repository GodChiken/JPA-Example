package valueType;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
@Data
@Embeddable
public class PhoneNumber {
    private String areaCode;
    private String localNumber;
    @ManyToOne
    private PhoneServiceProvider provider;
}
