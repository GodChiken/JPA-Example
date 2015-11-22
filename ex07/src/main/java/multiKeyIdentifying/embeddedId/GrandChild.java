package multiKeyIdentifying.embeddedId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "multiKeyIdentifyingEmbeddedIdGrandChild")
public class GrandChild {
    @EmbeddedId
    private GrandChildId id;

    @MapsId("parentId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "parentId", referencedColumnName = "id"),
            @JoinColumn(name = "grandParentId", referencedColumnName = "parentId")
    })
    private Child child;
    private String name;
}
