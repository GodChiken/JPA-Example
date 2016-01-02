package oopQueryModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Kim Donghoon on 2015-12-26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
}
