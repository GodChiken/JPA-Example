package manyToMany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Product productA = new Product("productA", "상품A");
        em.persist(productA);

        Member member = new Member("member1", "회원1", Arrays.asList(productA));
        em.persist(member);

        tx.commit();

        em.close();
        emf.close();
    }
}
