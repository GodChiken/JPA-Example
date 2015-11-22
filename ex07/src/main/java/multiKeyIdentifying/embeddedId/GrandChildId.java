package multiKeyIdentifying.embeddedId;

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
public class GrandChildId implements Serializable{
    private ChildId parentId;
    private String id;
}
