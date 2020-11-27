package com.donatedrop.post.model;

public class CommentDelete {
    private String commentID;
    private String postID;
    private String commentUserID;

    public CommentDelete(String commentID, String postID, String commentUserID) {
        this.commentID = commentID;
        this.postID = postID;
        this.commentUserID = commentUserID;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getCommentUserID() {
        return commentUserID;
    }

    public void setCommentUserID(String commentUserID) {
        this.commentUserID = commentUserID;
    }

    @Override
    public String toString() {
        return "CommentDelete{" +
                "commentID='" + commentID + '\'' +
                ", postID='" + postID + '\'' +
                ", commentUserID='" + commentUserID + '\'' +
                '}';
    }
}
