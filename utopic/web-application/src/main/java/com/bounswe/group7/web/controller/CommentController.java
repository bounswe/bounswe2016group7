package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Comments;
import com.bounswe.group7.model.VotedComments;
import com.bounswe.group7.web.domain.CommentVote;
import com.bounswe.group7.web.domain.DeleteComment;
import com.bounswe.group7.web.domain.TopicComment;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles comment functionalities like adding, deleting and voting comment.
 * @author Batuhan
 */
@RestController
public class CommentController {
    
    /**
     * Receives comment from AJAX request. Adds it to the database using
     * client methods. After addition, gets every comment of that topic and
     * returns them in the wanted form.
     * 
     * @param request
     * @param comment
     * @return topic's comments
     */
    @RequestMapping(value = "/addcomment", method = RequestMethod.PUT,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addComment(HttpServletRequest request,@RequestBody Comments comment) {
        HttpSession session = request.getSession();
        CommentServiceClient commentClient = new CommentServiceClient((String) session.getAttribute("token"));
        UserServiceClient userClient = new UserServiceClient((String) session.getAttribute("token"));
        ObjectMapper mapper = new ObjectMapper();
        List <TopicComment> topicCommentList = new ArrayList<TopicComment>();
        String topicComments = "";
        try{
            commentClient.createComment(comment);
            List <Comments> comments = commentClient.getTopicComments(comment.getTopicId());
            for(Comments temp: comments) {
                String username = userClient.getUser(temp.getUserId()).getUsername();
                TopicComment topicComment = new TopicComment(username, temp.getText(), temp.getDateCreated(), temp.getRate(), temp.getUserId(), temp.getCommentId());
                topicCommentList.add(topicComment);
            }
            topicComments = mapper.writeValueAsString(topicCommentList);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        return topicComments;
    }
    /**
     * Receives comment id and topic id in a domain called DeleteComment from
     * AJAX request. Deletes that comment and returns the comments of that topic
     * 
     * @param request
     * @param comment -> comment_id and topic_id
     * @return topic's comment
     */
    @RequestMapping(value = "/deletecomment", method = RequestMethod.DELETE,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String deleteComment(HttpServletRequest request,@RequestBody DeleteComment comment) {
        HttpSession session = request.getSession();
        CommentServiceClient commentClient = new CommentServiceClient((String) session.getAttribute("token"));
        UserServiceClient userClient = new UserServiceClient((String) session.getAttribute("token"));
        ObjectMapper mapper = new ObjectMapper();
        List <TopicComment> topicCommentList = new ArrayList<TopicComment>();
        String topicComments = "";
        try{
            commentClient.deleteComment(comment.commentId);
            List <Comments> comments = commentClient.getTopicComments(comment.topicId);
            for(Comments temp: comments) {
                String username = userClient.getUser(temp.getUserId()).getUsername();
                TopicComment topicComment = new TopicComment(username, temp.getText(), temp.getDateCreated(), temp.getRate(), temp.getUserId(), temp.getCommentId());
                topicCommentList.add(topicComment);
            }
            topicComments = mapper.writeValueAsString(topicCommentList);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return topicComments;
    }
    /**
     * Receives the type of vote (up/down) and comment id in a domain called
     * CommentVote by an AJAX request. Updates the votes of the comment and
     * returns total vote of that comment.
     * 
     * @param request
     * @param commentVote -> type and comment_id
     * @return vote
     */
    @RequestMapping(value = "/votecomment", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int voteComment(HttpServletRequest request, @RequestBody CommentVote commentVote){
        HttpSession session = request.getSession();
        CommentServiceClient commentClient = new CommentServiceClient((String) session.getAttribute("token"));
        VotedComments votedComment = new VotedComments();
        try{
            votedComment.setCommentId(commentVote.commentId);
            votedComment.setRate(commentVote.updown);
            commentClient.voteComment(votedComment);
            int votes = commentClient.getCommentRate(commentVote.commentId);
            return votes;
        }catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }
}
