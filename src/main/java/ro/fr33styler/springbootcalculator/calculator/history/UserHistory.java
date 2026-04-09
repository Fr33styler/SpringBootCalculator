package ro.fr33styler.springbootcalculator.calculator.history;

import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_history")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @OneToMany(mappedBy = "userHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> histories;

    public UserHistory() {
        histories = new ArrayList<>();
    }

    public UserHistory(@NonNull String username, @NonNull Collection<History> histories) {
        Objects.requireNonNull(username, "username cannot be null!");
        Objects.requireNonNull(histories, "The histories cannot be null!");

        this.username = username;
        this.histories = new ArrayList<>(histories);
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