package RestWeatherSensorApp.dto;

import RestWeatherSensorApp.models.Sensor;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;


public class MeasurementDTO {
    @NotNull
    @Range(min = -100,max = 100)
    private double value;
    @NotNull
    private boolean raining;

    @NotNull
    private int sensorId;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

}
