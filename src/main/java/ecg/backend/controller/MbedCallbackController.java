package ecg.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecg.backend.model.entity.Device;
import ecg.backend.model.entity.Heartbeat;
import ecg.backend.model.mbed.AsyncResponse;
import ecg.backend.model.mbed.AsyncResponseList;
import ecg.backend.model.repository.DeviceRepository;
import ecg.backend.model.repository.HeartbeatRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class MbedCallbackController {

    private static final Logger logger = LogManager.getLogger(MbedDeviceController.class);

    private final LocalDateTime       bootTime;
    private final DeviceRepository    deviceRepository;
    private final HeartbeatRepository heartbeatRepository;

    @Autowired
    public MbedCallbackController(@NotNull final DeviceRepository deviceRepository,
                                  @NotNull final HeartbeatRepository heartbeatRepository) {
        this.bootTime = LocalDateTime.now();
        this.deviceRepository = deviceRepository;
        this.heartbeatRepository = heartbeatRepository;
    }

    @PutMapping("/callback")
    public void callback(@RequestBody final String string) {


        final AsyncResponseList asyncResponseList;
        ObjectMapper            mapper = new ObjectMapper();
        logger.info("###\n");
        logger.info("###\n");
        logger.info("###\n");
        try {
            asyncResponseList = mapper.readValue(string, AsyncResponseList.class);
            storeValues(asyncResponseList);
        } catch (IOException e) {
            logger.warn("Unable to parse JSON", e);
        }


        logger.info(string);
        logger.info("###\n");
        logger.info("###\n");
        logger.info("###\n");
    }

    private void storeValues(@NotNull final AsyncResponseList list) {

        list.getAsyncRespons().forEach(logger::info);
        for (AsyncResponse response : list.getAsyncRespons()
                                          .stream()
                                          .filter(Objects::nonNull)
                                          .filter(response -> response.getError() == null)
                                          .collect(Collectors.toList())) {
            final String name;

            final Device device;

            name = response.getId().substring(response.getId().indexOf("#") + 1, response.getId().indexOf("@"));

            logger.info("Found device: " + name);

            device = deviceRepository.getDevicesByNameIgnoreCase(name)
                                     .stream()
                                     .findAny()
                                     .orElseGet(() -> deviceRepository.saveAndFlush(new Device(name)));

            heartbeatsFromPayload(response.getPayload(), device).forEach(logger::info);
        }

        list.getAsyncRespons()
            .stream()
            .filter(response -> response.getError() != null)
            .forEach(response -> logger.error("Response error: " + response));
    }

    private List<Heartbeat> heartbeatsFromPayload(@NotNull final String payload,
                                                  @NotNull final Device device) {
        final List<Heartbeat> heartbeats;
        heartbeats = new LinkedList<>();

        for (final String valueSet : payload.split(";")) {
            final String[]  values;
            final Heartbeat newHeartbeat;

            values = valueSet.trim().split(",");

            newHeartbeat = new Heartbeat();

            newHeartbeat.setDevice(device);
            newHeartbeat.setTimeStamp(bootTime.plus(Long.getLong(values[0]), ChronoUnit.MILLIS));
            newHeartbeat.setValue(Double.valueOf(values[1]));

            heartbeats.add(newHeartbeat);
        }
        return heartbeats;
    }

}
