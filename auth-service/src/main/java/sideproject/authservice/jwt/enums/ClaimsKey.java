package sideproject.authservice.jwt.enums;

public enum ClaimsKey {
    ROLES("roles"), ID("id"), NICKNAME("nickname"), AUTHORITY("authority");

    private final String value;

    ClaimsKey(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return this.value;
    }

}
