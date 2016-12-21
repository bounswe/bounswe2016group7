<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/cssFiles.jsp" />
    </head>
    <body ng-app="mainApp" ng-controller="topicController">
        <% if(session.getAttribute("username") != null){
        %><script type="text/javascript">
                    var auth = [];
            <c:forEach items="${authorities}" var="stdn" varStatus="status">
                    auth.push('${stdn.name}');
            </c:forEach>
                var initialRating = ${topic.rate}
                var followingUsers = ${followingUsers};
                var quiz = ${quiz};
                var activeUsername = "${sessionScope.username}";
                var activeId = ${userId};
                var activeToken = "${sessionScope.token}";
                var comments = ${comments};
                var ownerId = ${topic.userId};
                var topicId = ${topic.topicId};
                var topicTags = ${tags};
                var nextPrev = ${nextPrev};
        </script><%
            }else{
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "/"); 
           }    
        %>
        <jsp:include page="partial/getToolbar.jsp"/>
        <jsp:include page="partial/creatorModal.jsp"/>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="panel">
                            <div class="row">
                                <div class="col-xs-5 col-md-3">
                                    <div class="title-picture" style="background-image: url({{profilePicture}});">
                                        <div ng-if="topicOwner == activeUser"  class="change-picture">
                                            <i class="fa fa-camera" aria-hidden="true"></i>
                                            <br/>
                                            Change Picture
                                            <form id="picture-form" enctype="multipart/form-data">
                                                <input type="hidden" name="topicId" value="{{topic.topicId}}">
                                                <label for="update-picture">upload</label>
                                                <input id="update-picture" type="file" name="file" accept="image/jpg">
                                            </form>
                                        </div>
                                    </div>
                                    <p>Creator: <a href="/profile/${owner.id}">${owner.username}</a><br/>
                                    <div class="show-following">
                                        <a ng-click="toggleFollowing()">Followed (<span ng-bind="followingUsers.length"></span>)</a>
                                        <ul ng-show="showFollowing" class="following-users panel">
                                            <button ng-click="toggleFollowing()"><i class="fa fa-times" aria-hidden="true"></i></button>
                                            <li ng-repeat="user1 in followingUsers">
                                                <a href="/profile/{{user1.id}}" ng-bind="user1.username"></a>
                                            </li>
                                        </ul>
                                    </div>
                                    <button ng-if="topicOwner != activeUser" ng-click ="followTopic()" class="button button-orange" ng-bind="followText"></button>
                                    
                                </div>
                                <div class="col-xs-7 col-md-9">
                                    <h3>${topic.header} 
                                        <span class="rate-text">
                                            <div class="topic-rating"></div>
                                            <span>({{currentRate}})</span>
                                        </span>
                                    </h3>
                                    <p>${topic.description}</p>
                                    <div class="tags">
                                        <div ng-repeat="tag in tags" class="topic-tag" id="tag{{tag.tagId}}">
                                            {{tag.label}} ({{tag.category}})
                                        </div>
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-4">
                        <div class="panel">
                            <div class="current-topic">
                                <a href="/topicpack/${pack.topicPackId}" class="title">${pack.name}</a>
                                <a ng-if="nextPrev.prevId != -1" href="{{nextPrev.prevId}}" class="nav">
                                    <i style="margin-right:5px;" class="fa fa-chevron-left" aria-hidden="true"></i>
                                    <span ng-bind="nextPrev.prevName"></span>
                                </a>
                                <a ng-if="nextPrev.nextId != -1" href="/topic/{{nextPrev.nextId}}" class="nav">
                                    <span ng-bind="nextPrev.nextName"></span>
                                    <i style="margin-left:5px;" class="fa fa-chevron-right" aria-hidden="true"></i>
                                </a>
                            </div>
                            <div class="recommended">
                                <span class="title">Recommended</span>
                                <a href="#" class="recommended-topic">Topic 1</a>
                                <a href="#" class="recommended-topic">Topic 2</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-8">
                        <div class="panel topic-panel">
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#home" role="tab" aria-controls="home">Materials</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#profile" role="tab" aria-controls="profile">Discussion</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#quiz" role="tab" aria-controls="quiz">Quiz</a>
                                </li>
                            </ul>

                            <div class="tab-content">
                                <div class="tab-pane active" id="home" role="tabpanel">
                                    <p>${topic.content}</p>
                                </div>
                                <div class="tab-pane message-tab" id="profile" role="tabpanel">
                                    <div class="message-container">
                                        <div ng-if="!comments" class="centered">
                                            <p>There is no comments yet<br/>Be the first</p>
                                        </div>
                                        <div ng-repeat="comment in comments" class="message" id="comment{{comment.Id}}">
                                            <div class="message-point">
                                                <button class="button rate-button" ng-click="vote(comment, 1)"><i class="fa fa-chevron-up" aria-hidden="true"></i></button>
                                                <span>{{comment.Rating}}</span>
                                                <button class="button rate-button" ng-click="vote(comment, -1)"><i class="fa fa-chevron-down" aria-hidden="true"></i></button>
                                            </div>
                                            <div class="message-content">
                                                <a href="/profile/{{comment.UserId}}" class="title">
                                                    {{comment.Username}}
                                                </a>
                                                <button type="button" class="float-button button-green" name='{{comment.Username}}' slideid="comment{{comment.Id}}"  ng-click="reply($event)" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
                                                <p ng-bind-html="comment.Content | to_trusted"></p>
                                                <span class="date">
                                                    {{comment.CreateDate}}
                                                </span>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                    <div class="input-container">
                                        <input type="text" ng-model="commentToAdd" placeholder="Your message" class="form-control">
                                        <button class="button button-green send-button" ng-click="addComment()"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                                    </div>
                                </div>
                                <div class="tab-pane" id="quiz" role="tabpanel">
                                    <div ng-repeat="question in quiz" id="question{{question.id}}" class="message question">
                                        <div class="question-content">
                                            <p><span class="question-number">Q{{$index+1}} {{question.text}}</span></p>
                                        </div>
                                        <div  class="options">
                                            <div class="row">
                                                <div ng-repeat="opt in question.options" class="col-xs-12 col-sm-6 option{{opt.number}}">
                                                    <button class="button" ng-click="setAnswer($parent.question, opt)">{{opt.text}}
                                                        <i class="fa fa-check" aria-hidden="true"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <button ng-show="!isQuizSolved" ng-click="sendQuiz()" class="button button-green">SUBMIT</button>
                                    <div ng-show="isQuizSolved" class="showResult">
                                        <p>Correct Answer: <span ng-bind="correctAnswers"></span><br>
                                            Wrong Answer: <span ng-bind="wrongAnswers"></span>
                                        </p>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="partial/footer.jsp" />
        <script type="text/javascript" src="/js/controller/topicController.js"></script>
    </body>
        
</html>