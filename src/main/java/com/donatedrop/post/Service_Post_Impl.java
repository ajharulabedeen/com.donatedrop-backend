package com.donatedrop.post;

import com.donatedrop.post.model.*;
import com.donatedrop.util.StringUtil;
import com.donatedrop.util.Utils;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.Comment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Service_Post_Impl implements Service_Post_I {

    @Autowired
    Dao_Post_I dao_post_i;

    @Override
    public Map<String, String> savePost(Post post) {
        return dao_post_i.savePost(post);
    }

    @Override
    public Map<String, String> updatePost(Post post) {
        Post p = dao_post_i.findPostByUserIDNoComment(post.getPostUserID(), post.getPostID().toString());
        Map<String, String> status = new HashMap<>();
        if (p != null) {
            return dao_post_i.updatePost(post);
        } else {
            status.put(StringUtil.STATUS, StringUtil.FAIL);
            status.put(StringUtil.MESSAGE, StringUtil.UNAUTHERIZED);
        }
        return status;
    }

    @Override
    public Post findPostByUserIDNoComment(String userID, String postID) {
        return dao_post_i.findPostByUserIDNoComment(userID, postID);
    }

    @Override
    public Post findOnePostByID(String id) {
        return dao_post_i.findOnePostByID(id);
    }

    @Override
    public Map<String, String> deletePost(String userID, String postID) {
        Post p = dao_post_i.findPostByUserIDNoComment(userID, postID);
        Map<String, String> status = new HashMap<>();
        if (p != null) {
            return dao_post_i.deletePost(postID);
        } else {
            status.put(StringUtil.STATUS, StringUtil.FAIL);
            status.put(StringUtil.MESSAGE, StringUtil.UNAUTHERIZED);
        }
        return status;
    }

    @Override
    public Post findOnePostByIdWithComments(String postID) {
        return dao_post_i.findPostWithComments(postID);
    }

    @Override
    public Map<String, String> saveComment(PostComment postComment, String postID) {
        return dao_post_i.saveComment(postComment, postID);
    }

    @Override
    public PostComment findOneComment(String postID, String userID, String commentID) {
        return dao_post_i.findOneComment(postID, userID, commentID);
    }

    @Override
    public Map<String, String> updatePostComment(PostComment postComment, String postID) {
        Map<String, String> status = new HashMap<>();
        PostComment pc = dao_post_i.findOneComment(postID, postComment.getUserID(), postComment.getCommentID().toString());
        if (pc != null) {
            return dao_post_i.updatePostComment(postComment);
        } else {
            status.put(StringUtil.STATUS, StringUtil.FAIL);
            status.put(StringUtil.MESSAGE, StringUtil.UNAUTHERIZED);
        }
        return status;
    }

    @Override
    public Map<String, String> deletePostComment(String postID, PostComment postCommentOld) {
        PostComment postComment = dao_post_i.findOneComment(postID, postCommentOld.getUserID(), postCommentOld.getCommentID().toString());
        Map<String, String> status = new HashMap<>();
        if (postComment != null) {
            return dao_post_i.deletePostComment(postCommentOld);
        } else {
            status.put(StringUtil.STATUS, StringUtil.FAIL);
            status.put(StringUtil.MESSAGE, StringUtil.UNAUTHERIZED);
        }
        return status;
    }

    @Override
    public List<Post> getAllPostsByAnUser(MyPostSearch postSearch) {
        return dao_post_i.getAllPostsByAnUser(postSearch);
    }

    @Override
    public String countAllPostsByAnUser(MyPostSearch postSearch) {
        return dao_post_i.countAllPostsByAnUser(postSearch);
    }

    @Override
    public List<Post> getAllPostsByAnUserWithinDate(MyPostSearch postSearch) {
        return dao_post_i.getAllPostsByAnUserWithinDate(postSearch);
    }

    @Override
    public String countAllPostsByAnUserWithinDate(MyPostSearch postSearch) {
        return dao_post_i.countAllPostsByAnUserWithinDate(postSearch);
    }

    @Override
    public String countCommentsOfAPost(String postID) {
        return dao_post_i.countCommentsOfAPost(postID);
    }

    @Override
    public List<PostcommentWithUserInfo> getCommentWithUserInfo(String postID) {
        List<PostcommentWithUserInfo> postcommentWithUserInfoList
                = dao_post_i.getCommentWithUserInfo(postID);
        String userID = Utils.getLoggedUserID();
        postcommentWithUserInfoList.forEach(pcWithUserInfo -> {
            if (pcWithUserInfo.getCommentUserId().equals(userID)) {
                pcWithUserInfo.setCommentOwner(true);
            }
        });
        return postcommentWithUserInfoList;
    }

    @Override
    public PostWithComments getPostWithComents(String loggedUserID, String postID) {
        PostWithComments postWithComments = new PostWithComments();

        Post postBasic = dao_post_i.findOnePostByIDNoComment(postID);
        postWithComments.setPostBasic(postBasic);
        if (postBasic.getPostUserID().equals(loggedUserID)) {
            postWithComments.setPostOwner(true);
        } else {
            postWithComments.setPostOwner(false);
        }

        List<PostcommentWithUserInfo> postcommentWithUserInfoList = getCommentWithUserInfo(postID);
        postcommentWithUserInfoList.forEach(postComment -> {
            if (postComment.getCommentUserId().equals(loggedUserID)) {
                postComment.setCommentOwner(true);
            } else {
                postComment.setCommentOwner(false);
            }
        });
        postWithComments.setPostcommentWithUserInfoList(postcommentWithUserInfoList);

        postWithComments.setCommentCount(postcommentWithUserInfoList.size());

        return postWithComments;
    }

    @Override
    public Post findOnePostByIDNoComment(String postID) {
        return dao_post_i.findOnePostByIDNoComment(postID);
    }

}
