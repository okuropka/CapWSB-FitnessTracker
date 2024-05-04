package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingService, TrainingProvider {


    private final TrainingRepository trainingProvider;
    private final TrainingRepository trainingRepository;

    @Override
    public Optional<User> getTraining(long trainingId){
        throw new UnsupportedOperationException("not finished yet");
    }
    @Override
    public List<Training> getAllTrainingsForDedicatedUser(long userId) {
        return trainingRepository;
    }

    @Override
    public Training createTraining(User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed){
        Training out = new Training(user, startTime, endTime, activityType, distance, averageSpeed);
        return out.save(out);
    }
}