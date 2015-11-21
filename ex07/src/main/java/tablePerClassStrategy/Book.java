package tablePerClassStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by Kim Donghoon on 2015-11-21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tablePerClassStrategyBook")
public class Book extends Item {
    private String author;
    private String isbn;
}
