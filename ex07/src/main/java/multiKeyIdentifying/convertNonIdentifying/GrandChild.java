package multiKeyIdentifying.convertNonIdentifying;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Kim Donghoon on 2015-11-22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "multiKeyIdentifyingConvertNonIdentifyingGrandChild")
public class GrandChild {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    private Child child;
    private String name;
}
