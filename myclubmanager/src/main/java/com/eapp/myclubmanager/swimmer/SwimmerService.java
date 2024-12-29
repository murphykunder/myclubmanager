package com.eapp.myclubmanager.swimmer;

import com.eapp.myclubmanager.common.PageResponse;
import com.eapp.myclubmanager.exception.OperationNotPermittedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SwimmerService {

    private final SwimmerRepository swimmerRepository;
    private final SwimmerMapper swimmerMapper;

    public SwimmerService(SwimmerRepository swimmerRepository, SwimmerMapper swimmerMapper) {
        this.swimmerRepository = swimmerRepository;
        this.swimmerMapper = swimmerMapper;
    }


    public void save(SwimmerRequest request) {
        Swimmer swimmer = swimmerMapper.toSwimmer(request);
        swimmerRepository.save(swimmer);
    }

    public PageResponse<SwimmerResponse> findAllSwimmers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Swimmer> swimmers = swimmerRepository.findAll(pageable);
        List<SwimmerResponse> swimmerResponse = swimmers.stream().map(swimmerMapper::toSwimmerResponse).toList();
        return new PageResponse<>(
                swimmerResponse,
                swimmers.getNumber(),
                swimmers.getSize(),
                swimmers.getTotalElements(),
                swimmers.getTotalPages(),
                swimmers.isFirst(),
                swimmers.isLast()
        );
    }

}
