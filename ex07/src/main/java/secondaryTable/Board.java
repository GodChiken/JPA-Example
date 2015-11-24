package secondaryTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "secondaryTableBoard")
@SecondaryTable(name = "boardDetail",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "boardDetailId"))
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(table = "boardDetail")
    private String content;
}
