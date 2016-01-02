package querydsl.app;

import com.mysema.query.jpa.impl.JPAQuery;
import oopQueryModel.Member;
import oopQueryModel.QMember;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Kim Donghoon on 2016-01-02.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //queryDSL(em);
        //basic(em);
        searchCondtionQuery(em);


        tx.commit();
        em.close();
        emf.close();

    }

    public static void searchCondtionQuery(EntityManager em) {
        JPAQuery query = new JPAQuery();

    }

    public static void basic(EntityManager em) {
        JPAQuery query = new JPAQuery(em);
        List<Member> list = query.from(QMember.member)
                .where(QMember.member.name.eq("김동훈"))
                .orderBy(QMember.member.name.desc())
                .list(QMember.member);

        System.out.println(list);
    }

    public static void queryDSL(EntityManager em) {
        JPAQuery query = new JPAQuery(em);
        QMember qMember = new QMember("m");
        List<Member> members = query.from(qMember)
                .where(qMember.name.eq("김동훈"))
                .orderBy(qMember.name.desc())
                .list(qMember);

        System.out.println(members);

    }
}
