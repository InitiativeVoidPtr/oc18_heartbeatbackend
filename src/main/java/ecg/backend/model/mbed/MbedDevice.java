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

public class MbedDevice {
    private String name;
    private String type;
    private String status;


    public MbedDevice() {
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(@NotNull final String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(@NotNull final String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb;

        sb = new StringBuilder();

        sb.append("Name: ").append(getName()).append(System.lineSeparator());
        sb.append("Type: ").append(getType()).append(System.lineSeparator());
        sb.append("Status: ").append(getStatus());
        return sb.toString();
    }
}
