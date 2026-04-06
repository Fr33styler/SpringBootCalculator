package ro.fr33styler.springbootcalculator.calculator.history;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity(name = "histories")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String operation;
    private BigDecimal a;
    private BigDecimal b;
    private BigDecimal result;

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

}
