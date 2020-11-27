package com.donatedrop.agent.models;

public class RequestReviewRequest {
    private String requestID;
    private String value;

    public RequestReviewRequest() {
    }

    public RequestReviewRequest(String requestID, String value) {
        this.requestID = requestID;
        this.value = value;
    }


    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RequestReviewRequest{" +
                "requestID='" + requestID + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
