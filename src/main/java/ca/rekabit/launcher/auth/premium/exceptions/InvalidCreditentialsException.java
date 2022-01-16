package ca.rekabit.launcher.auth.premium.exceptions;

import ca.rekabit.launcher.auth.premium.responses.ErrorResponse;

public class InvalidCreditentialsException extends RequestException {
    public InvalidCreditentialsException(ErrorResponse error) {
        super(error);
    }
}
