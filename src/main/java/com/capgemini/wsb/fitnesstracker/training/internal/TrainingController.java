package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    @Lazy
    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    @GetMapping     // http://localhost:8080/v1/trainings
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/ofUser/{userId}")     // http://localhost:8080/v1/trainings/ofUser/1
    public List<TrainingDto> getTrainingsByUserId(@PathVariable long userId) {
        return trainingService.getAllTrainingsForDedicatedUser(userId).stream().map(trainingMapper::toDto).toList();
    }

    // objaśnienie, jak wysłać JSON w Postmanie
    // https://dev.to/serenepine/how-to-send-json-data-in-postman-90a

    // '{null, "imie", "nazwisko", "1999-09-09", "nowy.mail@poczta.ua}' "localhost:8080/v1/users/addUser"
    // TODO: post request
    @PostMapping
    public Training addTraining(@RequestBody TrainingDto trainingDto) {
        return trainingService.createTraining(trainingMapper.toEntity(trainingDto));
    }

    // TODO: fix error "Could not convert 'java.time.LocalDate' to 'java.sql.Timestamp' using 'org.hibernate.type.descriptor.java.JdbcTimestampJavaType' to wrap"
    @GetMapping("/finishedTrainings")       // http://localhost:8080/v1/trainings/finishedTrainings
    public List<TrainingDto> getFinishedTrainings() {
        return trainingService.findAllFinishedTrainings().stream().map(trainingMapper::toDto).toList();
    }

    // TODO: fix error "Could not convert 'java.time.LocalDate' to 'java.sql.Timestamp' using 'org.hibernate.type.descriptor.java.JdbcTimestampJavaType' to wrap"
    @GetMapping("/finishedTrainings/beforeDate/{date}")      // http://localhost:8080/v1/trainings/finishedTrainings/beforeDate/2024-01-13
    public List<TrainingDto> getFinishedTrainingsBefore(@PathVariable Date date) {
        return trainingService.findAllFinishedTrainingsBefore(date).stream().map(trainingMapper::toDto).toList();
    }

    // TODO: Get this right
    // https://www.baeldung.com/spring-enum-request-param
    @GetMapping("/byActivity")   // http://localhost:8080/v1/trainings/byActivity?
    public List<TrainingDto> getTrainingsByActivity(@RequestParam ActivityType activityType) {
        // ActivityType.RUNNING;
        return trainingService.findALlTrainingsByActivity(activityType).stream().map(trainingMapper::toDto).toList();
    }

    // TODO: fix error
    @PatchMapping("/update/byAverageSpeed/{id}")   // http://localhost:8080/v1/trainings/update/byAverageSpeed/1?averageSpeed=8.5
    public void updateTraining(@PathVariable Long id, @RequestParam double averageSpeed ) {
        Optional<Training> optionalTraining = trainingService.getTraining(id);
        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get(); // WARN 8220 --- [nio-8080-exec-4] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'GET' is not supported]
            training.setAverageSpeed(averageSpeed);
            trainingService.updateTraining(training);
        }
        else { throw new TrainingNotFoundException(id); }
    }

}