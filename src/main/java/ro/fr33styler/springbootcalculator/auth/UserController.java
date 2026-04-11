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
import ro.fr33styler.springbootcalculator.auth.request.AuthRequest;
import ro.fr33styler.springbootcalculator.auth.request.ChangePasswordRequest;
import ro.fr33styler.springbootcalculator.auth.request.NewAccountRequest;

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

    @PostMapping("/addNewAccount")
    public ResponseEntity<String> addNewAccount(@RequestBody NewAccountRequest request) {
        if (service.addAccount(request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getRole())) {
            return ResponseEntity.ok("Account has been added successfully!");
        }
        return ResponseEntity.badRequest().body("Account already exists!");
    }

    @DeleteMapping("/removeAccount")
    public ResponseEntity<String> removeAccount(@RequestParam String username) {
        if (service.deleteAccount(username)) {
            return ResponseEntity.ok("Account has been removed successfully!");
        }
        return ResponseEntity.badRequest().body("Account does not exist!");
    }

    @PatchMapping("/changeAccountPassword")
    public ResponseEntity<String> changeAccountPassword(@RequestBody ChangePasswordRequest request) {
        if (service.changeAccountPassword(request.getUsername(), passwordEncoder.encode(request.getNewPassword()))) {
            return ResponseEntity.ok("The password has been changed successfully!");
        }
        return ResponseEntity.badRequest().body("Account does not exist!");
    }

    @PatchMapping("/changeAccountRole")
    public ResponseEntity<String> changeAccountRole(@RequestParam String username, @RequestParam String newRole) {
        if (service.changeAccountRole(username, newRole)) {
            return ResponseEntity.ok("The role has been changed successfully!");
        }
        return ResponseEntity.badRequest().body("Account does not exist!");
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(jwtService.createToken(request.getUsername()));
            } else {
                return ResponseEntity.badRequest().body("Invalid account request!");
            }
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}