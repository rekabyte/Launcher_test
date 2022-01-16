package ca.rekabit.launcher.auth.premium;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonUtils {
    public static Gson gson = new Gson();
    public static Type stringObjectMap = (new TypeToken<Map<String, Object>>() {
    }).getType();
    private static final Map<String, Object> MINECRAFT_AGENT = new LinkedHashMap();

    public JsonUtils() {
    }

    public static String credentialsToJson(String username, String password, String clientToken) {
        Map<String, Object> jsonData = new LinkedHashMap();
        jsonData.putAll(MINECRAFT_AGENT);
        jsonData.put("username", username);
        jsonData.put("password", password);
        if (clientToken != null) {
            jsonData.put("clientToken", clientToken);
        }

        return gson.toJson(jsonData);
    }

    public static String tokenToJson(String authToken, String clientToken) {
        Map<String, Object> jsonData = new LinkedHashMap();
        jsonData.put("accessToken", authToken);
        if (clientToken != null) {
            jsonData.put("clientToken", clientToken);
        }

        return gson.toJson(jsonData);
    }

    static {
        Map<String, Object> agentValues = new LinkedHashMap();
        agentValues.put("name", "Minecraft");
        agentValues.put("version", 1);
        MINECRAFT_AGENT.put("agent", agentValues);
    }
}
