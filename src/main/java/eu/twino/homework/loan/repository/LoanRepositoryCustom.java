package eu.twino.homework.loan.repository;

import java.util.List;

public interface LoanRepositoryCustom {

    List<LoanEntity> getAppliedLoans(Integer limit, Integer offset, String sortField);
}
