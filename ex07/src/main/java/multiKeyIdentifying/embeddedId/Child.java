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
@Entity(name = "multiKeyIdentifyingEmbeddedIdChild")
public class Child {
    @EmbeddedId
    private ChildId childId;

    @MapsId("parentId") // embedded ID 의 parentId로 매핑
    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    public Parent parent;
    private String name;
}
