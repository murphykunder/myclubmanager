package com.eapp.myclubmanager.swimmer;

import com.eapp.myclubmanager.team.Team;
import com.eapp.myclubmanager.trainer.Trainer;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Swimmer {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String trainingStatus;
    @ManyToOne()
    @JoinColumn(name = "trainer_id", referencedColumnName = "id", nullable = true)
    private Trainer trainer;
    @ManyToOne()
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = true)
    private Team team;
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

    public Swimmer() {
    }

    public Swimmer(Long id, String firstname, String lastname, String email, String trainingStatus, Trainer trainer, Team team, LocalDateTime createdAt, Long createdBy, LocalDateTime lastModifiedAt, Long lastModifiedBy) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.trainingStatus = trainingStatus;
        this.trainer = trainer;
        this.team = team;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrainingStatus() {
        return trainingStatus;
    }

    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
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
        private String firstname;
        private String lastname;
        private String email;
        private String trainingStatus;
        private LocalDateTime createdAt;
        private Long createdBy;
        private LocalDateTime lastModifiedAt;
        private Long lastModifiedBy;
        private Trainer trainer;
        private Team team;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setTrainingStatus(String trainingStatus) {
            this.trainingStatus = trainingStatus;
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

        public Builder setTrainer(Trainer trainer) {
            this.trainer = trainer;
            return this;
        }

        public Builder setTeam(Team team) {
            this.team = team;
            return this;
        }

        public Swimmer createSwimmer() {
            return new Swimmer(id, firstname, lastname, email, trainingStatus, trainer, team, createdAt, createdBy, lastModifiedAt, lastModifiedBy);
        }
    }
}
