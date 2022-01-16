package ca.rekabit.launcher.auth.premium.exceptions;


import ca.rekabit.launcher.auth.premium.responses.ErrorResponse;

public class UserMigratedException extends RequestException {
    public UserMigratedException(ErrorResponse error) {
        super(error);
    }
}
