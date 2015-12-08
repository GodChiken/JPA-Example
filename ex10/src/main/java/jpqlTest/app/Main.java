package jpqlTest.app;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import jpqlTest.model.Address;
import jpqlTest.model.Member;
import jpqlTest.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        //newKeyword(em);
        //paging(em);
        //setAndOrdering(em);
        join(em);

        tx.commit();
        em.close();
        emf.close();
    }

    public static void join(EntityManager em) {
        System.out.println("\ninner join example");
        TypedQuery<Member> query = em.createQuery("select m from jpqlTestMember m join m.team t where t.name = :teamName", Member.class);
        System.out.println(
                query.setParameter("teamName", "개발 1팀").getResultList()
        );

        System.out.println("\nselect multiple object using inner join example");
        List<Object[]> result = em.createQuery("select m,t from jpqlTestMember m join m.team t where t.name = :teamName")
                .setParameter("teamName", "개발 1팀")
                .getResultList();
        result.stream()
                .forEach(row -> System.out.println((Member) row[0] + " // " + (Team) row[1]));

        System.out.println("\nouter join example");
        query = em.createQuery("select m from jpqlTestMember m left join m.team t", Member.class);
        System.out.println(
                query.getResultList()
        );

        System.out.println("\ncollection join example");
        TypedQuery<Team> queryTeam = em.createQuery("select t from jpqlTestTeam t left join t.members m", Team.class);
        queryTeam.getResultList().stream()
                .forEach(
                        team -> System.out.println(team + " -> " + team.getMembers())
                );

    }

    public static void setAndOrdering(EntityManager em) {
        System.out.println("\nstatistic example");
        List<Object[]> result = em.createQuery("select count(m), sum(m.age), avg(m.age), max(m.age), min(m.age) from jpqlTestMember m").getResultList();
        result.stream()
                .forEach(
                        row -> Arrays.asList(row).stream()
                                .forEach(column -> System.out.println(column))
                );

        System.out.println("\ngroup by example");
        result = em.createQuery("select t.name, count(m), sum(m.age), avg(m.age), max(m.age), min(m.age) from jpqlTestMember m " +
                "left join m.team t group by t.name").getResultList();
        result.stream()
                .forEach(
                        row -> Arrays.asList(row).stream()
                                .forEach(column -> System.out.println(column))
                );

        System.out.println("\nhaving example");
        result = em.createQuery("select t.name, count(m), sum(m.age), avg(m.age), max(m.age), min(m.age) from jpqlTestMember m " +
                "left join m.team t group by t.name having avg(m.age) > 30").getResultList();
        result.stream()
                .forEach(
                        row -> Arrays.asList(row).stream()
                                .forEach(column -> System.out.println(column))
                );

        System.out.println("\norder by example");
        result = em.createQuery("select t.name, count(m.age) as cnt from jpqlTestMember m left join m.team t group by t.name order by cnt").getResultList();
        result.stream()
                .forEach(
                        row -> Arrays.asList(row).stream()
                                .forEach(column -> System.out.println(column))
                );

    }

    public static void paging(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("select m from jpqlTestMember m where team.id = :teamId order by m.id asc", Member.class);
        query.setParameter("teamId", 1L);
        query.setFirstResult(2);
        query.setMaxResults(1);
        System.out.println(
                query.getResultList()
        );
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
