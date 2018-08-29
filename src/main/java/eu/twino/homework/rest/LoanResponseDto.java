package eu.twino.homework.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

public class LoanResponseDto {

    private boolean loanApplied;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reason;

    public boolean isLoanApplied() {
        return loanApplied;
    }

    public LoanResponseDto setLoanApplied(boolean loanApplied) {
        this.loanApplied = loanApplied;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public LoanResponseDto setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
