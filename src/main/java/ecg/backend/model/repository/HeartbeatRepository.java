package ecg.backend.model.repository;

import ecg.backend.model.entity.Heartbeat;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

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
    List<Heartbeat> getHeartbeatByDevice_IdOrderByIdDesc(@NotNull Long id, @NotNull Pageable pageable);
    List<Heartbeat> getHeartbeatByDevice_IdOrderByIdDesc(@NotNull Long id);
}
