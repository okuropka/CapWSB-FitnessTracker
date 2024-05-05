package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


// public record TrainingDto(Long id, User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) { }

@Data
public class TrainingDto {

    private Long id;

    private User user;

    private Date startTime;

    private Date endTime;

    private ActivityType activityType;

    private double distance;

    private double averageSpeed;
}