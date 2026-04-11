package ro.fr33styler.springbootcalculator.auth.request;

import java.util.Objects;

public class ChangePasswordRequest {

    private String username;
    private String newPassword;

    public ChangePasswordRequest() {}

    public ChangePasswordRequest(String username, String newPassword) {
        setUsername(username);
        setNewPassword(newPassword);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Objects.requireNonNull(username, "username cannot be null!");

        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        Objects.requireNonNull(newPassword, "newPassword cannot be null!");

        this.newPassword = newPassword;
    }
}
