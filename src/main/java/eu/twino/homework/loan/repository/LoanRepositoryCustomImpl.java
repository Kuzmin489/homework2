package eu.twino.homework.loan.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LoanRepositoryCustomImpl implements LoanRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public LoanRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<LoanEntity> getAppliedLoans(Integer limit, Integer offset, String sortField) {
        String jpql = "select loanEntity from LoanEntity loanEntity";

        return entityManager.createQuery(jpql, LoanEntity.class).setMaxResults(limit).setFirstResult(offset).getResultList();
    }
}
