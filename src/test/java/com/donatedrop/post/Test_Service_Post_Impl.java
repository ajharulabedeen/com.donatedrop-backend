package com.donatedrop.post;

import com.donatedrop.post.model.Post;
import com.donatedrop.post.model.PostComment;
import com.donatedrop.other.DumpDao;
import com.donatedrop.other.DumpData;
import com.donatedrop.post.model.PostWithComments;
import com.donatedrop.post.model.PostcommentWithUserInfo;
import com.donatedrop.security.models.User;
import com.donatedrop.util.StringUtil;
import javafx.geometry.Pos;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class Test_Service_Post_Impl {

    @Autowired
    DumpDao dumpDao;

    @Autowired
    Service_Post_I service_post_i;

    //    @Test
//    @Order(1)
//    public void test_savePost() {
//        Map<String, String> status = null;
//        List<User> userList = dumpDao.getUsers(0, 10);
//        Random random = new Random();
//        List<PostComment> comments = new ArrayList<>();
//        String useID = userList.get(random.nextInt(userList.size() - 1)).getId().toString();
//        for (int y = 0; y <= random.nextInt(5); y++) {
//            PostComment postComment = new PostComment(DumpData.getDate(), DumpData.getNote(), useID);
//            comments.add(postComment);
//        }
//
//        Post post = new Post(
//                userList.get(random.nextInt(userList.size() - 1)).getId().toString(),
//                DumpData.getBloodGroup(),
//                dumpDao.getAddressString("PRESENT"),
//                DumpData.getHospitalName(),
//                DumpData.getHospitalAddress(),
//                DumpData.getGender(),
//                DumpData.getPatientDescription(),
//                "20-10-10",
//                DumpData.getDate(),
//                DumpData.getQuantity(),
//                DumpData.getPhoneNumber(),
//                "0",
//                DumpData.getNote(),
//                "NO",
//                "SON",
//                DumpData.getNote(),
//                "NA",
//                DumpData.getNote(),
//                comments);
//
//        status = dao_post_i.savePost(post);
//        storeID(status.get(StringUtil.ID));
//        storeUserID(useID);
//        Assert.assertEquals(StringUtil.OK, status.get(StringUtil.STATUS));
//    }
//    @Test
//    public void test_findPostByUserIDNoComment() {
////        `post_id`=20618 `post_user_id`=12417
//        Post p = dao_post_i.findPostByUserIDNoComment("12417", "20619");
////        System.out.println(p.getHospitalAddress());
////        Assert.assertNotEquals(p, null);
////        Assert.assertNotNull(p);
//        Assert.assertNull(p);
//    }
//    @Test
//    public void testFindOneByID() {
////        `post_id`=20618;
//        Post p = dao_post_i.findOnePostByID(getID());
////        System.out.println(p.getPostID());
//        Assert.assertNull(p);
//    }

    //   done-passed
    @Test
    public void testUpdatePost() {
        String userID = getUserID();
        String postID = getID();
        Map<String, String> status = null;
        Post p = service_post_i.findOnePostByID(postID);
        System.out.println("\n>>>" + p.getNotes() + "\n");
        String updateNote = "NotaUpdated! No Blood Need!";
//        String updateNote = "Stupid People, haveing money by no means.";
        p.setNotes(updateNote);
        status = service_post_i.updatePost(p);
//    check update success.
        Post updatedP = service_post_i.findOnePostByID(postID);
        Assert.assertEquals(StringUtil.OK, status.get(StringUtil.STATUS));
        Assert.assertEquals(updateNote, updatedP.getNotes());
        System.out.println("\n>>>" + updatedP.getNotes() + "\n");


        //    check update fail : unatherised.
        p.setPostUserID(p.getPostUserID() + 1);//change userID.
        status = service_post_i.updatePost(p);
        updatedP = service_post_i.findOnePostByID(postID);
        Assert.assertEquals(StringUtil.FAIL, status.get(StringUtil.STATUS));
        System.out.println(status);
    }

    //    @Test
//    public void testPostByAnUserWithSearch() {
//        String userID = "12417";
////                              start, max, key, column, orderBy, orderType, userID
////       startDate, endDate :   start, max, key, column, orderBy, orderType, userID
////        SELECT * FROM `post` WHERE contact_info LIKE "%%" AND post_user_id = 12417 ORDER BY need_date DESC
//        PostSearch postSearch = new PostSearch();
//        postSearch.setColumn("contact_info");
//        postSearch.setKey("'%%'");
//        postSearch.setUserID("12417");
//        postSearch.setOrderBy("need_date");
//        postSearch.setOrderType("DESC");
//
//        dao_post_i.getAllPostsByAnUser(postSearch).forEach(p -> {
//            System.out.println(p.getHospitalAddress());
//        });
//    }
//    @Test
//    public void testCountPostsByAnUser() {
//        String userID = "12417";
////                              start, max, key, column, orderBy, orderType, userID
////       startDate, endDate :   start, max, key, column, orderBy, orderType, userID
////        SELECT * FROM `post` WHERE contact_info LIKE "%%" AND post_user_id = 12417 ORDER BY need_date DESC
//        PostSearch postSearch = new PostSearch();
//        postSearch.setColumn("contact_info");
//        postSearch.setKey("'%014%'");
//        postSearch.setUserID("12417");
//        postSearch.setOrderBy("need_date");
//        postSearch.setOrderType("DESC");
//
//        String count = dao_post_i.countAllPostsByAnUser(postSearch);
//
//        System.out.println(count);
//    }
//    @Test
//    public void testPostByAnUserBetweenDateWithSearch() {
//        String userID = "12417";
////                              start, max, key, column, orderBy, orderType, userID
////       startDate, endDate :   start, max, key, column, orderBy, orderType, userID
////        SELECT * FROM `post` WHERE contact_info LIKE "%%" AND post_user_id = 12417 ORDER BY need_date DESC
////        SELECT * FROM `post` WHERE hospital_address LIKE "%k%" AND post_user_id = 12417 AND ( need_date BETWEEN '2020-01-1' AND '2020-05-18' ) ORDER BY quantity DESC
//
//        PostSearch postSearch = new PostSearch();
//        postSearch.setDateType("need_date");
//        postSearch.setStartDate("2020-01-1");
//        postSearch.setEndDate("2020-05-18");
//        postSearch.setColumn("hospital_address");
//        postSearch.setKey("'%k%'");
//        postSearch.setUserID("12417");
//        postSearch.setOrderBy("need_date");
//        postSearch.setOrderType("DESC");
//
//        dao_post_i.getAllPostsByAnUserWithinDate(postSearch).forEach(p -> {
//            System.out.println(p.getPostID() + "---" + p.getHospitalAddress());
//        });
//    }
//    @Test
//    public void testCountPostByAnUserBetweenDateWithSearch() {
//        String userID = "12417";
////                              start, max, key, column, orderBy, orderType, userID
////       startDate, endDate :   start, max, key, column, orderBy, orderType, userID
////        SELECT * FROM `post` WHERE contact_info LIKE "%%" AND post_user_id = 12417 ORDER BY need_date DESC
////        SELECT * FROM `post` WHERE hospital_address LIKE "%k%" AND post_user_id = 12417 AND ( need_date BETWEEN '2020-01-1' AND '2020-05-18' ) ORDER BY quantity DESC
//
//        PostSearch postSearch = new PostSearch();
//        postSearch.setDateType("need_date");
//        postSearch.setStartDate("2020-01-1");
//        postSearch.setEndDate("2020-05-18");
//        postSearch.setColumn("hospital_address");
//        postSearch.setKey("'%k%'");
//        postSearch.setUserID("12417");
//        postSearch.setOrderBy("need_date");
//        postSearch.setOrderType("DESC");
//
//        String count = dao_post_i.countAllPostsByAnUserWithinDate(postSearch);
//        System.out.println(count);
//    }
//    @Test
//    public void testGetPostWithComments() {
//        Post p = dao_post_i.findPostWithComments(getID());
//        if (p != null) {
//            System.out.println(p.toString());
//            Assert.assertNotNull("");
//        }
//    }
    //    start : comment
//    @Test
//    public void testAddCommentToPost() {
//        Random r = new Random();
//        int start = r.nextInt(500);
//        String userID = dumpDao.getUsers(start, 1).get(0).getId().toString();
//        PostComment postComment = new PostComment(DumpData.getDate(), "Updated Comment!", userID);
//        Map<String, String> status = null;
////        System.out.println("\n\n" + dao_post_i.findPostWithComments(getID()).getPostComments().size());
//        status = dao_post_i.saveComment(postComment, getID());
//        storeCommentID(status.get(StringUtil.ID));
//        storeCommentUserID(userID);
//        System.out.println("\n\n" + status);
////        System.out.println("\n\n" + dao_post_i.findPostWithComments(getID()).getPostComments().size());
//    }
//    @Test
//    public void testCountCommentsAPost() {
//        // right value test
//        String postID = getID();
//        String totalCommet = dao_post_i.countCommentsOfAPost(postID);
//        System.out.println(totalCommet);
//    }
//    @Test
//    public void testReadOneComment() {
//        // right value test
//        String postID = getID();
//        String userID = getCommentUserID();
//        String commentID = getCommentID();
//        PostComment postComment = dao_post_i.findOneComment(postID, userID, commentID);
//        System.out.println(postComment);
//        Assert.assertNotNull(postComment);
//
//        // wrog value test . one user tring to access another comment
//        postComment = dao_post_i.findOneComment(postID, userID + 1, commentID);
//        Assert.assertNull(postComment);
//    }

    //    done
    @Test
    public void testUpdateComment() {
        Map<String, String> status = null;
        // right value test
        String postID = getID();
        String userID = getCommentUserID();
        String commentID = getCommentID();
        PostComment postCommentOld = service_post_i.findOneComment(postID, userID, commentID);
        System.out.println(postCommentOld);
        Assert.assertNotNull(postCommentOld);
//----------
        String sOld = "----Comment Detials Updated DIM DIM!";
        postCommentOld.setCommentDetails(sOld);
        status = service_post_i.updatePostComment(postCommentOld, postID);
        String sNew = service_post_i.findOneComment(postID, userID, commentID).getCommentDetails();
//----------
        Assert.assertEquals(sOld, sNew);
        Assert.assertEquals(StringUtil.OK, status.get(StringUtil.STATUS));

//        failTest
        status = service_post_i.updatePostComment(postCommentOld, postID + 1);
        Assert.assertEquals(StringUtil.FAIL, status.get(StringUtil.STATUS));
        Assert.assertEquals(StringUtil.UNAUTHERIZED, status.get(StringUtil.MESSAGE));
        System.out.println(status);
    }

    //done
    @Test
    public void testDeleteComment() {
        Map<String, String> status = null;
        // right value test
        String postID = getID();
        String userID = getCommentUserID();
        String commentID = getCommentID();
        PostComment postCommentOld = service_post_i.findOneComment(postID, userID, commentID);
        System.out.println(postCommentOld);
        Assert.assertNotNull(postCommentOld);

        status = service_post_i.deletePostComment(postID, postCommentOld);
        Assert.assertEquals(StringUtil.OK, status.get(StringUtil.STATUS));
        PostComment postCommentDeleted = service_post_i.findOneComment(postID, userID, commentID);
        Assert.assertNull(postCommentDeleted);

//        fail delete
        status = service_post_i.deletePostComment(postID + 1, postCommentOld);
        Assert.assertEquals(StringUtil.FAIL, status.get(StringUtil.STATUS));


    }

    @Test
    public void testGetCommentWithUserInfo() {
        String postID = getID();
        List<PostcommentWithUserInfo> commentWithUserInfoList =
                service_post_i.getCommentWithUserInfo(postID);
        System.out.println("\n\nTest Section--------------\n");
        commentWithUserInfoList.forEach(cui -> {
            System.out.println(cui.toString());
        });
    }
//    end : comment

    //done.
    @Test
    public void testDeletePost() {
        String userID = getUserID();
        String postID = getID();
        Map<String, String> status = null;
//        Post p = service_post_i.findOnePostByID(postID);
//        System.out.println("\n>>>" + p.getNotes() + "\n");
        status = service_post_i.deletePost(userID, postID);
//        Post updatedP = service_post_i.findOnePostByID(postID);
        Assert.assertEquals(StringUtil.OK, status.get(StringUtil.STATUS));

//        delete fail :
        status = service_post_i.deletePost(userID + 1, postID);
        Assert.assertEquals(StringUtil.FAIL, status.get(StringUtil.STATUS));
    }

    @Test
    public void test_getPostWithComents() {
        String postID = "19942";
        String userID = "11167";//owner one of the commentns.
        PostWithComments postWithComments = service_post_i.getPostWithComents(userID, postID);
        System.out.println(postWithComments.toString());
    }

    //    Data insertion :
    public void storeUserID(String userID) {
        try {
            FileWriter myWriter = new FileWriter("post_userID.txt");
            myWriter.write(userID);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getUserID() {
        String id = "";
        try {
            File myObj = new File("post_userID.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                id = data;
//                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return id;
    }

    public void storeID(String id) {
        try {
            FileWriter myWriter = new FileWriter("post_ID.txt");
            myWriter.write(id);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getID() {
        String id = "";
        try {
            File myObj = new File("post_ID.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                id = data;
//                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return id;
    }

    public void storeCommentID(String id) {
        try {
            FileWriter myWriter = new FileWriter("post_comment_ID.txt");
            myWriter.write(id);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getCommentID() {
        String id = "";
        try {
            File myObj = new File("post_comment_ID.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                id = data;
//                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return id;
    }

    public void storeCommentUserID(String id) {
        try {
            FileWriter myWriter = new FileWriter("post_comment_user_ID.txt");
            myWriter.write(id);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getCommentUserID() {
        String id = "";
        try {
            File myObj = new File("post_comment_user_ID.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                id = data;
//                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return id;
    }

}// class
