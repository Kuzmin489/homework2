package eu.twino.homework.loan.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository<LoanEntity, String>, LoanRepositoryCustom {

    @Query("select loanEntity from LoanEntity  loanEntity where loanEntity.loanStatus = ''")
    List<LoanEntity> findAppliedLoans(Integer limit, Integer offset);

}
