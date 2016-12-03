package com.bounswe.group7.web.controller;

import com.bounswe.group7.api.client.CommentServiceClient;
import com.bounswe.group7.api.client.QuizServiceClient;
import com.bounswe.group7.api.client.TagServiceClient;
import com.bounswe.group7.api.client.TopicServiceClient;
import com.bounswe.group7.api.client.UserServiceClient;
import com.bounswe.group7.model.Comments;
import com.bounswe.group7.model.Questions;
import com.bounswe.group7.model.Quizes;
import com.bounswe.group7.model.Tags;
import com.bounswe.group7.model.TopicPacks;
import com.bounswe.group7.model.Topics;
import com.bounswe.group7.model.Users;
import com.bounswe.group7.web.domain.SaveQuizOption;
import com.bounswe.group7.web.domain.SaveQuizQuestion;
import com.bounswe.group7.web.domain.SaveTopic;
import com.bounswe.group7.web.domain.TopicComment;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Batuhan
 */
@RestController
public class TopicController {
    
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
    public ModelAndView showTopic(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("topic");
        HttpSession session = request.getSession();
        TopicServiceClient topicClient = new TopicServiceClient((String) session.getAttribute("token"));
        CommentServiceClient commentClient = new CommentServiceClient((String) session.getAttribute("token"));
        UserServiceClient userClient = new UserServiceClient((String) session.getAttribute("token"));
        QuizServiceClient quizClient = new QuizServiceClient((String) session.getAttribute("token"));
        ObjectMapper mapper = new ObjectMapper();
        List <TopicComment> topicCommentList = new ArrayList<TopicComment>();
        
        try {
            Topics topic = topicClient.getTopic(id);
            Users owner = userClient.getUser(topic.getUserId());
            TopicPacks topicPack = topicClient.getTopicPack(topic.getTopicPackId());
            List<Comments> comments = commentClient.getTopicComments(id);
            
            for(Comments temp: comments) {
                String username = userClient.getUser(temp.getUserId()).getUsername();
                TopicComment topicComment = new TopicComment(username, temp.getText(), temp.getDateCreated(), temp.getRate(), temp.getUserId(), temp.getCommentId());
                topicCommentList.add(topicComment);
            }
            
            List<Tags> tags = topic.getTags();
            
            Quizes quizBack = quizClient.getQuiz(id);
          
            List<SaveQuizQuestion> quiz = new ArrayList<SaveQuizQuestion>();
            
            if(quizBack != null){
                for(Questions question : quizBack.getQuestions()){
                    SaveQuizQuestion quizQuestion = new SaveQuizQuestion();
                    quizQuestion.text = question.getQuestion();
                    quizQuestion.options = new ArrayList<SaveQuizOption>();
                    for(int i=0; i<4; i++){
                        SaveQuizOption option = new SaveQuizOption();
                        char optionLetter = (char) ('A' + i);
                        String methodName = "getChoice" + optionLetter; 
                        Method method = question.getClass().getMethod(methodName);
                        Object temp = method.invoke(question);
                        option.text = (String) temp;
                        quizQuestion.options.add(option);
                    }
                    quiz.add(quizQuestion);
                }
            }
            
            String tagsJson = mapper.writeValueAsString(tags);
            String commentsJson = mapper.writeValueAsString(topicCommentList);
            String quizJson = mapper.writeValueAsString(quiz);
            
            modelAndView.addObject("pack",topicPack);
            modelAndView.addObject("topic", topic);
            modelAndView.addObject("tags", tagsJson);
            modelAndView.addObject("owner", owner);
            modelAndView.addObject("quiz", quizJson);
            modelAndView.addObject("comments", commentsJson);
        } catch (Exception ex) {
            ex.printStackTrace();
            attributes.addAttribute("error", ex.getMessage());
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/createTopic", method = RequestMethod.PUT,
           produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long createTopic(HttpServletRequest request, @RequestBody SaveTopic topic, RedirectAttributes attributes){
        HttpSession session = request.getSession();
        TopicServiceClient topicClient = new TopicServiceClient((String) session.getAttribute("token"));
        TagServiceClient tagClient = new TagServiceClient((String) session.getAttribute("token"));
        QuizServiceClient quizClient = new QuizServiceClient((String) session.getAttribute("token"));
        Topics topicCreated = new Topics();
        try{
            Topics topicToBeAdded = new Topics();
            topicToBeAdded.setContent(topic.content);
            topicToBeAdded.setDescription(topic.description);
            topicToBeAdded.setTags(topic.tags);
            topicToBeAdded.setHeader(topic.header);
            topicCreated = topicClient.createTopic(topicToBeAdded);
            
            Quizes quiz = new Quizes();
            quiz.setName(topicCreated.getHeader());
            quiz.setTopicId(topicCreated.getTopicId());
            quiz = quizClient.createQuiz(quiz);
            List<Questions> questions = new ArrayList(); 
            for(SaveQuizQuestion saveQuestion : topic.questions) {
                Questions question = new Questions();
                question.setQuizId(quiz.getQuizId());
                question.setQuestion(saveQuestion.text);
                List<SaveQuizOption> options = saveQuestion.options;
                for(int i=0; i<options.size(); i++){
                    char optionLetter = (char) ('A' + i);
                    String methodName = "setChoice" + optionLetter; 
                    Method method = question.getClass().getMethod(methodName, String.class);
                    SaveQuizOption option = options.get(i);
                    method.invoke(question, option.text);
                    if(option.isValid == 1){
                        question.setRightAnswer(optionLetter);
                    }
                }
                quizClient.addQuestion(question);
                questions.add(question);
            }
          
        }catch(Exception ex){
            ex.printStackTrace();
            attributes.addAttribute("error", ex.getMessage());
        }
        return topicCreated.getTopicId();
    }
    
    @RequestMapping(value = "/createTag", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long createTag(HttpServletRequest request, @RequestBody Tags tag, RedirectAttributes attributes) {
        HttpSession session = request.getSession();
        TagServiceClient client = new TagServiceClient((String) session.getAttribute("token"));
        Tags tagCreated = new Tags();
        try {
            tagCreated = client.createTag(tag);
        } catch (Exception ex) {
            ex.printStackTrace();
            attributes.addAttribute("error", ex.getMessage());
        }
        return tagCreated.getTagId();
    }
    
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
}
