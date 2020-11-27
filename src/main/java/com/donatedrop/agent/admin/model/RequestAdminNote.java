package com.donatedrop.agent.admin.model;

public class RequestAdminNote {
    private String requestId;
    private String adminNote;

    public RequestAdminNote() {
    }

    public RequestAdminNote(String requestId, String adminNote) {
        this.requestId = requestId;
        this.adminNote = adminNote;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public void setAdminNote(String adminNote) {
        this.adminNote = adminNote;
    }
}
