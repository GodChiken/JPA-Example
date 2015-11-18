package manyToMany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kim Donghoon on 2015-11-15.
 */
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fin_jpa_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        init(emf);
        tx.begin();

        save(em);
        find(em);


        tx.commit();

        findInverse(em);
        em.close();
        emf.close();
    }

    /**
     * 저장
     * @param em
     */
    public static void save(EntityManager em){
        Product productA = new Product("productA", "상품A", new ArrayList<>());
        em.persist(productA);

        Member member = new Member("member1", "회원1", Arrays.asList(productA));
        em.persist(member);
    }

    /**
     * 조회
     * @param em
     */
    public static void find(EntityManager em){
        Member member = em.find(Member.class, "member1");
        member.getProducts().stream()
                .forEach(product -> System.out.println(product.getName()));
    }

    /**
     * 양방향 주인이 아닌 곳에서 찾기
     * @param em
     */
    public static void findInverse(EntityManager em){
        Product product = em.find(Product.class, "김동훈의 상품 ID");
        product.getMembers().stream()
                .forEach(member -> System.out.println(member.getName()));
    }

    public static void init(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Product product = new Product();
        product.setId("김동훈의 상품 ID");
        product.setName("김동훈의 상품");

        Product product2 = new Product();
        product2.setId("김동훈의 상품2 ID");
        product2.setName("김동훈의 상품2");

        Member member = new Member();
        member.setName("김동훈");
        member.setId("김동훈의 ID");
        member.addProduct(product);
        member.addProduct(product2);

        Member member2 = new Member();
        member2.setId("김동훈2의 ID");
        member2.setName("김동훈2");
        member2.addProduct(product);

        tx.begin();
        em.persist(product);
        em.persist(product2);
        em.persist(member);
        em.persist(member2);
        tx.commit();

        em.close();
    }
}
