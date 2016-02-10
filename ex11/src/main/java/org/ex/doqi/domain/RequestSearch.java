package org.ex.doqi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Kim Donghoon on 2016-02-10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSearch {
    private String memberName;
    private RequestStatus requestStatus;


}
