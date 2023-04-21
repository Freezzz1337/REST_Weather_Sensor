package RestWeatherSensorApp.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Sensor")
public class Sensor {
    @Id
    @Column(name = "name")
    @NotEmpty
    @Size(min = 3, max = 30, message = "The name must be at least 3 and not more than 30 characters")
    private String name;


}
