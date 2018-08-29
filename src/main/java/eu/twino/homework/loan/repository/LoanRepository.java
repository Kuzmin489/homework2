package eu.twino.homework.loan.repository;

import eu.twino.homework.loan.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LoanRepository extends PagingAndSortingRepository<LoanEntity, String> {

    Page<LoanEntity> getLoanEntityByLoanStatus(LoanStatus loanStatus, Pageable pageable);

}
