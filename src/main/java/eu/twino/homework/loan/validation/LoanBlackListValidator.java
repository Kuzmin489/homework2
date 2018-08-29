package eu.twino.homework.loan.validation;

import eu.twino.homework.blacklist.BlackListService;
import eu.twino.homework.loan.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoanBlackListValidator implements LoanValidator {

    private final BlackListService blackListService;

    @Autowired
    public LoanBlackListValidator(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    @Override
    public Optional<ValidationError> validate(LoanRequest loanRequest) {
        return blackListService.getBlackList(loanRequest.getPersonalId())
                .map(blackList -> new ValidationError("black_list", "Person is in black list!"));
    }
}
