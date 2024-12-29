package com.eapp.myclubmanager.trainer;

import com.eapp.myclubmanager.common.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerMapper trainerMapper;
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerMapper trainerMapper, TrainerRepository trainerRepository) {
        this.trainerMapper = trainerMapper;
        this.trainerRepository = trainerRepository;
    }

    public void save(TrainerRequest request) {
        Trainer trainer = trainerMapper.toTrainer(request);
        trainerRepository.save(trainer);
    }

    public PageResponse<TrainerResponse> findAllTrainers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Trainer> trainers = trainerRepository.findAll(pageable);
        List<TrainerResponse> trainerResponseList = trainers.stream().map(trainerMapper::toTrainerResponse).toList();
        return new PageResponse<>(
                trainerResponseList,
                trainers.getNumber(),
                trainers.getSize(),
                trainers.getTotalElements(),
                trainers.getTotalPages(),
                trainers.isFirst(),
                trainers.isLast()
        );
    }
}
