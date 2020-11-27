package com.donatedrop.post.model;

import com.donatedrop.post.model.PostcommentWithUserInfo;

import java.util.List;

public class PostWithComments {
    private boolean postOwner;
    private Post postBasic;
    private List<PostcommentWithUserInfo> postcommentWithUserInfoList;
    private int commentCount;

    public PostWithComments() {

    }

    public PostWithComments(boolean postOwner, Post postBasic, List<PostcommentWithUserInfo> postcommentWithUserInfoList, int commentCount) {
        this.postOwner = postOwner;
        this.postBasic = postBasic;
        this.postcommentWithUserInfoList = postcommentWithUserInfoList;
        this.commentCount = commentCount;
    }

    public boolean isPostOwner() {
        return postOwner;
    }

    public void setPostOwner(boolean postOwner) {
        this.postOwner = postOwner;
    }

    public Post getPostBasic() {
        return postBasic;
    }

    public void setPostBasic(Post postBasic) {
        this.postBasic = postBasic;
    }

    public List<PostcommentWithUserInfo> getPostcommentWithUserInfoList() {
        return postcommentWithUserInfoList;
    }

    public void setPostcommentWithUserInfoList(List<PostcommentWithUserInfo> postcommentWithUserInfoList) {
        this.postcommentWithUserInfoList = postcommentWithUserInfoList;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "PostWithComments{" +
                "postOwner=" + postOwner +
                ", postBasic=" + postBasic +
                ", postcommentWithUserInfoList=" + postcommentWithUserInfoList +
                ", commentCount=" + commentCount +
                '}';
    }
}
