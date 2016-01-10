package nativeQuery.app;

import oopQueryModel.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Arrays;

/**
 * Created by Kim Donghoon on 2016-01-10.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();

        //native1(em);
        //native2(em);
        native3(em);

        em.close();
        emf.close();
    }

    public static void native3(EntityManager em) {
        String sql = "select member.*, count(0) order_count, sum(product.price) price_sum from jpql_test_member member " +
                "left join jpql_test_order orders on member.id = orders.member_id " +
                "left join jpql_test_product product on orders.product_id = product.id " +
                "group by member.id";

        em.createNativeQuery(sql, "memberWithOrderCount").getResultList()
                .stream()
                .forEach(tuple -> Arrays.asList((Object[]) tuple).stream()
                        .forEach(value -> System.out.println(value)));
    }

    public static void native2(EntityManager em) {
        String sql = "select * from jpql_test_member where age > ?";
        Query nativeQuery = em.createNativeQuery(sql).setParameter(1, 20);
        nativeQuery.getResultList()
                .stream()
                .forEach(tuple -> Arrays.asList((Object[]) tuple).stream()
                        .forEach(value -> System.out.println(value)));
    }

    public static void native1(EntityManager em) {
        String sql = "select * from jpql_test_member where age > ?";
        Query nativeQuery = em.createNativeQuery(sql, Member.class).setParameter(1, 20);
        nativeQuery.getResultList()
                .stream()
                .forEach(member -> System.out.println(member));
    }


}
