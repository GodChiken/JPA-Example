package nativeQuery.app;

import oopQueryModel.Member;

import javax.persistence.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by Kim Donghoon on 2016-01-10.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();

        //native1(em);
        //native2(em);
        //native3(em);
        //native4(em);
        //native5(em);
        //native6(em);
        //usingProcedure(em);
        usingDefinedProcedure(em);

        em.close();
        emf.close();
    }

    public static void usingDefinedProcedure(EntityManager em) {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("multiply");
        query.setParameter("inParam", 100).execute();

        System.out.println(query.getOutputParameterValue("outParam"));

        StoredProcedureQuery query2 = em.createNamedStoredProcedureQuery("multiply_xml");
        query2.setParameter("inParam", 100).execute();

        System.out.println(query2.getOutputParameterValue("outParam"));
    }

    /**
     * resources/sql/load_script.sql 을 수동으로 돌려주자
     *
     * @param em
     */
    public static void usingProcedure(EntityManager em) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("proc_multiply");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT)
                .setParameter(1, 100)
                .execute();

        System.out.println(query.getOutputParameterValue(2));

        StoredProcedureQuery query2 = em.createStoredProcedureQuery("proc_multiply");
        query2.registerStoredProcedureParameter("inParam", Integer.class, ParameterMode.INOUT)
                .registerStoredProcedureParameter("outParam", Integer.class, ParameterMode.INOUT)
                .setParameter("inParam", 100)
                .execute();

        System.out.println(query2.getOutputParameterValue("outParam"));
    }

    public static void native6(EntityManager em) {
        em.createNamedQuery("Member.memberWithOrderCountXml").getResultList().stream()
                .forEach(
                        tuple -> Stream.of((Object[]) tuple)
                                .forEach(column -> System.out.println(column)));
    }

    public static void native5(EntityManager em) {
        em.createNamedQuery("Member.memberWithOrderCount").getResultList().stream()
                .forEach(
                        tuple -> Stream.of((Object[]) tuple)
                                .forEach(column -> System.out.println(column)));
    }

    public static void native4(EntityManager em) {
        TypedQuery<Member> nativeQuery = em.createNamedQuery("Member.memberSQL", Member.class)
                .setParameter(1, 29);
        nativeQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));
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
