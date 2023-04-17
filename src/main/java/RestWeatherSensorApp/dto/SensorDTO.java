package RestWeatherSensorApp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotNull
    @Size(min = 3,max = 30,message = "The name must be at least 3 and not more than 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
