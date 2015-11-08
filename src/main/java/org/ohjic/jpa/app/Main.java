package org.ohjic.jpa.app;

import org.ohjic.jpa.model.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-08.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            businessLogin(em);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void businessLogin(EntityManager manager) {
        String id = "id1";

        // ���
        Member member = new Member();
        member.setId(id);
        member.setUsername("Doqi Kim");
        member.setAge(29);

        manager.persist(member);

        // ����
        member.setAge(99);

        // �Ѱ� ��ȸ
        Member findMember = manager.find(Member.class, id);
        System.out.println("findMember : " + findMember);

        // ��� ��ȸ
        List<Member> members = manager.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members : " + members);

        // ����
        manager.remove(member);

    }
}
