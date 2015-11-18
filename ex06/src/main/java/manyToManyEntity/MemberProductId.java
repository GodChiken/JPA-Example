package manyToManyEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Kim Donghoon on 2015-11-18.
 */
@Data
@EqualsAndHashCode
public class MemberProductId implements Serializable{
    private long member;
    private long product;
}
