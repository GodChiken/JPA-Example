package oneToOne;

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

        Member member = new Member("id", null, "name");
        Locker locker = new Locker(null, "locker1");


        em.persist(locker);
        member.setLocker(locker);
        em.persist(member);

        tx.commit();

        em.close();
        emf.close();
    }
}
