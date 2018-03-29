package ecg.backend.model.mbed;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
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
@JsonIgnoreProperties(value = {"reg-updates", "registrations"})
public class AsyncResponseList {
    @JsonAlias("async-responses")
    private List<AsyncResponse> asyncRespons;

    public List<AsyncResponse> getAsyncRespons() {
        return asyncRespons;
    }

    public void setAsyncRespons(@NotNull final List<AsyncResponse> asyncRespons) {
        this.asyncRespons = asyncRespons;
    }
}
