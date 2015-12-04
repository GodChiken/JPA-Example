package jpqlTest.app;

import jpqlTest.model.Address;
import jpqlTest.model.Member;
import jpqlTest.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Kim Donghoon on 2015-12-04.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //queryVsTypedQuery(em);
        //parameterBinding(em);
        //entityTypeProjection(em);
        //embeddedTypeProjection(em);
        //scalaTypeProjection(em);
        //selectMultiValue(em);
        newKeyword(em);

        tx.commit();
        em.close();
        emf.close();
    }

    public static void newKeyword(EntityManager em) {
        List<Object[]> memberList = em.createQuery("select name, age from jpqlTestMember m").getResultList();
        System.out.println("\nnew keyword 1.");
        System.out.println(
                memberList.stream()
                        .map(object -> new UserDTO((String) object[0], (Integer) object[1]))
                        .collect(toList())
        );

        TypedQuery<UserDTO> query = em.createQuery("select new jpqlTest.app.UserDTO(name, age) from jpqlTestMember m", UserDTO.class);
        System.out.println("\nnew keyword 2.");
        System.out.println(
                query.getResultList()
        );
    }

    public static void selectMultiValue(EntityManager em) {
        List<Object[]> resultList = em.createQuery("select name, age from jpqlTestMember m").getResultList();
        resultList.stream()
                .forEach(result -> System.out.println(result[0] + " / " + result[1]));

        List<Object[]> orderList = em.createQuery("select o.member, o.product, o.orderAmount from jpqlTestOrder o").getResultList();
        orderList.stream()
                .forEach(order -> System.out.println(order[0] + " / " + order[1] + " / " + order[2]));
    }

    public static void scalaTypeProjection(EntityManager em) {
        System.out.println("\nscalaTypeProjection 1.");
        System.out.println(
                em.createQuery("select team from jpqlTestMember m", Team.class).getResultList()
        );

        System.out.println("\nscalaTypeProjection 2.");
        System.out.println(
                em.createQuery("select DISTINCT team from jpqlTestMember m", Team.class).getResultList()
        );

        System.out.println("\nscalaTypeProjection 3.");
        System.out.println(
                em.createQuery("select AVG(orderAmount) from jpqlTestOrder o", Double.class).getSingleResult()
        );
    }

    public static void embeddedTypeProjection(EntityManager em) {
        System.out.println("\nEmbeddedTypeProjection 1.");
        System.out.println(
                em.createQuery("select o.address from jpqlTestOrder o", Address.class).getResultList()
        );
    }

    public static void entityTypeProjection(EntityManager em) {
        System.out.println("\nentityTypeProjection 1.");
        System.out.println(
                em.createQuery("select m from jpqlTestMember m", Member.class).getResultList()
        );

        System.out.println("\nentityTypeProjection 2.");
        System.out.println(
                em.createQuery("select m.team from jpqlTestMember m", Team.class).getResultList()
        );
    }

    public static void parameterBinding(EntityManager em) {
        String userNameParam = "김동훈";
        TypedQuery<Member> query = em.createQuery("select m from jpqlTestMember m where name = :userNameParam", Member.class);
        query.setParameter("userNameParam", userNameParam);

        System.out.println("\nparameterBinding 1.");
        System.out.println(query.getResultList());

        System.out.println("\nparameterBinding 2.");
        System.out.println(
                em.createQuery("select m from jpqlTestMember m where name = :userNameParam", Member.class)
                        .setParameter("userNameParam", userNameParam)
                        .getResultList()
        );

        System.out.println("\nparameterBinding 3.");
        System.out.println(
                em.createQuery("select m from jpqlTestMember m where name = ?1", Member.class)
                        .setParameter(1, userNameParam)
                        .getResultList()
        );
    }

    public static void queryVsTypedQuery(EntityManager em) {
        System.out.println("\nqueryVsTypedQuery 1.");
        System.out.println(
                em.createQuery("select m.name from jpqlTestMember m").getResultList()
        );

        System.out.println("\nqueryVsTypedQuery 2.");
        System.out.println(
                em.createQuery("select m from jpqlTestMember m", Member.class).getResultList()
        );
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class UserDTO {
    private String name;
    private int age;
}
