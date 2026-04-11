package ro.fr33styler.springbootcalculator.auth.request;

import java.util.Objects;

public class NewAccountRequest {

    private String username;
    private String password;
    private String role;

    public NewAccountRequest() {}

    public NewAccountRequest(String username, String password, String role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        Objects.requireNonNull(username,  "username cannot be null!");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Objects.requireNonNull(password,  "password cannot be null!");
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        Objects.requireNonNull(role,  "role cannot be null!");

        this.role = role;
    }

}
