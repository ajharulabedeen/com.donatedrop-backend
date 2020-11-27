package com.donatedrop.history;

public class RequestSearch {
    String userID;
    String column;
    String key;
    int start;
    int perPage;

    public RequestSearch() {
    }

    public RequestSearch(String userID, String column, String key, int start, int perPage) {
        this.userID = userID;
        this.column = column;
        this.key = key;
        this.start = start;
        this.perPage = perPage;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
