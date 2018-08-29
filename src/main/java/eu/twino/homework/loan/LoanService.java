package eu.twino.homework.loan;

import eu.twino.homework.loan.repository.LoanEntity;
import eu.twino.homework.loan.repository.LoanRepository;
import eu.twino.homework.loan.validation.LoanValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public Loan applyForLoan(LoanRequest loanRequest) {

        LoanStatus loanStatus = loanValidationService.validateLoanRequest(loanRequest);

        Loan loan = loanMapper.loanRequestToLoan(loanRequest, loanStatus, LocalDateTime.now());
        LoanEntity loanEntity = loanMapper.domainToEntity(loan);
        if (loanRepository.existsById(loanRequest.getPersonalId())) {
            throw new LoanAlreadyAppliedException(loanRequest.getPersonalId());
        }
        LoanEntity savedLoan =  loanRepository.save(loanEntity);

        return loanMapper.entityToDomain(savedLoan);
    }

    @Transactional(readOnly = true)
    public List<Loan> getAppliedLoans(Integer page, Integer pageSize) {
        Page<LoanEntity> appliedLoans = loanRepository.getLoanEntityByLoanStatus(LoanStatus.APPLIED, PageRequest.of(page, pageSize));
        return loanMapper.entityToDomain(appliedLoans.getContent());
    }
}
