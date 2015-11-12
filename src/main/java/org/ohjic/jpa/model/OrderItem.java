package org.ohjic.jpa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-09.
 */
@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Item item;
    @ManyToOne
    private Order order;
    private int orderPrice;
    private int count;
}
