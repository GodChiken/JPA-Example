package org.ex.doqi.domain;


/**
 * @author Doqi Kim
 * @version 1.0
 * @created 09-2-2016 오후 6:06:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends Product {

    private String artist;
    private int etc;

}