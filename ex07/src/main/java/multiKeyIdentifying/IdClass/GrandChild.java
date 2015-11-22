package multiKeyIdentifying.IdClass;

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
@Entity(name = "multiKeyIdentifyingIdClassGrandChild")
@IdClass(GrandChildId.class)
public class GrandChild {
    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "grandParentId", referencedColumnName = "parentId"),
            @JoinColumn(name = "parentId", referencedColumnName = "id")
    })
    private Child child;

    @Id
    private String id;
    private String name;
}
