package valueType;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Kim Donghoon on 2015-11-28.
 */
@Data
@Embeddable
public class Period {
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    public boolean isWork(Date date) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= -1;
    }
}
