package querydsl.app;

import com.mysema.query.QueryModifiers;
import com.mysema.query.SearchResults;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Projections;
import com.mysema.query.types.expr.BooleanExpression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oopQueryModel.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static oopQueryModel.QMember.*;
import static oopQueryModel.QOrder.order;
import static oopQueryModel.QProduct.product;

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
        //searchConditionQuery(em);
        //pagingNOrdering(em);
        //grouping(em);
        //joining(em);
        //subQuery(em);
        //projection(em);
        distinct(em);

        tx.commit();
        em.close();
        emf.close();

    }

    public static void distinct(EntityManager em) {
        new JPAQuery(em).distinct()
                .from(product)
                .list(product);
    }

    public static void projection(EntityManager em) {
        new JPAQuery(em).from(product)
                .list(product.name)
                .stream()
                .forEach(name -> System.out.println(name));

        new JPAQuery(em).from(product)
                .list(product.name, product.price)
                .stream()
                .forEach(tuple -> System.out.println("name=>" + tuple.get(product.name) + " / price=>" + tuple.get(product.price)));

        new JPAQuery(em).from(product)
                .list(Projections.bean(ProductDTO.class, product.name.as("productName"), product.price.as("productPrice")))
                .stream()
                .forEach(tuple -> System.out.println(tuple));

        new JPAQuery(em).from(product)
                .list(Projections.fields(ProductDTO.class, product.name.as("productName"), product.price.as("productPrice")))
                .stream()
                .forEach(tuple -> System.out.println(tuple));

        new JPAQuery(em).from(product)
                .list(Projections.constructor(ProductDTO.class, product.name.as("productName"), product.price.as("productPrice")))
                .stream()
                .forEach(tuple -> System.out.println(tuple));

    }

    public static void subQuery(EntityManager em) {
        QProduct subProduct = new QProduct("sub");
        new JPAQuery(em).from(product)
                .where(product.price.eq(
                        new JPASubQuery().from(subProduct).unique(subProduct.price.max())
                ))
                .list(product);

        new JPAQuery(em).from(product)
                .where(product.in(
                        new JPASubQuery().from(subProduct)
                                .where(subProduct.name.eq(product.name))
                                .list(subProduct)
                ))
                .list(product);
    }

    public static void joining(EntityManager em) {
        new JPAQuery(em).from(order)
                .join(order.member(), member)
                .leftJoin(order.product(), product)
                .list(order);

        new JPAQuery(em).from(order)
                .join(order.product(), product)
                .on(product.price.gt(2))
                .list(order);

        new JPAQuery(em).from(order)
                .join(order.member(), member).fetch()
                .leftJoin(order.product(), product).fetch()
                .list(order);
    }

    public static void grouping(EntityManager em) {
        new JPAQuery(em).from(product)
                .groupBy(product.name)
                .having(product.name.contains("상품"))
                .list(product);
    }

    public static void pagingNOrdering(EntityManager em) {
        new JPAQuery(em).from(product)
                .where(product.price.gt(2000))
                .orderBy(product.price.desc())
                .offset(10).limit(20)
                .list(product);

        QueryModifiers queryModifiers = new QueryModifiers(20L, 10L);
        new JPAQuery(em).from(product)
                .restrict(queryModifiers)
                .list(product);

        SearchResults<Product> result = new JPAQuery(em).from(product)
                .offset(10).limit(20)
                .listResults(product);

        long toal = result.getTotal();
        long offest = result.getOffset();
        long limit = result.getLimit();
        List<Product> results = result.getResults();
    }

    public static void searchConditionQuery(EntityManager em) {
        JPAQuery query = new JPAQuery(em);
        BooleanExpression[] where = {product.name.contains("상품"), product.name.startsWith("상"), product.name.endsWith("품")};
        query.from(product)
                .where(where)
                .uniqueResult(product);

        query = new JPAQuery(em);
        query.from(product)
                .where(product.price.between(1000, 2000))
                .singleResult(product);
    }

    public static void basic(EntityManager em) {
        JPAQuery query = new JPAQuery(em);
        List<Member> list = query.from(member)
                .where(member.name.eq("김동훈"))
                .orderBy(member.name.desc())
                .list(member);

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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductDTO {
        private String productName;
        private int productPrice;
    }

}

