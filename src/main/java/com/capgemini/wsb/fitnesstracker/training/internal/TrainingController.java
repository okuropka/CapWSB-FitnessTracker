package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsByUserId(@PathVariable long userId) {
        return trainingService.getAllTrainingsForDedicatedUser(userId).stream().map(trainingMapper::toDto).toList();
    }

    @PostMapping
    public Training addTraining(@RequestBody TrainingDto trainingDto) {
        return trainingService.createTraining(trainingMapper.toEntity(trainingDto));
    }

    @GetMapping("/finished_trainings")
    public List<TrainingDto> getFinishedTrainings() {
        return trainingService.findAllFinishedTrainings().stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/finished_trainings/{date}")
    public List<TrainingDto> getFinishedTrainingsBefore(@PathVariable LocalDate date) {
        return trainingService.findAllFinishedTrainingsBefore(date).stream().map(trainingMapper::toDto).toList();
    }

    @GetMapping("/{activity}")
    public List<TrainingDto> getTrainingsByActivity(@PathVariable ActivityType activity) {
        return trainingService.findALlTrainingsByActivity(activity).stream().map(trainingMapper::toDto).toList();
    }
}