package ca.rekabit.launcher.auth.premium;

import ca.rekabit.launcher.auth.premium.exceptions.AuthenticationUnavailableException;
import ca.rekabit.launcher.auth.premium.exceptions.InvalidCreditentialsException;
import ca.rekabit.launcher.auth.premium.exceptions.RequestException;
import ca.rekabit.launcher.auth.premium.exceptions.UserMigratedException;
import ca.rekabit.launcher.auth.premium.responses.AuthenticationResponse;
import ca.rekabit.launcher.auth.premium.responses.ErrorResponse;
import ca.rekabit.launcher.auth.premium.responses.RequestResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Auth {
    private static Profile profile;
    private static String tokenAccess;

    public Auth() {
    }

    public static AuthenticationResponse authenticate(String username, String password, String clientToken, Proxy proxy) throws RequestException, AuthenticationUnavailableException {
        RequestResponse result = sendJsonPostRequest(getRequestUrl("authenticate"), JsonUtils.credentialsToJson(username, password, clientToken), proxy);
        if (result.isSuccessful()) {
            String accessToken = (String)result.getData().get("accessToken");
            String rClientToken = (String)result.getData().get("clientToken");
            Profile selectedProfile = (Profile)JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData().get("selectedProfile")), Profile.class);
            Profile[] availableProfile = (Profile[])JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData().get("availableProfiles")), Profile[].class);
            profile = selectedProfile;
            tokenAccess = accessToken;
            return new AuthenticationResponse(accessToken, rClientToken, selectedProfile, availableProfile);
        } else {
            profile = null;
            tokenAccess = "";
            ErrorResponse errorResponse = (ErrorResponse)JsonUtils.gson.fromJson(JsonUtils.gson.toJson(result.getData()), ErrorResponse.class);
            if (result.getData().get("cause") != null && ((String)((String)result.getData().get("cause"))).equalsIgnoreCase("UserMigratedException")) {
                throw new UserMigratedException(errorResponse);
            } else {
                throw new InvalidCreditentialsException(errorResponse);
            }
        }
    }

    public static AuthenticationResponse authenticate(String username, String password) throws RequestException, AuthenticationUnavailableException {
        return authenticate(username, password, (String)null, (Proxy)null);
    }

    private static URL getRequestUrl(String request) {
        try {
            return new URL("https://authserver.mojang.com/" + request);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    private static RequestResponse sendJsonPostRequest(URL requestUrl, String payload, Proxy proxy) throws AuthenticationUnavailableException {
        HttpsURLConnection connection = null;

        RequestResponse var10;
        try {
            byte[] payloadBytes = payload.getBytes(StandardCharsets.UTF_8);
            connection = (HttpsURLConnection)((HttpsURLConnection)(proxy != null ? requestUrl.openConnection(proxy) : requestUrl.openConnection()));
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(payloadBytes.length));
            connection.setUseCaches(false);
            OutputStream out = connection.getOutputStream();
            out.write(payloadBytes, 0, payloadBytes.length);
            out.close();
            int responseCode = connection.getResponseCode();
            BufferedReader reader = null;
            String response;
            switch(responseCode) {
                case 200:
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                    response = reader.readLine();
                    break;
                case 204:
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
                    response = reader.readLine();
                    break;
                default:
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
                    response = reader.readLine();
            }

            if (reader != null) {
                reader.close();
            }

            Map<String, Object> map = (Map)JsonUtils.gson.fromJson(response, JsonUtils.stringObjectMap);
            var10 = new RequestResponse(responseCode, map);
        } catch (Exception var14) {
            var14.printStackTrace();
            throw new AuthenticationUnavailableException((ErrorResponse)null);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }

        return var10;
    }

    public static Profile getProfile() {
        return profile;
    }

    public static String getTokenAccess() {
        return tokenAccess;
    }
}
