package multiKeyIdentifying.convertNonIdentifying;

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
@Entity(name = "multiKeyIdentifyingConvertNonIdentifyingChild")
public class Child {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    private Parent parent;
    private String name;
}
