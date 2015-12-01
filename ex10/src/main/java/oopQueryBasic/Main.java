package oopQueryBasic;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Kim Donghoon on 2015-12-01.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        System.out.println("==========================================jpql");
        jpql(em);
        System.out.println("==========================================criteria");
        criteria(em);
        System.out.println("==========================================queryDsl");
        queryDsl(em);
        System.out.println("==========================================nativeSql");
        nativeSql(em);
        System.out.println("==========================================jdbc");
        jdbc(em);


        tx.commit();
        em.close();
        emf.close();
    }

    public static void jdbc(EntityManager em) {
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                System.out.println("jdbc connection을 얻다");
            }
        });
    }

    public static void nativeSql(EntityManager em) {
        String sql = "select id, title_name from oop_query_basic_member where title_name = 'kim'";
        System.out.println(em.createNativeQuery(sql, Member.class).getResultList());
    }

    public static void queryDsl(EntityManager em) {
        /*JPAQuery query = new JPAQuery(em);
        QMember member = QMember.member;

        System.out.println(
                query.from(member)
                .where(member.username.eq("kim"))
                .list(member)
        );*/
    }

    public static void criteria(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);
        Root<Member> m = query.from(Member.class);

        CriteriaQuery<Member> cq = query.select(m)
                .where(cb.equal(m.get("titleName"), "kim"));    // meta oopQueryBasic
        System.out.println(
                em.createQuery(cq).getResultList()
        );
    }

    public static void jpql(EntityManager em) {
        String jpql = "select b from oopQueryBasicBoard b";
        System.out.println(
                em.createQuery(jpql, Board.class).getResultList()
        );

        jpql = "select m from oopQueryBasicMember m where titleName = 'titlename'";
        System.out.println(
                em.createQuery(jpql, Member.class).getResultList()
        );
    }
}
