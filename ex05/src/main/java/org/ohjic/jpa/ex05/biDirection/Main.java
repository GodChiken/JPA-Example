package org.ohjic.jpa.ex05.biDirection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Kim Donghoon on 2015-11-12.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Team team = new Team("team1", "팀1", null);
        em.persist(team);

        Member member1 = new Member("member1", team, "회원1");
        Member member2 = new Member("member2", team, "회원2");
        em.persist(member1);
        em.persist(member2);

        em.flush();
        tx.commit();
        System.out.println("테스트데이터 준비 끝");

        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
        team = em.find(Team.class, "team1");
        team.getMembers()
                .stream()
                .forEach(t -> System.out.println(t.getUsername()));

        tx.commit();
    }
}
