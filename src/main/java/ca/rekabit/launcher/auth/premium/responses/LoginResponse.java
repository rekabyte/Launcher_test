package ca.rekabit.launcher.auth.premium.responses;

import ca.rekabit.launcher.auth.premium.Profile;

public class LoginResponse {
    private String accessToken;
    private String clientToke;
    private Profile selectedProfile;

    public LoginResponse(String accessToken, String clientToke, Profile selectedProfile) {
        this.accessToken = accessToken;
        this.clientToke = clientToke;
        this.selectedProfile = selectedProfile;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getClientToke() {
        return this.clientToke;
    }

    public Profile getSelectedProfile() {
        return this.selectedProfile;
    }
}