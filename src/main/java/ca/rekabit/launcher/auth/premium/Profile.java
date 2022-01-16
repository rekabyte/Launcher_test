package ca.rekabit.launcher.auth.premium;

import java.io.Serializable;
import java.util.UUID;

public class Profile implements Serializable {
    private String id;
    private String name;
    private boolean legacy;

    public Profile(String id, String name, boolean legacy) {
        this.id = id;
        this.name = name;
        this.legacy = legacy;
    }

    public UUID getUUID() {
        return UUID.fromString(this.id.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
    }

    public String getName() {
        return this.name;
    }

    public boolean isLegacy() {
        return this.legacy;
    }
}