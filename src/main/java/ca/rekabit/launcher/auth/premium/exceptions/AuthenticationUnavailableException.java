package ca.rekabit.launcher.auth.premium.exceptions;

import ca.rekabit.launcher.auth.premium.responses.ErrorResponse;

public class AuthenticationUnavailableException extends Exception {
    public AuthenticationUnavailableException(ErrorResponse error) {
    }
}

