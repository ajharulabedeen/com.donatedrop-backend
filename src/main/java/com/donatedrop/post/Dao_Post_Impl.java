package com.donatedrop.post;

import com.donatedrop.post.model.MyPostSearch;
import com.donatedrop.post.model.PostComment;
import com.donatedrop.post.model.Post;
import com.donatedrop.agent.models.StatusType;
import com.donatedrop.history.History;
import com.donatedrop.post.model.PostcommentWithUserInfo;
import com.donatedrop.profile.model.ProfileBasic;
import com.donatedrop.util.DateUtil;
import com.donatedrop.util.StringUtil;
import javafx.geometry.Pos;
import lombok.Builder;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Transactional
public class Dao_Post_Impl implements Dao_Post_I {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Map<String, String> savePost(Post post) {
        Map<String, String> result = new HashMap<>();
        try {
            post.setPostDate(DateUtil.getDate());
            post.setStatus(StatusType.ZERO);
            entityManager.persist(post);
            result.put(StringUtil.STATUS, StringUtil.OK);
            result.put(StringUtil.MESSAGE, StringUtil.SAVE);
            result.put(StringUtil.ID, post.getPostID().toString());
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.DUPLICATE);
        } catch (ConstraintViolationException constraintViolationException) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.DUPLICATE);
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.UNKNOWN);
        }
        return result;
    }

    @Override
    public Map<String, String> updatePost(Post postNew) {
        Map<String, String> result = new HashMap<>();
        Post postOld = findPostByUserIDNoComment(postNew.getPostUserID(), postNew.getPostID().toString());
        if (postOld != null) {
            //refactor : follow emegency contact update
            postOld.setBloodType(postNew.getBloodType());
            postOld.setContactInfo(postNew.getContactInfo());
            postOld.setDonnerFound(postNew.getDonnerFound());
            postOld.setHospitalAddress(postNew.getHospitalAddress());
            postOld.setHospitalName(postNew.getHospitalName());
            postOld.setLocation(postNew.getLocation());
            postOld.setNeedDate(postNew.getNeedDate());
            postOld.setNotes(postNew.getNotes());
            postOld.setPatientDescription(postNew.getPatientDescription());
            postOld.setPatientGender(postNew.getPatientGender());
            postOld.setPostDate(postNew.getPostDate());
            postOld.setQuantity(postNew.getQuantity());
            postOld.setRelation(postNew.getRelation());
            postOld.setRemarks(postNew.getRemarks());
            postOld.setReport(postNew.getReport());
            postOld.setStatus(postNew.getStatus());
            try {
                entityManager.merge(postOld);
                result.put(StringUtil.STATUS, StringUtil.OK);
            } catch (Exception e) {
                System.out.println("Blood Post Update Failed!");
                result.put(StringUtil.STATUS, StringUtil.FAIL);
            }
        } else {
            System.out.println("Blood Post Not Found!");
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Post findPostByUserIDNoComment(String userID, String postID) {
        String sql = "SELECT * FROM `post` WHERE `post_id`=" + postID + " AND `post_user_id`=" + userID;
        Post post = null;
        List<Post> list = entityManager
                .createNativeQuery(sql, Post.class)
                .getResultList();
        if (list.size() >= 1) {
            post = list.get(0);
        }
        return post;
    }

    @Override
    public Post findOnePostByID(String id) {
        Post post = null;
        try {
            post = entityManager.find(Post.class, Long.parseLong(id));
        } catch (Exception e) {
            System.out.println("Not Found!");
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post findOnePostByIDNoComment(String id) {
        Post postBasic = new Post();
        try {
            Post post = entityManager.find(Post.class, Long.parseLong(id));
            postBasic.setPostID(post.getPostID());
            postBasic.setPostUserID(post.getPostUserID());
            postBasic.setStatus(post.getStatus());
            postBasic.setBloodType(post.getBloodType());
            postBasic.setQuantity(post.getQuantity());
            postBasic.setNeedDate(post.getNeedDate());
            postBasic.setPatientGender(post.getPatientGender());
            postBasic.setRelation(post.getRelation());
            postBasic.setHospitalName(post.getHospitalName());
            postBasic.setHospitalAddress(post.getHospitalAddress());
            postBasic.setLocation(post.getLocation());
            postBasic.setContactInfo(post.getContactInfo());
            postBasic.setPatientDescription(post.getPatientDescription());
            postBasic.setRemarks(post.getRemarks());
            postBasic.setNotes(post.getNotes());
        } catch (Exception e) {
            System.out.println("Not Found!");
            e.printStackTrace();
        }
        return postBasic;
    }

    @Override
    public Map<String, String> deletePost(String id) {
        Map<String, String> result = new HashMap<>();
        try {
            Post postDelete = entityManager.find(Post.class,
                    Long.parseLong(id));
            //to protect one user delete, another users information. will be checked in Service
            entityManager.remove(postDelete);
            result.put(StringUtil.STATUS, StringUtil.OK);
        } catch (Exception e) {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Post findPostWithComments(String postID) {
        Post post = findOnePostByID(postID);
        if (post != null) {
            post.getPostComments().forEach(s -> {
            });
            return post;
        } else {
            post = null;
        }
        return post;
    }

    @Override
    public Map<String, String> saveComment(PostComment postComment, String postID) {
        Map<String, String> result = new HashMap<>();
        Post post = findOnePostByID(postID);
        if (post != null) {
            try {
                postComment.setDate(DateUtil.getDate());
                entityManager.persist(postComment);
                post.getPostComments().add(postComment);
                entityManager.merge(post);
                result.put(StringUtil.STATUS, StringUtil.OK);
                result.put(StringUtil.MESSAGE, StringUtil.SAVE);
                System.out.println("\n---------------------" + result + "------------\n");
                System.out.println("\n---------------------" + postComment + "------------\n");
                result.put(StringUtil.ID, postComment.getCommentID().toString());
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                result.put(StringUtil.STATUS, StringUtil.FAIL);
                result.put(StringUtil.MESSAGE, StringUtil.DUPLICATE);
            } catch (ConstraintViolationException constraintViolationException) {
                result.put(StringUtil.STATUS, StringUtil.FAIL);
                result.put(StringUtil.MESSAGE, StringUtil.DUPLICATE);
            }
//            catch (Exception e) {
//                result.put(StringUtil.STATUS, StringUtil.FAIL);
//                result.put(StringUtil.MESSAGE, StringUtil.UNKNOWN);
//                System.out.println(e);
//            }
        } else {
            result.put(StringUtil.STATUS, StringUtil.FAIL);
            result.put(StringUtil.MESSAGE, StringUtil.NULL);
        }
        return result;
    }

    @Override
    public PostComment findOneComment(String postID, String userID, String commentID) {
        String sql = "SELECT * FROM postcomment WHERE comment_id = " + commentID
                + " AND postcomment.`comment_user_id` = " + userID
                + " AND `post_id` = " + postID;
        PostComment postComment = null;
        List<PostComment> commentList = entityManager.createNativeQuery(sql, PostComment.class).getResultList();
        if (commentList.size() >= 1) {
            postComment = commentList.get(0);
        }
        return postComment;
    }

    @Override
    public Map<String, String> updatePostComment(PostComment postComment) {
        Map<String, String> result = new HashMap<>();
        try {
            PostComment postCommentOld = entityManager.find(PostComment.class, postComment.getCommentID());
            postCommentOld.setCommentDetails(postComment.getCommentDetails());
            postCommentOld.setDate(DateUtil.getDate());
            entityManager.merge(postCommentOld);
            result.put(StringUtil.STATUS, StringUtil.OK);
        } catch (Exception e) {
            System.out.println("Blood Post Update Failed!");
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public Map<String, String> deletePostComment(PostComment postComment) {
        Map<String, String> result = new HashMap<>();
        try {
            PostComment postCommentOld = entityManager.find(PostComment.class, postComment.getCommentID());
            entityManager.remove(postCommentOld);
            result.put(StringUtil.STATUS, StringUtil.OK);
        } catch (Exception e) {
            System.out.println("Post Comment delete Failed!");
            result.put(StringUtil.STATUS, StringUtil.FAIL);
        }
        return result;
    }

    @Override
    public List<Post> getAllPostsByAnUser(MyPostSearch postSearch) {
        String sql = "SELECT * FROM `post` WHERE " + postSearch.getColumn() + " LIKE " + postSearch.getKey()
                + " AND post_user_id = " + postSearch.getUserID()
                + " AND status = " + postSearch.getPostType()
                + " ORDER BY " + postSearch.getOrderBy()
                + " " + postSearch.getOrderType(); //ASC-DESC

        return entityManager.createNativeQuery(sql, Post.class)
                .setFirstResult(postSearch.getStart())
                .setMaxResults(postSearch.getMax())
                .getResultList();
    }

    @Override
    public String countAllPostsByAnUser(MyPostSearch postSearch) {
        String sql = "SELECT count(*) FROM `post` WHERE " + postSearch.getColumn() + " LIKE " + postSearch.getKey()
                + " AND post_user_id = " + postSearch.getUserID()
                + " AND status = " + postSearch.getPostType()
                + " ORDER BY " + postSearch.getOrderBy()
                + " " + postSearch.getOrderType(); //ASC-DESC
        return entityManager.createNativeQuery(sql).getResultList().get(0).toString();
    }


    @Override
    public List<Post> getAllPostsByAnUserWithinDate(MyPostSearch postSearch) {
        String sql = "SELECT * FROM `post` WHERE " + postSearch.getColumn()
                + " LIKE " + postSearch.getKey()
                + " AND post_user_id = " + postSearch.getUserID()
                + " AND ( " + postSearch.getDateType() + " BETWEEN '" + postSearch.getStartDate() + "' AND '" + postSearch.getEndDate() + "' ) "
                + " AND status = " + postSearch.getPostType()
                + " ORDER BY " + postSearch.getOrderBy()
                + "  " + postSearch.getOrderType();

        return entityManager.createNativeQuery(sql, Post.class)
                .setFirstResult(postSearch.getStart())
                .setMaxResults(postSearch.getMax())
                .getResultList();
    }

    @Override
    public String countAllPostsByAnUserWithinDate(MyPostSearch postSearch) {
        String sql = "SELECT count(*) FROM `post` WHERE " + postSearch.getColumn()
                + " LIKE " + postSearch.getKey()
                + " AND post_user_id = " + postSearch.getUserID()
                + " AND ( " + postSearch.getDateType() + " BETWEEN '" + postSearch.getStartDate() + "' AND '" + postSearch.getEndDate() + "' ) "
                + " AND status = " + postSearch.getPostType()
                + " ORDER BY " + postSearch.getOrderBy()
                + "  " + postSearch.getOrderType();
        return entityManager.createNativeQuery(sql).getResultList().get(0).toString();
    }

    @Override
    public String countCommentsOfAPost(String postID) {
        String sql = "SELECT COUNT(*) FROM `postcomment` WHERE post_id=" + postID;
        return entityManager.createNativeQuery(sql).getResultList().get(0).toString();
    }

    @Override
    public List<PostcommentWithUserInfo> getCommentWithUserInfo(String postID) {
        String sql = "SELECT * FROM `postcomment_with_user_info` WHERE postcomment_with_user_info.post_id=" + postID;
        return entityManager.createNativeQuery(sql, PostcommentWithUserInfo.class).getResultList();
    }

}
