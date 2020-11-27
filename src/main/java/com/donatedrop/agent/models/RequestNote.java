package com.donatedrop.agent.models;

import com.donatedrop.agent.admin.model.*;

public class RequestNote {
    private String requestId;
    private String note;

    public RequestNote() {
    }

    public RequestNote(String requestId, String adminNote) {
        this.requestId = requestId;
        this.note = adminNote;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
