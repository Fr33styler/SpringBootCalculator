package ro.fr33styler.springbootcalculator.calculator.history;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user_history")
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> histories;

    public UserHistory() {}

    public UserHistory(String username, List<History> histories) {
        this.username = username;
        this.histories = histories;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<History> getHistories() {
        return histories;
    }

}
