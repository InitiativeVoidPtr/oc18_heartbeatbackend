package ecg.backend.model.repository;

import ecg.backend.model.entity.Heartbeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

/**
 * Brief description
 * <p>
 * Detailed description
 * <p>
 * <p>
 *
 * @author mspoeri - Die Softwareklitsche GbR
 * @version 1.0
 */
@RepositoryRestResource
public interface HeartbeatRepository extends JpaRepository<Heartbeat, Long> {

}
