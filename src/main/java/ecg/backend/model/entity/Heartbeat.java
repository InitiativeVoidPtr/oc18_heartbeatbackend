package ecg.backend.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
@Entity
public class Heartbeat extends AbstractEntity {

    @ManyToOne
    private Device device;

    private LocalDateTime timeStamp;

    private double value;

    public Device getDevice() {
        return device;
    }

    public void setDevice(@NotNull final Device device) {
        this.device = device;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(@NotNull final LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(@NotNull final double value) {
        this.value = value;
    }
}
