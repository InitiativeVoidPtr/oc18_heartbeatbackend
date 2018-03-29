package ecg.backend.controller;

import ecg.backend.model.entity.Heartbeat;
import ecg.backend.model.repository.HeartbeatRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class HeartbeatController {


    private final HeartbeatRepository heartbeatRepository;

    @Autowired
    public HeartbeatController(final HeartbeatRepository repository) {
        heartbeatRepository = repository;
    }


    @GetMapping("/devices/{id}/latestHeartbeats")
    public List<Heartbeat> getLatestHeartbeats(@PathVariable("id") final long id) {

        return heartbeatRepository.getHeartbeatByDevice_IdOrderByIdDesc(id, PageRequest.of(0, 100));
    }
}
