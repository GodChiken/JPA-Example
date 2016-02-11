package org.ex.doqi.service;

import org.ex.doqi.domain.Product;
import org.ex.doqi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kim Donghoon on 2016-02-10.
 */
@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public void saveProduct(Product product) {
        this.repository.save(product);
    }

    public List<Product> findProducts() {
        return this.repository.findAll();
    }

    public Product findOne(long productId) {
        return this.repository.findOne(productId);
    }
}
