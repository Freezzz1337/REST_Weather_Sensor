package RestWeatherSensorApp.repositories;

import RestWeatherSensorApp.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
