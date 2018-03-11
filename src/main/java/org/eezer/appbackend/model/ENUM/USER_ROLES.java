package org.eezer.appbackend.model.ENUM;

public enum USER_ROLES {
    USER_ROLE_ADMIN("ADMIN"),
    USER_ROLE_DRIVER("DRIVER");

    private String role;

    USER_ROLES(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
