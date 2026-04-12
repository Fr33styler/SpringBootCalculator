package ro.fr33styler.springbootcalculator.calculations.history;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.util.Objects;

public record HistoryDTO(String operation, BigDecimal a, @JsonInclude(JsonInclude.Include.NON_NULL) BigDecimal b,
                         BigDecimal result) {

    public HistoryDTO(History history) {
        this(history.getOperation(), history.getA(), history.getB(), history.getResult());
    }

    public HistoryDTO(@NonNull String operation, @NonNull BigDecimal a, @Nullable BigDecimal b, @NonNull BigDecimal result) {
        Objects.requireNonNull(operation, "operation cannot be null!");
        Objects.requireNonNull(a, "a cannot be null!");
        Objects.requireNonNull(result, "result cannot be null!");

        this.operation = operation;
        this.a = a;
        this.b = b;
        this.result = result;
    }

    @Override
    public @Nullable BigDecimal b() {
        return b;
    }

}
