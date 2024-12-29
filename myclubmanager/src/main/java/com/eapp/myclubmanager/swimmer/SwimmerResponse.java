package com.eapp.myclubmanager.swimmer;

public class SwimmerResponse {

    private String firstname;
    private String lastname;
    private String email;
    private String trainingStatus;

    public SwimmerResponse() {
    }

    public SwimmerResponse(String firstname, String lastname, String email, String trainingStatus) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.trainingStatus = trainingStatus;
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

    public static class Builder {

        private String firstname;
        private String lastname;
        private String email;
        private String trainingStatus;

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

        public SwimmerResponse createSwimmerResponse() {
            return new SwimmerResponse(firstname, lastname, email, trainingStatus);
        }
    }
}
