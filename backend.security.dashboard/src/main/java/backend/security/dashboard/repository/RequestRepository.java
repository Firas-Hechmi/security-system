package backend.security.dashboard.repository;

import backend.security.dashboard.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    Optional<Request> findById(Long id);

    @Query("SELECT r from Request r ORDER BY r.date DESC")
    List<Request> findAllRequests();
}
