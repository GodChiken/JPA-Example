package multiKeyNonIdentifying.embeddedId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;

/**
 * Created by Kim Donghoon on 2015-11-22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "multiKeyNonIdentifyingEmbeddedIdParent")
public class Parent {
    @EmbeddedId
    private ParentId id;
    private String name;
}
