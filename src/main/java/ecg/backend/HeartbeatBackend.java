package ecg.backend;

import ecg.backend.controller.MockEntryGenerator;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@SpringBootApplication
@EnableAutoConfiguration
public class HeartbeatBackend {


    public static void main(String[] args) {
        SpringApplication.run(HeartbeatBackend.class);
    }
}
