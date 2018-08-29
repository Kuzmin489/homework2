package eu.twino.homework.loan.repository;

import eu.twino.homework.loan.LoanStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class LoanEntity {

    @Id
    private String personalId;
    private String applierName;
    private String applierSurname;
    private BigDecimal amount;
    private Integer term;
    private String originCountry;
    private LocalDateTime applyDate;
    private LoanStatus loanStatus;

    public String getPersonalId() {
        return personalId;
    }

    public LoanEntity setPersonalId(String personalId) {
        this.personalId = personalId;
        return this;
    }

    public String getApplierName() {
        return applierName;
    }

    public LoanEntity setApplierName(String applierName) {
        this.applierName = applierName;
        return this;
    }

    public String getApplierSurname() {
        return applierSurname;
    }

    public LoanEntity setApplierSurname(String applierSurname) {
        this.applierSurname = applierSurname;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LoanEntity setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Integer getTerm() {
        return term;
    }

    public LoanEntity setTerm(Integer term) {
        this.term = term;
        return this;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public LoanEntity setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
        return this;
    }

    public LocalDateTime getApplyDate() {
        return applyDate;
    }

    public LoanEntity setApplyDate(LocalDateTime applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public LoanEntity setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
        return this;
    }
}
