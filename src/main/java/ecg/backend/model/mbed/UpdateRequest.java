package ecg.backend.model.mbed;

import com.fasterxml.jackson.annotation.JsonAlias;

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
public class UpdateRequest {

    @JsonAlias("reg-updates")
    private String endpoint;
}
