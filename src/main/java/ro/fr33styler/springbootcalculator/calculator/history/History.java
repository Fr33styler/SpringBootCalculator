package ro.fr33styler.springbootcalculator.calculator.history;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "history")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String operation;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal result;

    @ManyToOne
    @JoinColumn(name = "user_history_id")
    @JsonBackReference
    private UserHistory userHistory;

    public History() {}

    public History(String operation, BigDecimal a, BigDecimal b, BigDecimal result) {
        this.operation = operation;
        this.a = a;
        this.b = b;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public BigDecimal getA() {
        return a;
    }

    public BigDecimal getB() {
        return b;
    }

    public BigDecimal getResult() {
        return result;
    }

    public UserHistory getUserHistory() {
        return userHistory;
    }

    void setUserHistory(UserHistory userHistory) {
        this.userHistory = userHistory;
    }

}