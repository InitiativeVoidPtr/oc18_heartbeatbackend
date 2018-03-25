package ecg.backend.model.repository;

import ecg.backend.model.entity.Device;
import java.util.List;
import javax.validation.constraints.NotNull;
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
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Device getDeviceById(@NotNull Long id);

    List<Device> getDevicesByNameIgnoreCase(@NotNull String name);

}
