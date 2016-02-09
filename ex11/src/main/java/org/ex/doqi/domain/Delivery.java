package org.ex.doqi.domain;


/**
 * @author Doqi Kim
 * @version 1.0
 * @created 09-2-2016 오후 6:14:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery {

    private Address address;
    private DeliveryStatus status;
    private Request request;

}