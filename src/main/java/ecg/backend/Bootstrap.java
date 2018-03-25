package ecg.backend;

import ecg.backend.controller.MbedDeviceController;
import ecg.backend.controller.MockEntryGenerator;
import ecg.backend.model.entity.Device;
import ecg.backend.model.mbed.MbedDevice;
import ecg.backend.model.repository.DeviceRepository;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static ecg.backend.config.MbedConfig.API_ACCESS;


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

@Component
public class Bootstrap implements ApplicationListener<ApplicationReadyEvent> {

    private final DeviceRepository     deviceRepository;
    private final MockEntryGenerator   mockEntryGenerator;
    private final MbedDeviceController mbedDeviceController;
    private final RestTemplateBuilder  restTemplateBuilder;

    private final TaskScheduler taskScheduler;

    @Autowired
    public Bootstrap(@NotNull final DeviceRepository deviceRepository,
                     @NotNull final MockEntryGenerator mockEntryGenerator,
                     @NotNull final MbedDeviceController mbedDeviceController,
                     @NotNull @Qualifier("mbedTemplateBuilder") final RestTemplateBuilder restTemplateBuilder) {
        this.deviceRepository = deviceRepository;
        this.mockEntryGenerator = mockEntryGenerator;

        this.mbedDeviceController = mbedDeviceController;
        this.restTemplateBuilder = restTemplateBuilder;

        this.taskScheduler = new DefaultManagedTaskScheduler();


    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        mbedDeviceController.updateDevicesInDataBase();
//        restTemplateBuilder.build()
//                           .put(URI.create(API_ACCESS + "/v2/notification/callback"), new CallbackRegistration("http://185.162.248.93/callback"));
//        taskScheduler.scheduleAtFixedRate(this::printMbedDeviceInfo, Duration.of(1000, ChronoUnit.MILLIS));
        printMbedDeviceInfo();
    }


    private void printMbedDeviceInfo() {
        for (final Device device : deviceRepository.findAll()) {
            final RestTemplate template;
            template = restTemplateBuilder.build();

//            System.out.println(template.getForObject(API_ACCESS + "/endpoints/" + device.getName() + "/3", String.class));
//            System.out.println(template.getForObject(API_ACCESS + "/endpoints/" + device.getName() + "/3/0", String.class));
//            System.out.println(template.getForObject(API_ACCESS + "/endpoints/" + device.getName() + "/3341", String.class));

            System.out.println(template.getForObject(API_ACCESS + "/endpoints/" + device.getName() + "/3341/0/5527", String.class));
        }
    }


    public static class DevicesClass extends ArrayList<MbedDevice> {
        public DevicesClass() {
        }
    }
}
