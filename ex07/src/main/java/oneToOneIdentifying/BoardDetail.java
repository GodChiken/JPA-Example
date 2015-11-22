package oneToOneIdentifying;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "oneToOneIdentifyingBoardDetail")
public class BoardDetail {
    @Id
    private long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Board board;
    private String content;
}
