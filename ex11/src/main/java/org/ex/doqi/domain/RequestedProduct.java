package org.ex.doqi.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal orderPrice;

    private int count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "request_id")
    private Request request;

    public static RequestedProduct createRequestProduct(Product product, BigDecimal orderPrice, int count) {
        RequestedProduct requestedProduct = new RequestedProduct();
        requestedProduct.setProduct(product);
        requestedProduct.setOrderPrice(orderPrice);
        requestedProduct.setCount(count);

        product.removeStock(count);
        return requestedProduct;
    }

    public void cancel() {
        this.getProduct().addStock(count);
    }

    public BigDecimal getTotalPrice() {
        return orderPrice.multiply(BigDecimal.valueOf(count));
    }

}