package backend.security.dashboard.repository;

import backend.security.dashboard.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log,Long> {

    @Query("SELECT l FROM Log l WHERE (:message IS NULL OR :message = '' OR LOWER(l.message) LIKE CONCAT('%', LOWER(:message), '%')) AND (:logLevel IS NULL OR :logLevel = '' OR LOWER(l.logLevel) = LOWER(:logLevel)) ORDER BY l.date DESC")
    List<Log> findByMessageAndLogLevel(@Param("message") String message, @Param("logLevel") String logLevel);

    @Query("SELECT l from Log l ORDER BY l.date DESC")
    List<Log> findAllLogs();
}
