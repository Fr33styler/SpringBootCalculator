package ro.fr33styler.springbootcalculator.calculator.history;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryService {

    @Autowired
    private UserHistoryRepository repository;

    @Transactional
    public void appendHistory(String username, History history) {
        UserHistory userHistory = repository.getUserHistoryByUsername(username);

        if (userHistory == null) {
            userHistory = new UserHistory(username, List.of(history));
        } else {
            userHistory.getHistories().add(history);
        }

        history.setUserHistory(userHistory);

        repository.save(userHistory);
    }

    public List<History> getHistoriesByUsername(String username) {
        UserHistory userHistory = repository.getUserHistoryByUsername(username);
        if (userHistory == null) return List.of();

        return List.copyOf(userHistory.getHistories());
    }

}