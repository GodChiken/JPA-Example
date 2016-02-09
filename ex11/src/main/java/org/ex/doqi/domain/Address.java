package org.ex.doqi.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * @author Doqi Kim
 * @version 1.0
 * @created 09-2-2016 오후 6:06:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

}