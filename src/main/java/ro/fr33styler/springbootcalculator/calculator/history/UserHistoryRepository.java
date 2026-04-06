package ro.fr33styler.springbootcalculator.calculator.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {

    UserHistory getUserHistoryByUsername(String username);

}
