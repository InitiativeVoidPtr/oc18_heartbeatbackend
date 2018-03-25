package ecg.backend.model.mbed;

import org.jetbrains.annotations.NotNull;

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
public class CallbackRegistration {

    private String url;

    public CallbackRegistration() {
    }

    public CallbackRegistration(@NotNull final String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(@NotNull final String url) {
        this.url = url;
    }
}
