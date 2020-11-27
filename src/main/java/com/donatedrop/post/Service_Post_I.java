package com.donatedrop.post;

import com.donatedrop.post.model.*;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Map;

public interface Service_Post_I {

    public Map<String, String> savePost(Post post);

    public Map<String, String> updatePost(Post post);

    public Post findPostByUserIDNoComment(String userID, String postID);

    public Post findOnePostByID(String id);

    public Post findOnePostByIdWithComments(String postID);

    public Post findOnePostByIDNoComment(String postID);

    public Map<String, String> deletePost(String userID, String postID);

    public Map<String, String> saveComment(PostComment postComment, String postID);

    public PostComment findOneComment(String postID, String userID, String commentID);

    public Map<String, String> updatePostComment(PostComment postComment, String postID);

    public Map<String, String> deletePostComment(String postID, PostComment postCommentOld);

    //-----Search and all posts by an user.
    public List<Post> getAllPostsByAnUser(MyPostSearch postSearch);

    public String countAllPostsByAnUser(MyPostSearch postSearch);

    public List<Post> getAllPostsByAnUserWithinDate(MyPostSearch postSearch);

    public String countAllPostsByAnUserWithinDate(MyPostSearch postSearch);

    public String countCommentsOfAPost(String postID);

    public List<PostcommentWithUserInfo> getCommentWithUserInfo(String postID);

    /**
     * @param loggedUserID to check, does the current logged userID, is the
     *                     owner of a comment or not.
     * @param postID
     * @return
     */
    public PostWithComments getPostWithComents(String loggedUserID, String postID);
}
