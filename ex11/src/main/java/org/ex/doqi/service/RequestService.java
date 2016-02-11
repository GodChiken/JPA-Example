package org.ex.doqi.service;

import org.ex.doqi.domain.*;
import org.ex.doqi.repository.MemberRepository;
import org.ex.doqi.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kim Donghoon on 2016-02-10.
 */
@Service
@Transactional
public class RequestService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ProductService productService;

    public long order(long memberId, long productId, int count) {
        Member member = memberRepository.fineOne(memberId);
        Product product = productService.findOne(productId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        RequestedProduct requestedProduct = RequestedProduct.createRequestProduct(product, product.getPrice(), count);
        Request request = Request.createRequest(member, delivery, requestedProduct);

        requestRepository.save(request);
        return request.getId();
    }

    public void cancelRequest(long requestId) {
        Request request = requestRepository.findOne(requestId);
        request.cancel();
    }

    public List<Request> findRequests(RequestSearch requestSearch) {
        return requestRepository.findAll(requestSearch);
    }
}
