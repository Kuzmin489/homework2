package eu.twino.homework.loan.validation;

public class ValidationError {
    private String errorCode;
    private String errorMessage;

    public ValidationError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
