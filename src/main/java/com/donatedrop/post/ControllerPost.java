/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donatedrop.post;

import com.donatedrop.post.model.*;

import java.util.List;
import java.util.Map;

import com.donatedrop.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dell
 */
@RestController
@RequestMapping("/public/user/post")
public class ControllerPost {

    @Autowired
    Service_Post_I service_post_i;

    @PostMapping("save")
    public Map<String, String> savePost(@RequestBody Post post) {
        post.setPostUserID(Utils.getLoggedUserID());
        return service_post_i.savePost(post);
    }

    @PostMapping("updatePost")
    public Map<String, String> updatePost(@RequestBody Post post) {
        post.setPostUserID(Utils.getLoggedUserID());
        return service_post_i.updatePost(post);
    }

    @PostMapping("deletePost")
    public Map<String, String> deletePost(@RequestParam String postID) {
        String userID = Utils.getLoggedUserID();
        return service_post_i.deletePost(userID, postID);
    }

    @PostMapping("getAllPostsByAnUser")
    public List<Post> getAllPostsByAnUser(@RequestBody MyPostSearch postSearch) {
        postSearch.setUserID(Utils.getLoggedUserID());
        return service_post_i.getAllPostsByAnUser(postSearch);
    }

    @PostMapping("countAllPostsByAnUser")
    public String countAllPostsByAnUser(@RequestBody MyPostSearch postSearch) {
        postSearch.setUserID(Utils.getLoggedUserID());
        return service_post_i.countAllPostsByAnUser(postSearch);
    }

    @PostMapping("getAllPostsByAnUserWithinDate")
    public List<Post> getAllPostsByAnUserWithinDate(@RequestBody MyPostSearch postSearch) {
        postSearch.setUserID(Utils.getLoggedUserID());
        return service_post_i.getAllPostsByAnUserWithinDate(postSearch);
    }

    @PostMapping("countAllPostsByAnUserWithinDate")
    public String countAllPostsByAnUserWithinDate(@RequestBody MyPostSearch postSearch) {
        postSearch.setUserID(Utils.getLoggedUserID());
        return service_post_i.countAllPostsByAnUserWithinDate(postSearch);
    }

    @PostMapping("findOnePostByIDNoComment")
    public Post findOnePostByIDNoComment(@RequestParam String postID) {
        return service_post_i.findOnePostByIDNoComment(postID);
    }

    @PostMapping("getCommentWithUserInfo")
    public List<PostcommentWithUserInfo> getCommentWithUserInfo(@RequestParam String postID) {
        return service_post_i.getCommentWithUserInfo(postID);
    }

    @PostMapping("saveComment")
    public Map<String, String> saveComment(@RequestBody CommentSave commentSave) {
        String userID = Utils.getLoggedUserID();
        PostComment postComment = new PostComment(commentSave.getCommentContent(), userID);
        String postID = commentSave.getPostID();
        return service_post_i.saveComment(postComment, postID);
    }

    @PostMapping("deleteComment")
    public Map<String, String> deletePostComment(@RequestBody CommentDelete commentDelete) {
        PostComment postComment = new PostComment();
        postComment.setCommentID(new Long(commentDelete.getCommentID()));
        postComment.setUserID(commentDelete.getCommentUserID());
        String postID = commentDelete.getPostID();
        return service_post_i.deletePostComment(postID, postComment);
    }
}
