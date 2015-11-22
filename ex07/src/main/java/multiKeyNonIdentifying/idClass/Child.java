package multiKeyNonIdentifying.idClass;

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
@Entity(name = "multiKeyNonIdentifyingIdClassChild")
public class Child {
    @Id
    private String id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="id1", referencedColumnName = "id1"),
            @JoinColumn(name="id2", referencedColumnName = "id2")
    })
    private Parent parent;
}
