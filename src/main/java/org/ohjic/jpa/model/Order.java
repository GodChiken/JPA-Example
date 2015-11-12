package org.ohjic.jpa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-09.
 */

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    private Member member;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    private Date orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void setMember(Member member){
        if(this.member != null){
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);
    }
}
