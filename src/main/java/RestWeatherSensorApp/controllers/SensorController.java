package RestWeatherSensorApp.controllers;

import RestWeatherSensorApp.dto.SensorDTO;
import RestWeatherSensorApp.models.Sensor;
import RestWeatherSensorApp.services.SensorService;
import RestWeatherSensorApp.util.SensorCreateException;
import RestWeatherSensorApp.util.SensorAndMeasurementErrorResponse;
import RestWeatherSensorApp.util.SensorWithThisNameAlreadyExists;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/sensor/registration")
    public ResponseEntity<HttpStatus> sensorRegistration(@RequestBody @Valid SensorDTO sensorDTO,
                                                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            throw new SensorCreateException(errorMsg.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorAndMeasurementErrorResponse> handleException(SensorCreateException ex) {
        SensorAndMeasurementErrorResponse errorResponse = new SensorAndMeasurementErrorResponse(
                "Invalid values!" +
                        " The name must be at least 3 and not more than 30 characters" +
                        " The name shouldn't be empty",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<SensorAndMeasurementErrorResponse> handleException(SensorWithThisNameAlreadyExists ex){
        SensorAndMeasurementErrorResponse errorResponse = new SensorAndMeasurementErrorResponse(
                "Sensor with this name already exists!",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
