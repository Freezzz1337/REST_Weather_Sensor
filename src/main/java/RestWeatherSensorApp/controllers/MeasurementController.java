package RestWeatherSensorApp.controllers;

import RestWeatherSensorApp.dto.MeasurementDTO;
import RestWeatherSensorApp.models.Measurement;
import RestWeatherSensorApp.services.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


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
        if (bindingResult.hasErrors()){

        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
