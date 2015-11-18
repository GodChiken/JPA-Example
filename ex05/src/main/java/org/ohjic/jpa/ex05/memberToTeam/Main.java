package org.ohjic.jpa.ex05.memberToTeam;

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
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", team1, "회원 1");
        em.persist(member1);

        Member member2 = new Member("member2", team1, "회원 2");
        em.persist(member2);

        System.out.println("object graph search : ");
        System.out.println(
                em.find(Member.class, "member1")
        );

        System.out.println("JPQL : ");
        System.out.println(
                em.createQuery("select m from member_membertoteam m join m.team t where t.name = :teamName", Member.class)
                        .setParameter("teamName", "팀1")
                        .getResultList()
        );

        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);

        System.out.println("\n==================================================");
        System.out.println("member1의 팀이 team2로 수정되었다 =>" +
                em.find(Member.class, "member1")
        );
        System.out.println("==================================================\n");

        System.out.println("\n==================================================");
        member1.setTeam(null);
        System.out.println("member1는 아무곳에도 들어있지 않게 되었다 =>" +
                        em.find(Member.class, "member1")
        );
        System.out.println("==================================================\n");

        System.out.println("\n==================================================");
        member1.setTeam(null);
        member2.setTeam(null);
        em.remove(team1);
        em.remove(team2);
        System.out.println("아무것도 존재하지 않게 되었다고 한다.");
        System.out.println("==================================================\n");

        tx.commit();
    }
}
