package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;


public record SimpleUserDto(@Nullable Long Id, String firstName, String lastName) {

}
