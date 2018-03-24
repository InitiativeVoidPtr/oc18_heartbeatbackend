package ecg.backend.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
@MappedSuperclass
public abstract class AbstractEntity {

    @GenericGenerator(
            name = "IDSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "idSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )

    @Id
    @GeneratedValue(generator = "IDSequenceGenerator")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(@NotNull final Long id) {
        this.id = id;
    }


    public boolean isPersistent(){
        return getId() != null && getId() > 0;
    }
}
