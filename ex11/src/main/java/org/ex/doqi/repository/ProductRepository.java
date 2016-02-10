package org.ex.doqi.repository;

import org.ex.doqi.domain.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kim Donghoon on 2016-02-10.
 */
@Repository
public class ProductRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Product product) {
        if (product.getId() == 0) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    public Product findOne(long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }
}
