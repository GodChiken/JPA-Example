package joinedStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * Created by Kim Donghoon on 2015-11-21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "joinedStrategyBook")
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "bookId")
public class Book extends Item{
    private String author;
    private String isbn;
}
