package com.donatedrop.agent.admin.model;

public class RequestPersonalNote {
    private String requestId;
    private String personalNote;

    public RequestPersonalNote() {
    }

    public RequestPersonalNote(String requestId, String adminNote) {
        this.requestId = requestId;
        this.personalNote = adminNote;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPersonalNote() {
        return personalNote;
    }

    public void setPersonalNote(String personalNote) {
        this.personalNote = personalNote;
    }
}
