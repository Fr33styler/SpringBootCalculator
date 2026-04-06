package ro.fr33styler.springbootcalculator.calculator.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserHistoryService {

    @Autowired
    private UserHistoryRepository repository;

    public void appendHistory(String username, History history) {
        UserHistory userHistory = repository.getUserHistoryByUsername(username);
        if (userHistory == null) {
            userHistory = new UserHistory(username, new ArrayList<>());
        }
        userHistory.getHistories().add(history);
        repository.save(userHistory);
    }

    public List<History> getHistoriesByUsername(String username) {
        UserHistory userHistory = repository.getUserHistoryByUsername(username);
        if (userHistory == null) return List.of();

        return userHistory.getHistories();
    }

}
