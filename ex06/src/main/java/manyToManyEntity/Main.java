package manyToManyEntity;

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
        Member member1 = new Member();
        member1.setName("Doqi Kim");
        em.persist(member1);

        Product product1 = new Product();
        product1.setName("mac book white");
        em.persist(product1);

        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member1);
        memberProduct.setProduct(product1);
        memberProduct.setOrderAmount(300);
        em.persist(memberProduct);
    }

    public static void find(EntityManager em) {
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember(1);
        memberProductId.setProduct(1);

        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);
        System.out.println(memberProduct.getMember().getName());
        System.out.println(memberProduct.getProduct().getName());
        System.out.println(memberProduct.getOrderAmount());
    }
}
