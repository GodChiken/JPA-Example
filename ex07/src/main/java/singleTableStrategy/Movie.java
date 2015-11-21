package singleTableStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Kim Donghoon on 2015-11-21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "singleTableStrategyMovie")
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String actor;
}
