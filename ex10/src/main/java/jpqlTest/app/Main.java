package jpqlTest.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oopQueryModel.*;

import javax.persistence.*;
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
        //join(em);
        //fetchJoin(em);
        //pathExpression(em);
        //subQuery(em);
        //existsAllAnySomeInt(em);
        //betweenInLikeNull(em);
        //collectionExpression(em);
        //scalaExpression(em);
        //caseExpression(em);
        //polymorphismQuery(em);
        //customFunction(em);
        //usingDirectEntity(em);
        namedQuery(em);

        tx.commit();
        em.close();
        emf.close();
    }

    public static void namedQuery(EntityManager em) {
        System.out.println(
                em.createNamedQuery("Member.findByName", Member.class)
                        .setParameter("name", "김동훈")
                        .getResultList()
        );
        System.out.println(
                em.createNamedQuery("Member.count", Long.class)
                        .getSingleResult()
        );

        System.out.println(
                em.createNamedQuery("Member.xmlFindByName", Member.class)
                        .setParameter("name", "김동훈")
                        .getResultList()
        );
        System.out.println(
                em.createNamedQuery("Member.xmlCount", Long.class)
                        .getSingleResult()
        );
    }

    public static void usingDirectEntity(EntityManager em) {
        System.out.println(
                (Long) em.createQuery("select count(m) from jpqlTestMember m").getSingleResult()
        );

        System.out.println(
                (Long) em.createQuery("select count(m.id) from jpqlTestMember m").getSingleResult()
        );


        Member member = em.find(Member.class, 1L);
        System.out.println(
                em.createQuery("select m from jpqlTestMember m where m = :member", Member.class)
                        .setParameter("member", member)
                        .getSingleResult()
        );

        System.out.println(
                em.createQuery("select m from jpqlTestMember m where m.id = 1", Member.class)
                        .getSingleResult()
        );


        Team team = em.find(Team.class, 1L);
        System.out.println(
                em.createQuery("select m from jpqlTestMember m where m.team = :team")
                        .setParameter("team", team)
                        .getResultList()
        );

        System.out.println(
                em.createQuery("select m from jpqlTestMember m where m.team.id = 1")
                        .getResultList()
        );
    }

    public static void customFunction(EntityManager em) {
        String result = (String) em.createQuery("select get_a() from jpqlTestItem i").getSingleResult();
        System.out.println(result);
    }

    public static void polymorphismQuery(EntityManager em) {
        List<Item> resultList = em.createQuery("select i from jpqlTestItem i", Item.class).getResultList();
        System.out.println(resultList);

        resultList = em.createQuery("select i from jpqlTestItem i where type(i) in (Book)").getResultList();
        System.out.println(resultList);


        resultList = em.createQuery("select i from jpqlTestItem i where i.author = '김동훈'").getResultList();
        System.out.println(resultList);
        resultList = em.createQuery("select i from jpqlTestItem i where treat(i as Book).author = '김동훈'").getResultList();
        System.out.println(resultList);
    }

    public static void caseExpression(EntityManager em) {
        List<Object> resultList = em.createQuery("select case when m.age <= 20 then '20미만' when m.age <= 30 then '30미만' else '기타' end from jpqlTestMember m").getResultList();
        resultList.stream().forEach(row -> {
            System.out.println(row);
        });

        resultList = em.createQuery("select case m.age when 20 then '20' when 30 then '30' else '?' end from jpqlTestMember m").getResultList();
        resultList.stream().forEach(row -> {
            System.out.println(row);
        });

        resultList = em.createQuery("select coalesce(m.name, '이름없는 회원') from jpqlTestMember m").getResultList();
        resultList.stream().forEach(row -> {
            System.out.println(row);
        });

        resultList = em.createQuery("select nullif(m.name, '김동훈') from jpqlTestMember m").getResultList();
        resultList.stream().forEach(row -> {
            System.out.println(row);
        });
    }

    public static void scalaExpression(EntityManager em) {
        List<Object[]> resultList = em.createQuery("select current_time , current_date , current_timestamp from jpqlTestTeam t").getResultList();
        resultList.stream().forEach(row -> {
            Arrays.asList(row).stream()
                    .forEach(column -> System.out.println(column));
        });

        resultList = em.createQuery("select year(current_timestamp), month(current_timestamp), day(current_timestamp) from jpqlTestTeam t").getResultList();
        resultList.stream().forEach(row -> {
            Arrays.asList(row).stream()
                    .forEach(column -> System.out.println(column));
        });
    }

    public static void collectionExpression(EntityManager em) {
        TypedQuery<Member> memberQuery = em.createQuery("select m from jpqlTestMember m where m.orders is not empty", Member.class);
        memberQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));

        Member member = memberQuery.getResultList().get(0);
        TypedQuery<Team> teamQuery = em.createQuery("select t from jpqlTestTeam t where :memberParam member of t.members", Team.class);
        teamQuery.setParameter("memberParam", member);
        teamQuery.getResultList().stream()
                .forEach(team -> System.out.println(team));
    }

    public static void betweenInLikeNull(EntityManager em) {
        TypedQuery<Member> memberQuery = em.createQuery("select m from jpqlTestMember m where m.age between 29 and 31", Member.class);
        memberQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));

        memberQuery = em.createQuery("select m from jpqlTestMember m where m.name in ('김동훈','정현일')", Member.class);
        memberQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));

        memberQuery = em.createQuery("select m from jpqlTestMember m where m.name is null", Member.class);
        memberQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));
    }

    public static void existsAllAnySomeInt(EntityManager em) {
        TypedQuery<Member> memberQuery = em.createQuery("select m from jpqlTestMember m where exists (select t from m.team t where t.name = '개발 2팀')", Member.class);
        memberQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));

        TypedQuery<Order> orderQuery = em.createQuery("select o from jpqlTestOrder o where o.orderAmount > ALL (select p.stockAmount from jpqlTestProduct p)", Order.class);
        orderQuery.getResultList().stream()
                .forEach(order -> System.out.println(order));

        memberQuery = em.createQuery("select m from jpqlTestMember m where m.team = ANY (select t from jpqlTestTeam t)", Member.class);
        memberQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));

        TypedQuery<Team> teamQuery = em.createQuery("select t from jpqlTestTeam t where t IN (select t2 from jpqlTestTeam t2 join t2.members m2 where m2.age >= 30)", Team.class);
        teamQuery.getResultList().stream()
                .forEach(team -> System.out.println(team));
    }


    public static void subQuery(EntityManager em) {
        TypedQuery<Member> memberQuery = em.createQuery("select m from jpqlTestMember m where m.age > (select avg(m2.age) from jpqlTestMember m2)", Member.class);
        memberQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));

        memberQuery = em.createQuery("select m from jpqlTestMember m where (select count(0) from jpqlTestOrder o where m = o.member)>0", Member.class);
        memberQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));

        memberQuery = em.createQuery("select m from jpqlTestMember m where m.orders.size > 0", Member.class);
        memberQuery.getResultList().stream()
                .forEach(member -> System.out.println(member));
    }

    public static void pathExpression(EntityManager em) {
        List<Object> result = em.createQuery("select t.members.size from jpqlTestTeam t").getResultList();
        result.stream()
                .forEach(
                        row -> Arrays.asList(row).stream()
                                .forEach(column -> System.out.println(column))
                );


        TypedQuery<Member> query = em.createQuery("select o.member from jpqlTestOrder o", Member.class);
        System.out.println(query.getResultList());

        TypedQuery<Team> teamQuery = em.createQuery("select o.member.team from jpqlTestOrder o where o.product.name = '마우스'", Team.class);
        System.out.println(teamQuery.getResultList());
    }

    // 영속성 컨테이너의 영향으로 각 fetch join 테스트는 따로따로 수행해야함.
    public static void fetchJoin(EntityManager em) {
        System.out.println("\nDistinct Normal Collection fetch join");
        TypedQuery<Team> teamQuery = em.createQuery("select distinct t from jpqlTestTeam t join fetch t.members", Team.class);
        teamQuery.getResultList().stream()
                .forEach(team -> System.out.println(team));

        System.out.println("\nNormal Collection fetch join");
        teamQuery = em.createQuery("select t from jpqlTestTeam t join t.members", Team.class);
        teamQuery.getResultList().stream()
                .forEach(team -> System.out.println(team));

        System.out.println("\nCollection fetch join");
        teamQuery = em.createQuery("select t from jpqlTestTeam t join fetch t.members", Team.class);
        teamQuery.getResultList().stream()
                .forEach(team -> System.out.println(team));

        System.out.println("\nnormal join");
        TypedQuery<Member> query = em.createQuery("select m from jpqlTestMember m join m.team", Member.class);
        query.getResultList().stream()
                .forEach(member -> System.out.println(member));

        System.out.println("\njoin fetch");
        query = em.createQuery("select m from jpqlTestMember m join fetch m.team", Member.class);
        query.getResultList().stream()
                .forEach(member -> System.out.println(member));
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

        System.out.println("\ntheta join example");
        result = em.createQuery("select count(m) from jpqlTestMember m, jpqlTestTeam t where m.name = t.name").getResultList();
        System.out.println(result);

        System.out.println("\njoin on example");
        query = em.createQuery("select m from jpqlTestMember m join m.team t on t.name = '기획디자인팀'", Member.class);
        query.getResultList().stream()
                .forEach(member -> System.out.println(member));
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
