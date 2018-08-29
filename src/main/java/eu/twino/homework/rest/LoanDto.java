package eu.twino.homework.rest;

import eu.twino.homework.loan.LoanStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanDto {

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

    public LoanDto setApplierName(String applierName) {
        this.applierName = applierName;
        return this;
    }

    public String getApplierSurname() {
        return applierSurname;
    }

    public LoanDto setApplierSurname(String applierSurname) {
        this.applierSurname = applierSurname;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LoanDto setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Integer getTerm() {
        return term;
    }

    public LoanDto setTerm(Integer term) {
        this.term = term;
        return this;
    }

    public String getPersonalId() {
        return personalId;
    }

    public LoanDto setPersonalId(String personalId) {
        this.personalId = personalId;
        return this;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public LoanDto setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
        return this;
    }

    public LocalDateTime getApplyDate() {
        return applyDate;
    }

    public LoanDto setApplyDate(LocalDateTime applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public LoanDto setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
        return this;
    }
}
