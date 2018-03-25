package ecg.backend.model.mbed;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class AsyncResponseList {
    @JsonAlias("async-responses")
    private List<AsyncResponse> asyncRespons;

    @JsonAlias("reg-updates")
    private String regUpdates;

    public List<AsyncResponse> getAsyncRespons() {
        return asyncRespons;
    }

    public void setAsyncRespons(@NotNull final List<AsyncResponse> asyncRespons) {
        this.asyncRespons = asyncRespons;
    }

    public String getRegUpdates() {
        return regUpdates;
    }

    public void setRegUpdates(@NotNull final String regUpdates) {
        this.regUpdates = regUpdates;
    }
}
