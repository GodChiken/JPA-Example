package mappedSuperClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Kim Donghoon on 2015-11-21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mappedSuperclassMember")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "memberId")),
        @AttributeOverride(name = "name", column = @Column(name = "memberName"))
})
public class Member extends BaseEntity {
    private String email;
}
