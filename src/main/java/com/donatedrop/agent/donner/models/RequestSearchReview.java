package com.donatedrop.agent.donner.models;

public class RequestSearchReview {
    private int max;
    private int start;
    private String key;
    private String column;
    private String statusType;
    private String userIdAgent;

    public RequestSearchReview() {
    }

    public RequestSearchReview(int start, int max, String column, String key, String statusType) {
        this.start = start;
        this.max = max;
        this.column = column;
        this.key = key;
        this.statusType = statusType;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getUserIdAgent() {
        return userIdAgent;
    }

    public void setUserIdAgent(String userIdAgent) {
        this.userIdAgent = userIdAgent;
    }

    @Override
    public String toString() {
        return "RequestSearchReview{" +
                "max=" + max +
                ", start=" + start +
                ", key='" + key + '\'' +
                ", column='" + column + '\'' +
                ", statusType='" + statusType + '\'' +
                ", userIdAgent='" + userIdAgent + '\'' +
                '}';
    }
}
