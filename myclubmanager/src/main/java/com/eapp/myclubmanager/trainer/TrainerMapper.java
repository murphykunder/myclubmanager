package com.eapp.myclubmanager.trainer;

import org.springframework.stereotype.Service;

@Service
public class TrainerMapper {
    public Trainer toTrainer(TrainerRequest request) {
        return new Trainer.Builder()
                .setFirstname(request.firstname())
                .setLastname(request.lastname())
                .setEmail(request.email())
                .setPhoneNumber(request.phoneNumber())
                .createTrainer();
    }

    public TrainerResponse toTrainerResponse(Trainer trainer){
        return new TrainerResponse.Builder()
                .setEmail(trainer.getEmail())
                .setFirstname(trainer.getFirstname())
                .setLastname(trainer.getLastname())
                .setPhoneNumber(trainer.getPhoneNumber())
                .setId(trainer.getId())
                .createTrainerResponse();
    }
}
