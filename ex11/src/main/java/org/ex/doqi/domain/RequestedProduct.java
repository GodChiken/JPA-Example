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
public class RequestedProduct {

    private long id;
    private BigDecimal orderPrice;
    private int count;
    private Product product;
    private Request request;

}