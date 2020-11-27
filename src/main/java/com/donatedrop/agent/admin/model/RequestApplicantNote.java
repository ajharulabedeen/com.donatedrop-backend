package com.donatedrop.agent.admin.model;

public class RequestApplicantNote {
    private String requestId;
    private String applicantNote;

    public RequestApplicantNote() {
    }

    public RequestApplicantNote(String requestId, String adminNote) {
        this.requestId = requestId;
        this.applicantNote = adminNote;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getApplicantNote() {
        return applicantNote;
    }

    public void setApplicantNote(String applicantNote) {
        this.applicantNote = applicantNote;
    }
}
