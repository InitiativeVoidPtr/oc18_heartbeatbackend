package ecg.backend.model.mbed;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class AsynchResponse {

    private String id;

    private String status;

    private String payload;

    @JsonAlias("ct")
    private String type;

    @JsonAlias("max-age")
    private String maxAge;

    public String getId() {
        return id;
    }

    public void setId(@NotNull final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb;

        sb = new StringBuilder();

        sb.append("id: ").append(id).append(System.lineSeparator());
        sb.append("status: ").append(status).append(System.lineSeparator());
        sb.append("payload: ").append(payload.substring(0, 50)).append(System.lineSeparator());
        sb.append("type: ").append(type).append(System.lineSeparator());
        sb.append("maxAge: ").append(maxAge).append(System.lineSeparator());

        return sb.toString();
    }
}
