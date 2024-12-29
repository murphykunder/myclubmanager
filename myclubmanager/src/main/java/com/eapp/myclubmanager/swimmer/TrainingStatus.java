package com.eapp.myclubmanager.swimmer;

public enum TrainingStatus {

    NOT_STARTED("not_started", "Training is not started"),
    IN_PROGRESS("in_progress", "Training is in progress"),
    COMPLETE("complete", "Training is complete");

    private final String code;
    private final String description;

    TrainingStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }
}
