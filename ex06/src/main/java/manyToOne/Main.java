package manyToOne;

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
        Team team = new Team();
        team.setName("real madrid");
        em.persist(team);

        Member member = new Member(null, "김동훈", null);
        em.persist(member);
        member.setTeam(team);

        tx.commit();

        em.close();
        emf.close();
    }
}
