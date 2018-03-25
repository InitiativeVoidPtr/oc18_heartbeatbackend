package ecg.backend;

import ecg.backend.controller.MbedDeviceController;
import ecg.backend.controller.MockEntryGenerator;
import ecg.backend.model.entity.Device;
import ecg.backend.model.mbed.CallbackRegistration;
import ecg.backend.model.mbed.MbedDevice;
import ecg.backend.model.repository.DeviceRepository;
import java.net.URI;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
    private final WebClient            webClient;


    @Autowired
    public Bootstrap(@NotNull final DeviceRepository deviceRepository,
                     @NotNull final MockEntryGenerator mockEntryGenerator,
                     @NotNull final MbedDeviceController mbedDeviceController,
                     @NotNull @Qualifier("mbedTemplateBuilder") final RestTemplateBuilder restTemplateBuilder,
                     @NotNull final WebClient webClient) {
        this.deviceRepository = deviceRepository;
        this.mockEntryGenerator = mockEntryGenerator;

        this.mbedDeviceController = mbedDeviceController;

        this.restTemplateBuilder = restTemplateBuilder;
        this.webClient = webClient;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        mbedDeviceController.updateDevicesInDataBase();
        printMbedDeviceInfo();
    }


    private void printMbedDeviceInfo() {
        for (final Device device : deviceRepository.findAll()) {
            final RestTemplate template;
            template = restTemplateBuilder.build();

            template.put(URI.create(API_ACCESS + "/v2/notification/callback"),
                         new CallbackRegistration("http://185.162.248.93/callback"));

//            System.out.println(template.getForObject(API_ACCESS + "/endpoints/" + device.getName() + "/3", String.class));
//            System.out.println(template.getForObject(API_ACCESS + "/endpoints/" + device.getName() + "/3/0", String.class));
//            System.out.println(template.getForObject(API_ACCESS + "/endpoints/" + device.getName() + "/3341", String.class));
            while(true){
                System.out.println(template.getForObject(API_ACCESS + "/endpoints/" + device.getName() + "/3341/0/5527", String.class));
            }
        }
    }


    public static class DevicesClass extends ArrayList<MbedDevice> {
        public DevicesClass() {
        }
    }
}
