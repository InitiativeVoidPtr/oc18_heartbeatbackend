package ecg.backend.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @PutMapping("/callback")
    public void callback(@RequestBody final String string) {
        logger.info(string);
    }

}
