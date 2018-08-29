package eu.twino.homework.loan;

import eu.twino.homework.loan.repository.LoanEntity;
import eu.twino.homework.loan.repository.LoanRepository;
import eu.twino.homework.loan.validation.LoanValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class LoanService {

    private final LoanValidationService loanValidationService;

    private final LoanMapper loanMapper;

    private final LoanRepository loanRepository;


    @Autowired
    public LoanService(LoanValidationService loanValidationService, LoanMapper loanMapper, LoanRepository loanRepository) {
        this.loanValidationService = loanValidationService;
        this.loanMapper = loanMapper;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public void applyForLoan(LoanRequest loanRequest) {
        validateLoanRequest(loanRequest);

        LoanStatus loanStatus = loanValidationService.validateLoanRequest(loanRequest);

        Loan loan = loanMapper.loanRequestToLoan(loanRequest, loanStatus, LocalDateTime.now());
        LoanEntity loanEntity = loanMapper.domainToEntity(loan);
        loanRepository.save(loanEntity);
    }

    @Transactional(readOnly = true)
    public List<Loan> getAppliedLoans(Integer offset, Integer limit, String sortField) {
        List<LoanEntity> appliedLoans = loanRepository.getAppliedLoans(offset, limit, sortField);
        return loanMapper.entityToDomain(appliedLoans);
    }

    private void validateLoanRequest(LoanRequest loanRequest) {
        Objects.requireNonNull(loanRequest, "LoanRequest cannot be null!");
        Objects.requireNonNull(loanRequest.getPersonalId(), "PersonalId cannot be null!");
        Objects.requireNonNull(loanRequest.getAmount(), "Amount cannot be null!");
        Objects.requireNonNull(loanRequest.getApplierName(), "Applier name cannot be null!");
        Objects.requireNonNull(loanRequest.getApplierSurname(), "Applier surname cannot be null!");
        Objects.requireNonNull(loanRequest.getOriginCountry(), "Origin country cannot be null!");
        Objects.requireNonNull(loanRequest.getTerm(), "Term cannot be null!");
    }
}
