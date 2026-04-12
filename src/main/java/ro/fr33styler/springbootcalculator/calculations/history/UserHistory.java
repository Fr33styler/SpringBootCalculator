package ro.fr33styler.springbootcalculator.calculations.history;

import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_history", indexes = @Index(name = "user_history_indexes", columnList = "username"))
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @OneToMany(mappedBy = "userHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> histories = new ArrayList<>();

    public UserHistory() {}

    public UserHistory(@NonNull String username) {
        Objects.requireNonNull(username, "username cannot be null!");

        this.username = username;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public List<History> getHistories() {
        return histories;
    }

}