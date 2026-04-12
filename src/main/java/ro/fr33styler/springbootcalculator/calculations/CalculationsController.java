package ro.fr33styler.springbootcalculator.calculations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.fr33styler.springbootcalculator.calculations.history.History;
import ro.fr33styler.springbootcalculator.calculations.history.HistoryDTO;
import ro.fr33styler.springbootcalculator.calculations.history.UserHistoryService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("/calculations")
public class CalculationsController {

    @Autowired
    private UserHistoryService userHistoryService;

    private static final MathContext MATH_CONTEXT = new MathContext(10, RoundingMode.HALF_UP);

    @GetMapping("/histories")
    public List<HistoryDTO> getHistories(Authentication authentication) {
        return userHistoryService.getHistoriesByUsername(authentication.getName());
    }

    @GetMapping("/additions")
    public BigDecimal addition(Authentication authentication, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.add(b);

        userHistoryService.appendHistory(authentication.getName(), new History("addition", a, b, result));

        return result;
    }

    @GetMapping("/subtractions")
    public BigDecimal subtraction(Authentication authentication, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.subtract(b);

        userHistoryService.appendHistory(authentication.getName(), new History("subtraction", a, b, result));

        return result;
    }

    @GetMapping("/multiplications")
    public BigDecimal multiplication(Authentication authentication, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.multiply(b);

        userHistoryService.appendHistory(authentication.getName(), new History("multiplication", a, b, result));

        return result;
    }

    @GetMapping("/divisions")
    public BigDecimal division(Authentication authentication, @RequestParam BigDecimal a, @RequestParam BigDecimal b) {
        BigDecimal result = a.divide(b, MATH_CONTEXT);

        userHistoryService.appendHistory(authentication.getName(), new History("division", a, b, result));

        return result;
    }

    @GetMapping("/sqrts")
    public BigDecimal sqrt(Authentication authentication, @RequestParam BigDecimal a) {
        BigDecimal result = a.sqrt(MATH_CONTEXT);

        userHistoryService.appendHistory(authentication.getName(), new History("sqrt", a, null, result));

        return result;
    }

    @GetMapping("/powers")
    public BigDecimal power(Authentication authentication, @RequestParam BigDecimal a, @RequestParam int b) {
        BigDecimal result = a.pow(b, MATH_CONTEXT);

        userHistoryService.appendHistory(authentication.getName(), new History("power", a, BigDecimal.valueOf(b), result));

        return result;
    }

}