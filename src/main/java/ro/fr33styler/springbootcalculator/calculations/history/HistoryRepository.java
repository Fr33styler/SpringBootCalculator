package ro.fr33styler.springbootcalculator.calculations.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {

}
