package multiKeyNonIdentifying.idClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by Kim Donghoon on 2015-11-22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "multiKeyNonIdentifyingIdClassParent")
@IdClass(ParentId.class)
public class Parent {
    @Id
    private String id1;

    @Id
    private String id2;

    private String name;
}
