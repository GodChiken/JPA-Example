package org.ex.doqi.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Doqi Kim
 * @version 1.0
 * @created 09-2-2016 오후 6:06:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "PD0101")
public class Book extends Product {

    private String artist;
    private int etc;

}