package ro.fr33styler.springbootcalculator.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.fr33styler.springbootcalculator.auth.account.AccountService;

@RestController
@RequestMapping("/auth/accounts")
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/{username}/{role}")
    public ResponseEntity<String> createAccount(@PathVariable String username, @PathVariable String role, @RequestBody PasswordRequest request) {
        service.addAccount(username, passwordEncoder.encode(request.getPassword()), role);
        return ResponseEntity.ok("Account has been added successfully!");
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteAccount(@RequestParam String username) {
        service.deleteAccount(username);
        return ResponseEntity.ok("Account has been removed successfully!");
    }

    @PatchMapping("/{username}/password")
    public ResponseEntity<String> updateAccountPassword(@PathVariable String username, @RequestBody PasswordRequest request) {
        service.updateAccountPassword(username, passwordEncoder.encode(request.getPassword()));
        return ResponseEntity.ok("The password has been changed successfully!");
    }

    @PatchMapping("/{username}/role/{role}")
    public ResponseEntity<String> updateAccountRole(@PathVariable String username, @PathVariable String role) {
        service.updateAccountRole(username, role);
        return ResponseEntity.ok("The role has been changed successfully!");
    }

    @PostMapping("/token/{username}")
    public ResponseEntity<String> generateToken(@PathVariable String username, @RequestBody PasswordRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(jwtService.createToken(username));
            } else {
                return ResponseEntity.badRequest().body("Invalid account request!");
            }
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
        }
    }
}