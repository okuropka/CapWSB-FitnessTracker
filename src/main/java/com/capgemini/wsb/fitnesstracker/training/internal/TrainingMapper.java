package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TrainingMapper {

    private final UserMapper userMapper;
    TrainingDto toDto(Training training) {
        
        return new TrainingDto(training.getId(),
                userMapper.toDto(training.getUser()),
                training.getAverageSpeed(),
                training.getDistance(),
                training.getStartTime(),
                training.getEndTime());
    }

    Training toEntity(TrainingDto trainingDto) {
        return new Training(trainingDto.id(),
                userMapper.toDto(trainingDto.user()),
                trainingDto.averageSpeed(),
                trainingDto.distance(),
                trainingDto.startTime(),
                trainingDto.endTime());
    }

}
