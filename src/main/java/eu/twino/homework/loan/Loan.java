package eu.twino.homework.loan;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Loan {
    private String applierName;
    private String applierSurname;
    private BigDecimal amount;
    private Integer term;
    private String personalId;
    private String originCountry;
    private LocalDateTime applyDate;
    private LoanStatus loanStatus;

    public String getApplierName() {
        return applierName;
    }

    public Loan setApplierName(String applierName) {
        this.applierName = applierName;
        return this;
    }

    public String getApplierSurname() {
        return applierSurname;
    }

    public Loan setApplierSurname(String applierSurname) {
        this.applierSurname = applierSurname;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Loan setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Integer getTerm() {
        return term;
    }

    public Loan setTerm(Integer term) {
        this.term = term;
        return this;
    }

    public String getPersonalId() {
        return personalId;
    }

    public Loan setPersonalId(String personalId) {
        this.personalId = personalId;
        return this;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public Loan setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
        return this;
    }

    public LocalDateTime getApplyDate() {
        return applyDate;
    }

    public Loan setApplyDate(LocalDateTime applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public Loan setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
        return this;
    }
}
