package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getAverageSpeed(),
                training.getDistance());
    }

    Training toEntity(TrainingDto trainingDto) {
        return new Training(
                trainingDto.getUser(),
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed());
    }

}
