package com.eapp.myclubmanager.email;

public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account");

    private final String templateName;

    EmailTemplateName(String name){
        this.templateName = name;
    }

    public String getTemplateName() {
        return templateName;
    }
}
