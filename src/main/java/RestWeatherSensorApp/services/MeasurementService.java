package RestWeatherSensorApp.services;

import RestWeatherSensorApp.models.Measurement;
import RestWeatherSensorApp.repositories.MeasurementRepository;
import RestWeatherSensorApp.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Measurement newMeasurement) {
        if (sensorRepository.findByName(newMeasurement.getSensor().getName()).isPresent()) {
            newMeasurement.setMeasurementTime(new Date());
            measurementRepository.save(newMeasurement);
        }else {

        }
    }


}
