package RestWeatherSensorApp.services;

import RestWeatherSensorApp.models.Sensor;
import RestWeatherSensorApp.repositories.SensorRepository;
import RestWeatherSensorApp.util.SensorCreateException;
import RestWeatherSensorApp.util.SensorWithThisNameAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        Optional<Sensor> optional = sensorRepository.findByName(sensor.getName());
        if (optional.isEmpty()) {
            sensorRepository.save(sensor);
        }else {
            throw new SensorWithThisNameAlreadyExists();
        }
    }



}
