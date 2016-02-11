package org.ex.doqi.service;

import org.ex.doqi.domain.*;
import org.ex.doqi.repository.RequestRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Kim Donghoon on 2016-02-10.
 */
public class RequestServiceTest extends SpringTest {
    @Autowired
    private RequestService requestService;
    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void 상품주문() throws Exception {
        Member member = createMember();
        Product product = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        long requestId = requestService.order(member.getId(), product.getId(), orderCount);

        Request getRequest = requestRepository.findOne(requestId);
        assertEquals("상품 주문시 상태는 ORDER", RequestStatus.ORDER, getRequest.getStatus());
        assertEquals("주문한 상품의 종류 수가 정확해야한다.", 1, getRequest.getRequestProducts().size());
        assertEquals("주문 가격은 가격 * 수량이다. ", product.getPrice().multiply(BigDecimal.valueOf(orderCount)), getRequest.getTotalPrice());
        assertEquals("수량만큼 재고가 줄어야한다.", 8, product.getStockQuantity());
    }

    @Test(expected = IllegalStateException.class)
    public void 상품주문_재고수량초과() throws Exception {
        Member member = createMember();
        Product product = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;

        requestService.order(member.getId(), product.getId(), orderCount);

        fail("재고 수량 부족 예외가 발생해야한다.");
    }

    @Test
    public void 주문취소() {
        Member member = createMember();
        Product product = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        long requestId = requestService.order(member.getId(), product.getId(), orderCount);

        requestService.cancelRequest(requestId);

        Request getRequest = requestRepository.findOne(requestId);

        assertEquals("주문 취소시 상태는 CANCEL이다.", RequestStatus.CANCEL, getRequest.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야한다. ", 10, product.getStockQuantity());
    }


    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("경기도 광명시", "하안로 204", "12345"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(BigDecimal.valueOf(price));
        em.persist(book);
        return book;
    }
}