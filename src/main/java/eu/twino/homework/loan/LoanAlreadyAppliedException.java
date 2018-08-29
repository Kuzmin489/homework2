package eu.twino.homework.loan;

public class LoanAlreadyAppliedException extends RuntimeException {

    private final String personCode;

    public LoanAlreadyAppliedException(String personCode) {
        super();
        this.personCode = personCode;
    }

    public String getPersonCode() {
        return personCode;
    }
}
