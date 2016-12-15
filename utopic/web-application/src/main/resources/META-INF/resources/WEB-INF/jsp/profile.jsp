<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/cssFiles.jsp" />
    </head>
    <body ng-app="mainApp" ng-controller="userController">
        <% if(session.getAttribute("username") != null){
        %><script type="text/javascript">
                    var auth = [];
            <c:forEach items="${authorities}" var="stdn" varStatus="status">
                    auth.push('${stdn.name}');
            </c:forEach>
                    var activeUsername = "${sessionScope.username}";
                    var activeId = ${userId};
                    var activeToken = "${sessionScope.token}";
                    var reviews = ${reviews};
                    var ownerId = ${profiledUser.id};
                    var profiledUserAuth =  [];
                    <c:forEach items="${profiledUser.authorities}" var="stdn" varStatus="status">
                        profiledUserAuth.push('${stdn.name}');
                    </c:forEach>
                    var topics = ${topics};
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
                <div class ="row">
                    <div class ="col-xs-12">
                        <div class="panel">
                            <div class="row">
                                <div class="col-xs-5 col-md-3">
                                    <div class="title-picture" style="background-image: url({{profilePicture}});">
                                        <div ng-if="ownerId == currentUserId"  class="change-picture">
                                            <i class="fa fa-camera" aria-hidden="true"></i>
                                            <br/>
                                            Change Picture
                                            <form id="picture-form" enctype="multipart/form-data">
                                                <label for="update-picture">upload</label>
                                                <input id="update-picture" type="file" name="file" accept="image/*">
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-7 col-md-9">
                                    <h3>${profiledUser.firstname} ${profiledUser.lastname}<span></span></h3>
                                    <p>Association will be shown here.</p>
                                    <div class="tags">
                                        <div class="topic-tag">Badge 1</div>
                                        <div class="topic-tag">Badge 2</div>
                                        <div class="topic-tag">Badge 3</div>		
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-3">
                        <div class="panel">
                            User information will be shown here.
                        </div>	
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-9">
                        <div class="panel profile-panel">
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#home" role="tab" aria-controls="home">Quiz Progress</a>
                                </li>
                                <li  ng-if="profiledAuths.indexOf('ROLE_CREATOR')!=-1" class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#profile" role="tab" aria-controls="profile">Topics</a>
                                </li>
                                <li  ng-if="profiledAuths.indexOf('ROLE_CREATOR')!=-1" class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#messages" role="tab" aria-controls="messages">Reviews</a>
                                </li>
                            </ul>

                            <div class="tab-content">
                                <div class="tab-pane active" id="home" role="tabpanel">
                                    <p>QUIZ PROGRESS</p>
                                </div>
                                <div  ng-if="profiledAuths.indexOf('ROLE_CREATOR')!=-1" class="tab-pane message-tab" id="profile" role="tabpanel">
                                    <div class="message-container">
                                        <div ng-if="!topics" class="centered">
                                            <p>There is no topics yet<br/>Create one!</p>
                                        </div>
                                        <div ng-repeat="topic in topics" class="message" id="topic{{topic.id}}">
                                            <div class="message-point">
                                                <i class="fa fa-picture-o fa-4x" aria-hidden="true"></i>
                                            </div>
                                            <div class="message-content">
                                                <a href="/topic/{{topic.topicId}}" class="title">
                                                    {{topic.header}}
                                                </a>
                                                <p ng-bind-html="topic.description | to_trusted"></p>
                                                <span class="date">
                                                    {{topic.createDate}}
                                                </span>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </div>
                                <div  ng-if="profiledAuths.indexOf('ROLE_CREATOR')!=-1" class="tab-pane message-tab" id="messages" role="tabpanel">
                                    <div class="message-container">
                                        <div ng-if="!reviews" class="centered">
                                            <p>There is no reviews yet<br/>Be the first</p>
                                        </div>
                                        <div ng-repeat="review in reviews" class="message" id="review{{review.id}}">
                                            <div class="message-point">
                                            </div>
                                            <div class="message-content">
                                                <a href="/profile/{{review.reviewerId}}" class="title">
                                                    {{review.reviewerName}}
                                                </a>
                                                <p ng-bind-html="review.text | to_trusted"></p>
                                                <span class="date">
                                                    {{review.dateCreated}}
                                                </span>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                    <div class="input-container">
                                        <input type="text" ng-model="reviewToAdd" placeholder="Your message" class="form-control">
                                        <button class="button button-green send-button" ng-click="addReview()"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="partial/footer.jsp" />
        <script type="text/javascript" src="/js/controller/userController.js"></script>
    </body>
</html>
