package eu.twino.homework.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class LoanRequestDto {

    @NotNull
    @Positive(message = "Loan amount should be more than zero")
    private BigDecimal amount;

    @NotNull
    @Positive(message = "Term should be more than zero")
    private Integer term;

    @NotNull
    @Size(min=2, message="Name should have at least 2 characters")
    private String name;

    @NotNull
    @Size(min=2, message="Surname should have at least 2 characters")
    private String surname;

    @NotNull
    private String personalId;

    public BigDecimal getAmount() {
        return amount;
    }

    public LoanRequestDto setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Integer getTerm() {
        return term;
    }

    public LoanRequestDto setTerm(Integer term) {
        this.term = term;
        return this;
    }

    public String getName() {
        return name;
    }

    public LoanRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public LoanRequestDto setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPersonalId() {
        return personalId;
    }

    public LoanRequestDto setPersonalId(String personalId) {
        this.personalId = personalId;
        return this;
    }
}
