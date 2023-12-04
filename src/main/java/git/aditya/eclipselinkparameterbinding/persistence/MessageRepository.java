package git.aditya.eclipselinkparameterbinding.persistence;

import git.aditya.eclipselinkparameterbinding.entity.JSONMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository {
    @Autowired
    protected EntityManager entityManager;

    public List<JSONMessage> getMessage() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<JSONMessage> cq = cb.createQuery(JSONMessage.class);
        Root<JSONMessage> eventLogRoot = cq.from(JSONMessage.class);
        cq.where(getSearchPredicate(cb, eventLogRoot));
        TypedQuery<JSONMessage> searchQuery = entityManager.createQuery(cq);
        searchQuery.setHint(QueryHints.BIND_PARAMETERS, false);
        return searchQuery.getResultList();
    }

    private Predicate[] getSearchPredicate(CriteriaBuilder cb, Root<JSONMessage> eventLogRoot) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(cb.function("JSON_VALUE",
                                            String.class,
                                            eventLogRoot.get("message"),
                                            cb.literal("$.applicationId")), "1"));

        return predicates.toArray(Predicate[]::new);
    }

}
