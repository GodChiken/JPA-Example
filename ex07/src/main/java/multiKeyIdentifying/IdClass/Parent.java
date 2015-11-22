package multiKeyIdentifying.IdClass;

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
@Entity(name = "multiKeyIdentifyingIdClassParent")
public class Parent {
    @Id
    private String id;
    private String name;
}
