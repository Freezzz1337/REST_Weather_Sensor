package RestWeatherSensorApp.repositories;

import RestWeatherSensorApp.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
}
