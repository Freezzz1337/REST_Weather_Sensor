package RestWeatherSensorApp.models;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Range(min = -100, max = 100)
    @Column(name = "value")
    private double value;

    @NotNull
    @Column(name = "raining")
    private boolean raining;

    @NotEmpty
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "sensor", referencedColumnName = "id")
    private Sensor sensor;

    @Column(name = "measurement_time")
    @Temporal(TemporalType.TIMESTAMP)
    Date measurementTime;

    public Measurement() {
    }

    public Measurement(int id, double value, boolean raining, Sensor sensor) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Date measurementTime) {
        this.measurementTime = measurementTime;
    }
}
