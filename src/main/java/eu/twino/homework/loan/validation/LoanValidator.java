package eu.twino.homework.loan.validation;

import eu.twino.homework.loan.LoanRequest;

import java.util.Optional;

public interface LoanValidator {
    Optional<ValidationError> validate(LoanRequest loanRequest);
}
