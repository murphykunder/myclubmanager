package com.eapp.myclubmanager.handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    private String businessErrorCode;
    private String businessErrorDescription;
    private Set<String> validationErrors;
    private String error;

    public ExceptionResponse(String businessErrorCode, String businessErrorDescription, Set<String> validationErrors, String error) {
        this.businessErrorCode = businessErrorCode;
        this.businessErrorDescription = businessErrorDescription;
        this.validationErrors = validationErrors;
        this.error = error;
    }

    public ExceptionResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getBusinessErrorCode() {
        return businessErrorCode;
    }

    public void setBusinessErrorCode(String businessErrorCode) {
        this.businessErrorCode = businessErrorCode;
    }

    public String getBusinessErrorDescription() {
        return businessErrorDescription;
    }

    public void setBusinessErrorDescription(String businessErrorDescription) {
        this.businessErrorDescription = businessErrorDescription;
    }

    public Set<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public static class Builder {

        private String businessErrorCode;
        private String businessErrorDescription;
        private Set<String> validationErrors;
        private String error;

        public Builder setBusinessErrorCode(String businessErrorCode) {
            this.businessErrorCode = businessErrorCode;
            return this;
        }

        public Builder setBusinessErrorDescription(String businessErrorDescription) {
            this.businessErrorDescription = businessErrorDescription;
            return this;
        }

        public Builder setValidationErrors(Set<String> validationErrors) {
            this.validationErrors = validationErrors;
            return this;
        }

        public Builder setError(String error) {
            this.error = error;
            return this;
        }

        public ExceptionResponse createExceptionResponse() {
            return new ExceptionResponse(businessErrorCode, businessErrorDescription, validationErrors, error);
        }
    }
}
