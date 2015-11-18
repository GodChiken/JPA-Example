package manyToManyNonIdentifying;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        save(em);
        find(em);

        tx.commit();
        em.close();
        emf.close();
    }

    public static void save(EntityManager em) {
        Member member = new Member();
        member.setName("Doqi Kim");
        em.persist(member);

        Product product = new Product();
        product.setName("macbook pro");
        em.persist(product);

        Order order = new Order();
        order.setMember(member);
        order.setProduct(product);
        order.setOrderAmount(3000);
        em.persist(order);
    }

    public static void find(EntityManager em) {
        Order order = em.find(Order.class, 1L);
        System.out.println(order.getMember().getName());
        System.out.println(order.getProduct().getName());
        System.out.println(order.getOrderAmount());
    }
}
