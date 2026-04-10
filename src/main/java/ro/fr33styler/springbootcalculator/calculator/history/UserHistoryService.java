package ro.fr33styler.springbootcalculator.calculator.history;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<HistoryDTO> getHistoriesByUsername(String username) {
        UserHistory userHistory = repository.getUserHistoryByUsername(username);
        if (userHistory == null) return new ArrayList<>(0);

        List<History> histories = userHistory.getHistories();

        List<HistoryDTO> dtoHistories = new ArrayList<>(histories.size());

        for (History history : histories) {
            dtoHistories.add(new HistoryDTO(history));
        }
        return dtoHistories;
    }

}