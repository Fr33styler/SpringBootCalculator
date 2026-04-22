package ro.fr33styler.springbootcalculator.auth.account;

import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository repository;

    @Transactional
    public void addAccount(String username, String password, String role) {
        if (repository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        repository.save(new Account(username, password, role));
    }

    @Transactional
    public void deleteAccount(String username) throws UsernameNotFoundException {
        if (repository.existsByUsername(username)) {
            repository.deleteAccountByUsername(username);
        } else {
            throwUsernameNotFoundException(username);
        }
    }

    @Transactional
    public void updateAccountRole(String username, String newRole) throws UsernameNotFoundException {
        Account account = repository.getAccountByUsername(username);

        if (account == null) {
            throwUsernameNotFoundException(username);
        }

        account.setRole(newRole);
    }

    @Transactional
    public void updateAccountPassword(String username, String newPassword) {
        Account account = repository.getAccountByUsername(username);

        if (account == null) {
            throwUsernameNotFoundException(username);
        }

        account.setPassword(newPassword);
    }

    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Account account = repository.getAccountByUsername(username);

        if (account == null) {
            throwUsernameNotFoundException(username);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(account.getRole())
                .build();
    }

    private void throwUsernameNotFoundException(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

}