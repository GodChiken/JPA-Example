package multiKeyIdentifying.convertNonIdentifying;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Kim Donghoon on 2015-11-22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "multiKeyIdentifyingConvertNonIdentifyingParent")
public class Parent {
    @Id
    private String id;
    private String name;
}
