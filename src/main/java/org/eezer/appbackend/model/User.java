package org.eezer.appbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonObject;
import org.eezer.appbackend.model.ENUM.USER_ROLES;

@SuppressWarnings({"WeakerAccess", "unused"})
public class User implements Model {

    private String username;
    private String password;
    private String role;
    private String realName;
    private String phone;
    private String email;
    private String organization;
    private String other;

    @JsonCreator
    public User(@JsonProperty(value = "username", required = true) String username,
                @JsonProperty(value = "password", required = true) String password,
                @JsonProperty(value = "role", required = true) String role,
                @JsonProperty(value = "realName", required = true) String realName,
                @JsonProperty(value = "phone", required = true) String phone,
                @JsonProperty(value = "email", required = true) String email) {

        setUsername(username);
        setPassword(password);
        setRole(role);
        setRealName(realName);
        setPhone(phone);
        setEmail(email);
    }

    @Override
    public JsonObject toJson() {
        return new JsonObject()
            .put("username", username)
            .put("password", password)
            .put("role", role)
            .put("realName", realName)
            .put("phone", phone)
            .put("email", email)
            .put("organization", organization)
            .put("other", other);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() < 5) throw new IllegalArgumentException("username must be at least 5 chars");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 8) throw new IllegalArgumentException("password must be at least 8 chars");
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (!role.equals(USER_ROLES.USER_ROLE_ADMIN.getRole()) && !role.equals(USER_ROLES.USER_ROLE_DRIVER.getRole())) {
            throw new IllegalArgumentException("Role not valid");
        }
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        if (realName.length() < 5) throw new IllegalArgumentException("realName must be at least 5 chars");
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.length() < 4) throw new IllegalArgumentException("email must be at least 4 chars");
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
