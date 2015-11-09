package org.ohjic.jpa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kim Donghoon on 2015-11-09.
 */

@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long memberId;
    private Date orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
