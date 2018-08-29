package eu.twino.homework.loan.repository;

import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<LoanEntity, String>, LoanRepositoryCustom {

}
