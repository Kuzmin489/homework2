package eu.twino.homework.loan;

public class LoanApplyResult {
    private boolean applied;
    private String reason;

    public LoanApplyResult(boolean applied) {
        this.applied = applied;
    }

    public LoanApplyResult(boolean applied, String reason) {
        this.applied = applied;
        this.reason = reason;
    }

    public boolean isApplied() {
        return applied;
    }

    public LoanApplyResult setApplied(boolean applied) {
        this.applied = applied;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public LoanApplyResult setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
