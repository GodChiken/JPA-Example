package valueType;

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
@Entity(name = "valueTypeMember")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Embedded
    private Period workPeriod;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "homeCity")),
            @AttributeOverride(name = "street", column = @Column(name = "homeStreet")),
            @AttributeOverride(name = "zipcode.zip", column = @Column(name = "homeZip")),
            @AttributeOverride(name = "zipcode.plusFour", column = @Column(name = "homePlusFour"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "companyCity")),
            @AttributeOverride(name = "street", column = @Column(name = "companyStreet")),
            @AttributeOverride(name = "zipcode.zip", column = @Column(name = "companyZip")),
            @AttributeOverride(name = "zipcode.plusFour", column = @Column(name = "companyPlusFour"))
    })
    private Address companyAddress;

    @Embedded
    private PhoneNumber phoneNumber;
}
