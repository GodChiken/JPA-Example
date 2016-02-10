package org.ex.doqi.repository;

import com.mysema.query.jpa.impl.JPAQuery;
import org.ex.doqi.domain.Request;
import org.ex.doqi.domain.RequestSearch;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.ex.doqi.domain.QRequest.request;

/**
 * Created by Kim Donghoon on 2016-02-10.
 */
@Repository
public class RequestRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Request request) {
        em.persist(request);
    }

    public Request findOne(long id) {
        return em.find(Request.class, id);
    }

    public List<Request> findAll(RequestSearch requestSearch) {
        JPAQuery query = new JPAQuery(em);
        query.from(request);

        if (requestSearch.getRequestStatus() != null) {
            query.where(request.status.eq(requestSearch.getRequestStatus()));
        }

        if (StringUtils.hasText(requestSearch.getMemberName())) {
            query.where(request.member().name.contains(requestSearch.getMemberName()));
        }

        return query.listResults(request).getResults();
    }
}
