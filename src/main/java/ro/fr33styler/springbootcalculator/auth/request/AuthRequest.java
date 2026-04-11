package ro.fr33styler.springbootcalculator.auth.request;

import java.util.Objects;

public class AuthRequest {

    private String username;
    private String password;

    public AuthRequest() {}

    public AuthRequest(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null!");

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Objects.requireNonNull(password, "password cannot be null!");

        this.password = password;
    }

}
