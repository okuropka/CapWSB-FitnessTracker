package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    // TODO: implement query
    @Query("select ")
    List<Training> findAllTrainingsByUserID();
}
