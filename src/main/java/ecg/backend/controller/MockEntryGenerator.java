package ecg.backend.controller;

import ecg.backend.model.entity.Device;
import ecg.backend.model.entity.Heartbeat;
import ecg.backend.model.repository.DeviceRepository;
import ecg.backend.model.repository.HeartbeatRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.stereotype.Service;

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
@Service
public class MockEntryGenerator {

    private static final int  NUMBER_OF_DEVICES = 10;
    private static final long UPDATE_RATE_MS    = 1000;


    private final DeviceRepository    deviceRepository;
    private final HeartbeatRepository heartbeatRepository;

    private final DefaultManagedTaskScheduler taskScheduler;

    private final List<ScheduledFuture<?>> scheduledFutures;

    @Autowired
    public MockEntryGenerator(@NotNull final DeviceRepository deviceRepository,
                              @NotNull final HeartbeatRepository heartbeatRepository) {


        this.deviceRepository = deviceRepository;
        this.heartbeatRepository = heartbeatRepository;

        taskScheduler = new DefaultManagedTaskScheduler();
        scheduledFutures = new LinkedList<>();

    }

    public void startGenerator() {
        //Create devices
        for (int i = 0; i < NUMBER_OF_DEVICES; i++) {
            final long   deviceId;
            final Device device;

            deviceId = i;

            device = Optional.ofNullable(deviceRepository.getDeviceById(deviceId))
                             .orElseGet(() -> deviceRepository.saveAndFlush(new Device("TEST_DEVICE_" + deviceId)));

            scheduledFutures.add(taskScheduler.scheduleAtFixedRate(() -> createNewSample(device),
                                                                   Duration.of(UPDATE_RATE_MS, ChronoUnit.MILLIS).toMillis()));
        }
    }

    private void createNewSample(@NotNull final Device device) {
        final LocalDateTime now;
        final Heartbeat     heartbeat;
        final long          secondsOfHour;

        now = LocalDateTime.now();
        heartbeat = new Heartbeat();
        secondsOfHour = Duration.between(LocalTime.of(now.getHour(), 0, 0), now).getSeconds();


        heartbeat.setDevice(device);
        heartbeat.setTimeStamp(now);
        heartbeat.setValue(40 * Math.sin((2 * Math.PI * secondsOfHour) / 1800) + 100);

        heartbeatRepository.saveAndFlush(heartbeat);
    }
}
