package oneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "oneToOneMember")
public class Member {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "lockerId")
    private Locker locker;
    private String userName;
}
