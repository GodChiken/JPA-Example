package org.ex.doqi.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
    @OneToOne(mappedBy = "delivery")
    private Request request;

}