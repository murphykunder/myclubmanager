package com.eapp.myclubmanager.team;

import com.eapp.myclubmanager.swimmer.Swimmer;
import com.eapp.myclubmanager.trainer.Trainer;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Team {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Swimmer> swimmers;
    @OneToMany(mappedBy = "team")
    private List<Trainer> trainers;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private Long createdBy;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedAt;
    @LastModifiedBy
    @Column(insertable = false)
    private Long lastModifiedBy;

    public Team() {
    }

    public Team(Long id, String name, List<Swimmer> swimmers, List<Trainer> trainers, LocalDateTime createdAt, Long createdBy, LocalDateTime lastModifiedAt, Long lastModifiedBy) {
        this.id = id;
        this.name = name;
        this.swimmers = swimmers;
        this.trainers = trainers;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.lastModifiedAt = lastModifiedAt;
        this.lastModifiedBy = lastModifiedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Swimmer> getSwimmers() {
        return swimmers;
    }

    public void setSwimmers(List<Swimmer> swimmers) {
        this.swimmers = swimmers;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public static class Builder {

        private Long id;
        private String name;
        private List<Swimmer> swimmers;
        private List<Trainer> trainers;
        private LocalDateTime createdAt;
        private Long createdBy;
        private LocalDateTime lastModifiedAt;
        private Long lastModifiedBy;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSwimmers(List<Swimmer> swimmers) {
            this.swimmers = swimmers;
            return this;
        }

        public Builder setTrainers(List<Trainer> trainers) {
            this.trainers = trainers;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setCreatedBy(Long createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder setLastModifiedAt(LocalDateTime lastModifiedAt) {
            this.lastModifiedAt = lastModifiedAt;
            return this;
        }

        public Builder setLastModifiedBy(Long lastModifiedBy) {
            this.lastModifiedBy = lastModifiedBy;
            return this;
        }

        public Team createTeam() {
            return new Team(id, name, swimmers, trainers, createdAt, createdBy, lastModifiedAt, lastModifiedBy);
        }
    }
}
