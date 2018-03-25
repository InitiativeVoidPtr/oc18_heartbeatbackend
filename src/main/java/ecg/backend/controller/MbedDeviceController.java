package ecg.backend.controller;

import ecg.backend.config.MbedConfig;
import ecg.backend.model.entity.Device;
import ecg.backend.model.mbed.MbedDevice;
import ecg.backend.model.mbed.MbedDeviceList;
import ecg.backend.model.repository.DeviceRepository;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
public class MbedDeviceController {

    private static final Logger logger = LogManager.getLogger(MbedDeviceController.class);

    private final DeviceRepository    deviceRepository;
    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public MbedDeviceController(@NotNull final DeviceRepository deviceRepository,
                                @NotNull @Qualifier("mbedTemplateBuilder") final RestTemplateBuilder restTemplateBuilder) {
        this.deviceRepository = deviceRepository;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public void updateDevicesInDataBase() {
        final RestTemplate   deviceQuerry;
        final MbedDeviceList deviceList;

        deviceQuerry = restTemplateBuilder.build();

        deviceList = deviceQuerry.getForObject(MbedConfig.API_ACCESS + "/endpoints", MbedDeviceList.class);

        for (final MbedDevice mbedDevice : deviceList) {
            final List<Device> devices;

            devices = deviceRepository.getDevicesByNameIgnoreCase(mbedDevice.getName());

            if (devices == null || devices.isEmpty()) {
                final Device newDevice;
                newDevice = new Device();

                newDevice.setName(mbedDevice.getName());

                logger.info("Created new device: " + newDevice.getName());

                deviceRepository.saveAndFlush(newDevice);
            }
        }
    }
}
