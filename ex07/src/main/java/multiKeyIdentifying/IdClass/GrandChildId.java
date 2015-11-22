package multiKeyIdentifying.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Kim Donghoon on 2015-11-22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GrandChildId implements Serializable{
    private Child child;
    private String id;
}
