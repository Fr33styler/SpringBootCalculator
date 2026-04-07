package ro.fr33styler.springbootcalculator.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.fr33styler.springbootcalculator.calculator.history.History;
import ro.fr33styler.springbootcalculator.calculator.history.UserHistoryService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    private UserHistoryService userHistoryService;

    private static final MathContext MATH_CONTEXT = new MathContext(10, RoundingMode.HALF_UP);

    @GetMapping("/history")
    public List<History> getHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return List.of();

        return userHistoryService.getHistoriesByUsername(authentication.getName());
    }

    @GetMapping("/add")
    public BigDecimal add(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.add(b);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return result;

        userHistoryService.appendHistory(authentication.getName(), new History("add", a, b, result));

        return result;
    }

    @GetMapping("/subtract")
    public BigDecimal subtract(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.subtract(b);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return result;

        userHistoryService.appendHistory(authentication.getName(), new History("subtract", a, b, result));

        return result;
    }

    @GetMapping("/multiply")
    public BigDecimal multiply(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.multiply(b);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return result;

        userHistoryService.appendHistory(authentication.getName(), new History("multiply", a, b, result));

        return result;
    }

    @GetMapping("/divide")
    public BigDecimal divide(@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.divide(b, MATH_CONTEXT);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return result;

        userHistoryService.appendHistory(authentication.getName(), new History("divide", a, b, result));

        return result;
    }

    @GetMapping("/sqrt")
    public BigDecimal sqrt(@RequestParam BigDecimal a) {
        BigDecimal result = a.sqrt(MATH_CONTEXT);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return result;

        userHistoryService.appendHistory(authentication.getName(), new History("sqrt", a, null, result));

        return result;
    }

    @GetMapping("/power")
    public BigDecimal power(@RequestParam BigDecimal a, @RequestParam int b) {
        BigDecimal result = a.pow(b, MATH_CONTEXT);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return result;

        userHistoryService.appendHistory(authentication.getName(), new History("power", a, BigDecimal.valueOf(b), result));

        return result;
    }

}