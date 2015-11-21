package tablePerClassStrategy;

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
@Entity(name = "tablePerClassStrategyItem")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
}
