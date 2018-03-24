package ecg.backend.model.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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
public class Device extends AbstractEntity {

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
    private List<Heartbeat> heartbeats;

    public Device() {
    }

    public Device(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
