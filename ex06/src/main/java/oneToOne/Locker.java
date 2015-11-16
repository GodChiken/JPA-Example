package oneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "oneToOneLocker")
public class Locker {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
