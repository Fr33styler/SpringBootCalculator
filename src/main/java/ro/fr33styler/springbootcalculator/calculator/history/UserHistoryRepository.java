package ro.fr33styler.springbootcalculator.calculator.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {

    UserHistory getUserHistoryByUsername(String username);

}