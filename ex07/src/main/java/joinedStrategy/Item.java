package joinedStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "joinedStrategyItem")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dType")
public abstract class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
}
