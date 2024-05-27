package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class TrainingServiceImpl implements TrainingService, TrainingProvider {


    //private final TrainingProvider trainingProvider;
    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    //@Override
    //public void deleteAllTrainingsByUser(final Long id) { trainingRepository.deleteAllTrainingsByUserId(id); }

    @Override
    public List<Training> getAllTrainings() { return trainingRepository.findAll(); }
    // dlaczego korzystając z trainingProvider wychodzi błąd cyklicznej zależności...? (TrainingController <--> TrainingServiceImpl)
    //public List<Training> getAllTrainings(){ return trainingProvider.getAllTrainings(); }
    @Override
    public List<Training> getAllTrainingsForDedicatedUser(long userId) {
        return trainingRepository.findAllTrainingsByUserID(userId);
    }

    @Override
    public Training createTraining(Training training){
        log.info("Creating Training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, update is not permitted!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> findAllFinishedTrainings(){
        LocalDate today = LocalDate.now();
        return trainingRepository.findAllFinishedTrainings(today);
    }

    @Override
    public List<Training> findAllFinishedTrainingsBefore(Date date){
        //return trainingRepository.findAllFinishedTrainings(date);
        System.out.println(date);
        return trainingRepository.findAllFinishedTrainingsBefore(date);
    }

    @Override
    public List<Training> findALlTrainingsByActivity(ActivityType activity){
        return trainingRepository.findAllTrainingsByActivity(activity);
    }

    @Override
    public Training updateTraining(Training training){
        log.info("Updating Training {}", training);
        return trainingRepository.save(training);
    }
}