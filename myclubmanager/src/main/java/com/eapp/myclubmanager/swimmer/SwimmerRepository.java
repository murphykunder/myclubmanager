package com.eapp.myclubmanager.swimmer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SwimmerRepository extends JpaRepository<Swimmer, Long> {

    Optional<Swimmer> findByEmail(String email);

    @Query("""
            SELECT s
            FROM Swimmer s
            WHERE s.trainingStatus = :trainingStatus
            """)
    Page<Swimmer> findAllByTrainingStatus(Pageable pageable, String trainingStatus);
}
