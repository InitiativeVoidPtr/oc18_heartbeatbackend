package ecg.backend;

import ecg.backend.controller.MockEntryGenerator;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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

    private final MockEntryGenerator mockEntryGenerator;

    @Autowired
    public Bootstrap(@NotNull final MockEntryGenerator mockEntryGenerator) {
        this.mockEntryGenerator = mockEntryGenerator;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        mockEntryGenerator.startGenerator();
    }
}
