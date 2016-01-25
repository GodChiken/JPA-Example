package oopQueryAdvanced;

import com.mysema.query.SearchResults;
import com.mysema.query.jpa.impl.JPAQuery;
import oopQueryModel.Product;
import oopQueryModel.QProduct;

import javax.persistence.*;

import java.util.List;

import static oopQueryModel.QProduct.product;

/**
 * Created by Kim Donghoon on 2016-01-25.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //updateBulk(em);
        //deleteBulk(em);
        //insertBulk(em);
        //problemWithBulk(em);
        //solveProblemWithBulk1(em);
        //solveProblemWithBulk2(em);
        //solveProblemWithBulk3(em);
        problemWithQueryAndFlushMode(em);

        tx.commit();
        em.close();
        emf.close();
    }

    public static void problemWithQueryAndFlushMode(EntityManager em) {
        em.setFlushMode(FlushModeType.COMMIT);
        Product productA = em.find(Product.class, 1L);
        productA.setPrice(2000);

        // em.flush();
        new JPAQuery(em)
                .from(product)
                .where(product.price.eq(2000))
                .setFlushMode(FlushModeType.AUTO)
                .listResults(product)
                .getResults()
                .stream()
                .forEach(product -> System.out.println(product));

    }

    public static void solveProblemWithBulk1(EntityManager em) {
        Product productA = new JPAQuery(em)
                .from(product)
                .where(product.name.eq("마우스"))
                .singleResult(product);

        System.out.println("수정 전 : " + productA.getPrice());

        em.createQuery("update jpqlTestProduct p set p.price = 1")
                .executeUpdate();
        em.refresh(productA);

        System.out.println("수정 후 : " + productA.getPrice());

        System.out.println(productA);
    }

    public static void solveProblemWithBulk2(EntityManager em) {
        Product productA = new JPAQuery(em)
                .from(product)
                .where(product.name.eq("마우스"))
                .singleResult(product);

        em.createQuery("update jpqlTestProduct p set p.price = 1")
                .executeUpdate();
        System.out.println("수정 전 : " + productA.getPrice());
        System.out.println("수정 후 : " + productA.getPrice());

        System.out.println(productA);
    }

    public static void solveProblemWithBulk3(EntityManager em) {
        // initialize persistent context
    }

    public static void problemWithBulk(EntityManager em) {
        Product productA = new JPAQuery(em)
                .from(product)
                .where(product.name.eq("마우스"))
                .singleResult(product);

        System.out.println("수정 전 : " + productA.getPrice());

        em.createQuery("update jpqlTestProduct p set p.price = 1")
                .executeUpdate();

        System.out.println("수정 후 : " + productA.getPrice());

        System.out.println(productA);
    }

    public static void insertBulk(EntityManager em) {
        System.out.println(
                em.createQuery("insert into jpqlTestMember(age, name, team) " +
                        "select m.age, m.name, t from jpqlTestMember m join m.team t")
                        .executeUpdate()
        );
    }

    public static void deleteBulk(EntityManager em) {
        System.out.println(
                em.createQuery("delete from jpqlTestMember m where m.age > :ageTemp")
                        .setParameter("ageTemp", 29)
                        .executeUpdate()
        );
    }

    public static void updateBulk(EntityManager em) {
        System.out.println(
                em.createQuery("update jpqlTestMember m set m.age = 5 where m.age > :ageTemp")
                        .setParameter("ageTemp", 29)
                        .executeUpdate()
        );
    }
}
