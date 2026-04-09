package ro.fr33styler.springbootcalculator.calculator.history;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryService {

    @Autowired
    private UserHistoryRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void appendHistory(String username, History history) {
        UserHistory userHistory = repository.getUserHistoryByUsername(username);

        if (userHistory == null) {
            userHistory = new UserHistory(username);
            entityManager.persist(userHistory);
        }

        history.setUserHistory(userHistory);
        entityManager.persist(history);
    }

    public List<History> getHistoriesByUsername(String username) {
        UserHistory userHistory = repository.getUserHistoryByUsername(username);
        if (userHistory == null) return List.of();

        return List.copyOf(userHistory.getHistories());
    }

}