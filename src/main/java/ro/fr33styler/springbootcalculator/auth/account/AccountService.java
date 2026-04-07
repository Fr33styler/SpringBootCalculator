package ro.fr33styler.springbootcalculator.auth.account;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository repository;

    public boolean addAccount(String username, String password, String role) {
        if (repository.existsByUsername(username)) {
            return false;
        }
        repository.save(new Account(username, password, role));
        return true;
    }

    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Account account = repository.getAccountByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(account.getRole())
                .build();
    }

}