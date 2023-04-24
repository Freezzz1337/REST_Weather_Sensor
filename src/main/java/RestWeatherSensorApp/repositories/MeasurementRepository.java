package RestWeatherSensorApp.repositories;

import RestWeatherSensorApp.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    List<Measurement> findAllByRainingTrue();
}
