package RestWeatherSensorApp.controllers;

import RestWeatherSensorApp.dto.MeasurementDTO;
import RestWeatherSensorApp.models.Measurement;
import RestWeatherSensorApp.services.MeasurementService;
import RestWeatherSensorApp.util.MeasurementCreateException;
import RestWeatherSensorApp.util.SensorAndMeasurementErrorResponse;
import RestWeatherSensorApp.util.SensorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    //http://localhost:8080/measurements/add

//    {
//        "value":24.7,
//            "raining":false,
//            "sensor":{
//        "name":"first sensor"
//    }
//    }

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> measuresAdd(@RequestBody @Valid MeasurementDTO measurementDTO,
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

            throw new MeasurementCreateException(errorMsg.toString());
        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<Measurement> getMeasurements(){
        return measurementService.getAll();
    }


    @ExceptionHandler
    private ResponseEntity<SensorAndMeasurementErrorResponse> handleException(SensorNotFoundException ex) {
        SensorAndMeasurementErrorResponse errorResponse = new SensorAndMeasurementErrorResponse(
                "Sensor with this name not exist",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<SensorAndMeasurementErrorResponse> handleException(MeasurementCreateException ex) {
        SensorAndMeasurementErrorResponse errorResponse = new SensorAndMeasurementErrorResponse(
                "Invalid values!",
                System.currentTimeMillis()

        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
