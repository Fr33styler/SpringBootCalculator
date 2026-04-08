package ro.fr33styler.springbootcalculator.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.fr33styler.springbootcalculator.auth.account.AccountService;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    /* TODO Add default admin here!
    @PostConstruct
    public void init() {
        service.addUser("your_username", passwordEncoder.encode("your_password"), "ADMIN");
    }*/

    @PostMapping("/addNewAccount")
    public ResponseEntity<String> addNewAccount(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (service.addAccount(username, passwordEncoder.encode(password), role)) {
            return ResponseEntity.ok("User has been added successfully!");
        }
        return ResponseEntity.badRequest().body("User already exists!");
    }

    @DeleteMapping("/removeAccount")
    public ResponseEntity<String> removeAccount(@RequestParam String username) {
        if (service.deleteAccount(username)) {
            return ResponseEntity.ok("User has been removed successfully!");
        }
        return ResponseEntity.badRequest().body("User does not exist!");
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(jwtService.createToken(username));
            } else {
                return ResponseEntity.badRequest().body("Invalid user request!");
            }
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}