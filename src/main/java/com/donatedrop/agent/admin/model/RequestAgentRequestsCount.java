package com.donatedrop.agent.admin.model;

public class RequestAgentRequestsCount {
    String column;
    String key;
    String statusType;

    public RequestAgentRequestsCount(String column) {
    }

    public RequestAgentRequestsCount(String column, String key, String statusType) {
        this.column = column;
        this.key = key;
        this.statusType = statusType;
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
}
