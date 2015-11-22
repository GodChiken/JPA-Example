package multiKeyNonIdentifying.embeddedId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Kim Donghoon on 2015-11-22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ParentId implements Serializable{
    private String id1;
    private String id2;
}
