package jpqlTest.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Kim Donghoon on 2015-12-26.
 */
@Data
@Entity
@DiscriminatorValue("A")
public class Album extends Item {
}
