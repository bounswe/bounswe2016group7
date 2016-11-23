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
                    var activeUsername = "${sessionScope.username}";
                    var activeId = "${sessionScope.id}";
                    var activeToken = "${sessionScope.token}";
                    var comments = ${comments};
                    var ownerId = ${owner.id};
                    var topicId = ${topic.topicId};
                    var topicTags = ${tags};
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
                                    <div class="title-picture" style="background-image: url(/images/header1.jpg);"></div>
                                    <p>Creator: <a href="/profile/${owner.id}">${owner.username}</a><br/>
                                        <a href="#">Interested people will be shown here.</a>
                                    <p>
                                </div>
                                <div class="col-xs-7 col-md-9">
                                    <h3>${topic.header} <div class="topic-rating"></div></h3>
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
                                <a href="#" class="title">${pack.name}</a>
                                <a href="#" class="nav">
                                    <i style="margin-right:5px;" class="fa fa-chevron-left" aria-hidden="true"></i>
                                    Previous Topic in Pack</a>
                                <a href="#" class="nav">
                                    Next Topic in Pack
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
                                    <a class="nav-link" data-toggle="tab" href="#messages" role="tab" aria-controls="messages">Quiz</a>
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
                                                <i class="fa fa-chevron-up" aria-hidden="true"></i>
                                                <span>{{comment.Rating}}</span>
                                                <i class="fa fa-chevron-down" aria-hidden="true"></i>
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
                                <div class="tab-pane" id="messages" role="tabpanel">
                                    <div class="message question">
                                        <div class="question-content">
                                            <p><span class="question-number">Q1</span> Sample Question</p>
                                        </div>
                                        <div class="options">
                                            <div class="row">
                                                <div class="col-xs-12 col-sm-6">
                                                    <button class="button">option 1</button>
                                                </div>
                                                <div class="col-xs-12 col-sm-6">
                                                    <button class="button selected">option 2</button>
                                                </div>
                                                <div class="col-xs-12 col-sm-6">
                                                    <button class="button">option 3</button>
                                                </div>
                                                <div class="col-xs-12 col-sm-6">
                                                    <button class="button">option 4</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="partial/footer.jsp" />
        <script type="text/javascript" src="/js/controller/topicController.js"></script>
        <script>
                                                    $(".topic-rating").starRating({
                                                        initialRating: ${topic.rate},
                                                        hoverColor: "#0a6c8e",
                                                        useGradient: false,
                                                        activeColor: "#fec400",
                                                        readOnly: true,
                                                        starSize: 25
                                                    });
        </script>
    </body>
</html>