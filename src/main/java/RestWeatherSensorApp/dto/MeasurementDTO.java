package RestWeatherSensorApp.dto;

import RestWeatherSensorApp.models.Sensor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.util.Date;

public class MeasurementDTO {
    @NotEmpty
    @Size(min = -100,max = 100)
    private double value;

    @NotEmpty
    private boolean raining;

    @NotEmpty
    private Sensor sensor;

    @Temporal(TemporalType.TIMESTAMP)
    Date measurementTime;

    public Date getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Date measurementTime) {
        this.measurementTime = measurementTime;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
