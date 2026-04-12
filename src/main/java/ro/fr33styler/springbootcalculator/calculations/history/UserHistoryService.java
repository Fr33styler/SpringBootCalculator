package ro.fr33styler.springbootcalculator.calculations.history;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserHistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Transactional
    public void appendHistory(String username, History history) {
        UserHistory userHistory = userHistoryRepository.getUserHistoryByUsername(username);

        if (userHistory == null) {
            userHistory = new UserHistory(username);
            userHistoryRepository.save(userHistory);
        }

        history.setUserHistory(userHistory);
        historyRepository.save(history);
    }

    public List<HistoryDTO> getHistoriesByUsername(String username) {
        UserHistory userHistory = userHistoryRepository.getUserHistoryByUsername(username);
        if (userHistory == null) return new ArrayList<>(0);

        List<History> histories = userHistory.getHistories();

        List<HistoryDTO> dtoHistories = new ArrayList<>(histories.size());

        for (History history : histories) {
            dtoHistories.add(new HistoryDTO(history));
        }
        return dtoHistories;
    }

}