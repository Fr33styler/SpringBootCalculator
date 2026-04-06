package ro.fr33styler.springbootcalculator.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fr33styler.springbootcalculator.auth.JwtService;
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

    @Autowired
    private JwtService jwtService;

    private static final MathContext MATH_CONTEXT = new MathContext(10, RoundingMode.HALF_UP);

    @GetMapping("/history")
    public List<History> getHistory(@RequestHeader("Authorization") String authorization) {
        String username = jwtService.getUsernameFromToken(authorization.substring(7));
        return userHistoryService.getHistoriesByUsername(username);
    }

    @GetMapping("/add")
    public BigDecimal add(@RequestHeader("Authorization") String authorization, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.add(b);

        String username = jwtService.getUsernameFromToken(authorization.substring(7));
        userHistoryService.appendHistory(username, new History("add", a, b, result));

        return result;
    }

    @GetMapping("/subtract")
    public BigDecimal subtract(@RequestHeader("Authorization") String authorization, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.subtract(b);

        String username = jwtService.getUsernameFromToken(authorization.substring(7));
        userHistoryService.appendHistory(username, new History("subtract", a, b, result));

        return result;
    }

    @GetMapping("/multiply")
    public BigDecimal multiply(@RequestHeader("Authorization") String authorization, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.multiply(b);

        String username = jwtService.getUsernameFromToken(authorization.substring(7));
        userHistoryService.appendHistory(username, new History("multiply", a, b, result));

        return result;
    }

    @GetMapping("/divide")
    public BigDecimal divide(@RequestHeader("Authorization") String authorization, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.divide(b, MATH_CONTEXT);

        String username = jwtService.getUsernameFromToken(authorization.substring(7));
        userHistoryService.appendHistory(username, new History("divide", a, b, result));

        return result;
    }

    @GetMapping("/sqrt")
    public BigDecimal sqrt(@RequestHeader("Authorization") String authorization, @RequestParam BigDecimal a) {
        BigDecimal result = a.sqrt(MATH_CONTEXT);

        String username = jwtService.getUsernameFromToken(authorization.substring(7));
        userHistoryService.appendHistory(username, new History("sqrt", a, null, result));

        return result;
    }

    @GetMapping("/power")
    public BigDecimal power(@RequestHeader("Authorization") String authorization, @RequestParam BigDecimal a, @RequestParam int b) {
        BigDecimal result = a.pow(b, MATH_CONTEXT);

        String username = jwtService.getUsernameFromToken(authorization.substring(7));
        userHistoryService.appendHistory(username, new History("power", a, BigDecimal.valueOf(b), result));

        return result;
    }

}
