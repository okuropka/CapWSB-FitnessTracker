package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingService, TrainingProvider {


    private final TrainingProvider trainingProvider;
    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> getAllTrainings(){
        return trainingProvider.getAllTrainings();
    }

    // TODO: implement query
    @Override
    public List<Training> getAllTrainingsForDedicatedUser(long userId) {
        return trainingRepository.findAllTrainingsByUserID();
    }

    @Override
    public Training createTraining(User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed){
        return trainingRepository.save(new Training(user, startTime, endTime, activityType, distance, averageSpeed));
    }

}