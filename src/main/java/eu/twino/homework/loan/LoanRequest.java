package eu.twino.homework.loan;

import java.math.BigDecimal;


public class LoanRequest {

    private BigDecimal amount;
    private Integer term;
    private String applierName;
    private String applierSurname;
    private String personalId;
    private String originCountry;



    public BigDecimal getAmount() {
        return amount;
    }

    public LoanRequest setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Integer getTerm() {
        return term;
    }

    public LoanRequest setTerm(Integer term) {
        this.term = term;
        return this;
    }

    public String getApplierName() {
        return applierName;
    }

    public LoanRequest setApplierName(String applierName) {
        this.applierName = applierName;
        return this;
    }

    public String getApplierSurname() {
        return applierSurname;
    }

    public LoanRequest setApplierSurname(String applierSurname) {
        this.applierSurname = applierSurname;
        return this;
    }

    public String getPersonalId() {
        return personalId;
    }

    public LoanRequest setPersonalId(String personalId) {
        this.personalId = personalId;
        return this;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public LoanRequest setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
        return this;
    }
}
