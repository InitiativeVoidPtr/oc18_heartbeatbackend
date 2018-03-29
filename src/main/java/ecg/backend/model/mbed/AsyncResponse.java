package ecg.backend.model.mbed;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Base64;
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
@JsonIgnoreProperties(value = {"registrations-expired"})
public class AsyncResponse {

    private String id;

    private String status;

    private String payload;

    @JsonAlias("ct")
    private String type;

    @JsonAlias("max-age")
    private String maxAge;

    private String error;

    public String getId() {
        return id;
    }

    public void setId(@NotNull final String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(@NotNull final String status) {
        this.status = status;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(@NotNull final String payload) {
        this.payload = new String(Base64.getDecoder().decode(payload));
    }

    public String getType() {
        return type;
    }

    public void setType(@NotNull final String type) {
        this.type = type;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(@NotNull final String maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public String toString() {
        StringBuilder sb;

        sb = new StringBuilder();

        sb.append("id: ").append(getId()).append(System.lineSeparator());
        sb.append("status: ").append(getStatus()).append(System.lineSeparator());
        sb.append("payload: ").append(getPayload().substring(0, 50)).append(System.lineSeparator());
        sb.append("type: ").append(getType()).append(System.lineSeparator());
        sb.append("maxAge: ").append(getMaxAge()).append(System.lineSeparator());
        sb.append("error: ").append(getError()).append(System.lineSeparator());

        return sb.toString();
    }

    public String getError() {
        return error;
    }

    public void setError(@NotNull final String error) {
        this.error = error;
    }
}
