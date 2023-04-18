package RestWeatherSensorApp.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty
    @Size(min = 3,max = 30,message = "The name must be at least 3 and not more than 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
