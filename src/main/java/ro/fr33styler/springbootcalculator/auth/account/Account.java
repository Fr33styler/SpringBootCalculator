package ro.fr33styler.springbootcalculator.auth.account;

import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String role;

    public Account() {}

    public Account(@NonNull String username, @NonNull String password, @NonNull String role) {
        Objects.requireNonNull(username, "username cannot be null!");
        Objects.requireNonNull(password, "password cannot be null!");
        Objects.requireNonNull(role, "role cannot be null!");

        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        Objects.requireNonNull(username, "username cannot be null!");

        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        Objects.requireNonNull(password, "password cannot be null!");

        this.password = password;
    }

    @NonNull
    public String getRole() {
        return role;
    }

    public void setRole(@NonNull String role) {
        Objects.requireNonNull(role, "role cannot be null!");

        this.role = role;
    }

}