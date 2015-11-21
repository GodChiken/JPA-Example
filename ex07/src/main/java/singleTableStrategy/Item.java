package singleTableStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="singleTableStrategyItem")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dType")
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
}
