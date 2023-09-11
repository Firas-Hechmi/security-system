package backend.security.dashboard.repository;

import backend.security.dashboard.model.Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends JpaRepository<Ip,Integer> {
}
