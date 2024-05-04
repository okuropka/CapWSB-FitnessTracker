package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import java.util.Date;


public record TrainingDto(Long id, User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {

}
