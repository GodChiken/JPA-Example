package org.ex.doqi.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Doqi Kim
 * @version 1.0
 * @created 09-2-2016 오후 6:06:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<RequestedProduct> requestProducts = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public void setMember(Member member) {
        this.member = member;
        member.getRequests().add(this);
    }

    public void addRequestProduct(RequestedProduct requestedProduct) {
        requestProducts.add(requestedProduct);
        requestedProduct.setRequest(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setRequest(this);
    }

    public static Request createRequest(Member member, Delivery delivery, RequestedProduct... products) {
        Request request = new Request();
        request.setMember(member);
        request.setDelivery(delivery);

        Arrays.asList(products).stream()
                .forEach(product -> request.addRequestProduct(product));

        request.setStatus(RequestStatus.ORDER);
        request.setOrderDate(LocalDate.now());
        return request;
    }

    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new RuntimeException("이미 배송된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(RequestStatus.CALCEL);
        requestProducts.stream().forEach(RequestedProduct::cancel);
    }

    public BigDecimal getTotalPrice() {
        return requestProducts.stream()
                .map(RequestedProduct::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}