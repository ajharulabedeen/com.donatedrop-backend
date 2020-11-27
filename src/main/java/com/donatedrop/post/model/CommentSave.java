package com.donatedrop.post.model;

public class CommentSave {
    private String commentContent;
    private String postID;

    public CommentSave(String commentContent, String postID) {
        this.commentContent = commentContent;
        this.postID = postID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    @Override
    public String toString() {
        return "CommentSave{" +
                "commentContent='" + commentContent + '\'' +
                ", postID='" + postID + '\'' +
                '}';
    }
}
