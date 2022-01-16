package ca.rekabit.launcher.auth.premium.exceptions;

import ca.rekabit.launcher.auth.premium.responses.ErrorResponse;

public class RequestException extends Exception {
    private ErrorResponse error;

    public RequestException(ErrorResponse error) {
        this.error = error;
    }

    public ErrorResponse getError() {
        return this.error;
    }

    public String getErrorMessage() {
        return this.error.getErrorMessage();
    }

    public String getErrorCause() {
        return this.error.getCause();
    }
}