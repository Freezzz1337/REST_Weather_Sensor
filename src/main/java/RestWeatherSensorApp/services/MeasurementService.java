package RestWeatherSensorApp.services;

import RestWeatherSensorApp.models.Measurement;
import RestWeatherSensorApp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void save(Measurement newMeasurement){
        newMeasurement.setMeasurementTime(new Date());
        measurementRepository.save(newMeasurement);
    }


}
