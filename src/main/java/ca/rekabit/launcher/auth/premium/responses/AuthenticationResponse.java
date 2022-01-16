package ca.rekabit.launcher.auth.premium.responses;

import ca.rekabit.launcher.auth.premium.Profile;

public class AuthenticationResponse extends LoginResponse {
    private Profile[] availableProfiles;

    public AuthenticationResponse(String accessToken, String clientToke, Profile selectedProfile, Profile[] availableProfiles) {
        super(accessToken, clientToke, selectedProfile);
        this.availableProfiles = availableProfiles;
    }

    public Profile[] getAvailableProfiles() {
        return this.availableProfiles;
    }
}

