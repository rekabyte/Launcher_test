package ca.rekabit.launcher.auth.premium.responses;

public class ErrorResponse {
    private String error;
    private String errorMessage;
    private String cause;

    public ErrorResponse() {
    }

    public String getError() {
        return this.error;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getCause() {
        return this.cause;
    }
}

