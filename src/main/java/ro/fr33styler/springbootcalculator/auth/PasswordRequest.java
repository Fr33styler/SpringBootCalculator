package ro.fr33styler.springbootcalculator.auth;

import java.util.Objects;

public class PasswordRequest {

    private String password;

    public PasswordRequest() {}

    public PasswordRequest(String password) {
        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Objects.requireNonNull(password, "password is required!");

        this.password = password;
    }
}
