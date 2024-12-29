package com.eapp.myclubmanager.swimmer;

import com.eapp.myclubmanager.exception.OperationNotPermittedException;
import com.eapp.myclubmanager.trainer.Trainer;
import com.eapp.myclubmanager.trainer.TrainerRepository;
import io.jsonwebtoken.lang.Objects;
import org.springframework.stereotype.Service;

@Service
public class SwimmerMapper {
    private final TrainerRepository trainerRepository;

    public SwimmerMapper(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Swimmer toSwimmer(SwimmerRequest request) {
        Trainer trainer = null;
        if(request.trainerId() != null & !Objects.isEmpty(request.trainerId())){
            trainer = trainerRepository.findById(request.trainerId()).orElseThrow(() -> new OperationNotPermittedException("Trainer does not exists."));
        }
        return new Swimmer.Builder()
                .setId(request.id())
                .setEmail(request.email())
                .setFirstname(request.firstname())
                .setLastname(request.lastname())
                .setTrainingStatus(request.trainingStatus())
                .setTrainer(trainer)
                .createSwimmer();
    }

    public SwimmerResponse toSwimmerResponse(Swimmer swimmer) {
        return new SwimmerResponse.Builder()
                .setFirstname(swimmer.getFirstname())
                .setLastname(swimmer.getLastname())
                .setEmail(swimmer.getEmail())
                .setTrainingStatus(swimmer.getTrainingStatus())
                .createSwimmerResponse();
    }
}
