/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.post.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author Dell
 */
@Entity
public class PostComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long commentID;

    @Column(name = "comment_date")
    private String date;

    @Lob
    @Column(name = "comment_details")
    private String commentDetails;

    @Column(name = "comment_user_id")
    private String userID;

    public PostComment() {
    }

    public PostComment(String commentDetails, String userID) {
        this.date = date;
        this.commentDetails = commentDetails;
        this.userID = userID;
    }

    public PostComment(String date, String commentDetails, String userID) {
        this.date = date;
        this.commentDetails = commentDetails;
        this.userID = userID;
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "PostComment{" + "commentID=" + commentID + ", date=" + date + ", commentDetails=" + commentDetails + ", userID=" + userID + '}';
    }

}
