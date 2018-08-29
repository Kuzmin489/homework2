package eu.twino.homework.loan.validation;

import eu.twino.homework.loan.LoanRequest;
import eu.twino.homework.loan.LoanStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanValidationService.class);

    private final List<LoanValidator> validators;

    @Autowired
    public LoanValidationService(List<LoanValidator> validators) {
        this.validators = validators;
    }

    public LoanStatus validateLoanRequest(LoanRequest loanRequest) {
        boolean shouldPass = validators.stream()
                .map(loanValidator -> loanValidator.validate(loanRequest))
                .peek(validationError -> validationError.ifPresent(this::logError))
                .anyMatch(Optional::isPresent);

        return shouldPass ? LoanStatus.REJECTED : LoanStatus.APPLIED;
    }

    private void logError(ValidationError validationError) {
        LOGGER.info("Loan Rejected reason: {}", validationError.getErrorMessage());
    }
}
