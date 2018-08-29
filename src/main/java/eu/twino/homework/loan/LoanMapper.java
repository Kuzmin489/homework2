package eu.twino.homework.loan;

import eu.twino.homework.loan.repository.LoanEntity;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    Loan loanRequestToLoan(LoanRequest loanRequest, LoanStatus loanStatus, LocalDateTime applyDate);

    LoanEntity domainToEntity(Loan loan);

    Loan entityToDomain(LoanEntity loanEntity);

    List<Loan> entityToDomain(List<LoanEntity> appliedLoans);
}
