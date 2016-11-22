<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="partial/cssFiles.jsp" />
    </head>
    <body ng-app="mainApp">
    <% if(session.getAttribute("username") != null){
    %><script type="text/javascript">
        var activeUsername = "${sessionScope.username}";
        var activeId = "${sessionScope.id}";
        var activeToken = "${sessionScope.token}";
				var reviews = ${reviewList};
        var topics = ${topicList};
    </script><%
        }else{
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", "/"); 
       }    
    %>
        <jsp:include page="partial/getToolbar.jsp"/>
        <div class="content">
            <div class="container-fluid">
                <div class ="row">
                    <div class ="col-xs-12">
                        <div class="panel">
                            <div class="row">
                                <div class="col-xs-5 col-md-3">
                                    <div class="title-picture" style="background-image: url(/images/user.png);"></div>
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
            </div>
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-3">
                    <div class="panel">
													User information will be shown here.
                    </div>	
                </div>
                <div class="col-xs-12 col-sm-12 col-md-9">
                    <div class="panel">
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#home" role="tab" aria-controls="home">Quiz Progress</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#profile" role="tab" aria-controls="profile">Topics</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#messages" role="tab" aria-controls="messages">Reviews</a>
                            </li>
                        </ul>
                            
                        <div class="tab-content">
                            <div class="tab-pane active" id="home" role="tabpanel">
                                <p>QUIZ PROGRESS</p>
                            </div>
                            <div class="tab-pane message-tab" id="profile" role="tabpanel">
                                <P>TOPIC LIST</P>
                            </div>
                            <div class="tab-pane" id="messages" role="tabpanel">
																<div class="message-container">
																		<div ng-if="!reviews" class="centered">
																				<p>There is no reviews yet<br/>Be the first</p>
																		</div>
																	<div ng-repeat="review in reviews" class="message" id="review{{review.Id}}">
																		<div class="message-point">
																			<i class="fa fa-chevron-up" aria-hidden="true"></i>
																			<span>{{reviews.Rating}}</span>
																			<i class="fa fa-chevron-down" aria-hidden="true"></i>
																		</div>
																		<div class="message-content">
																				<a href="/profile/{{review.UserId}}" class="title">{{reviews.Username}}</a>
																				<button type="button" class="float-button button-green" name='{{review.Username}}' slideid="review{{review.Id}}"  ng-click="reply($event)" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
																				<p ng-bind-html="review.Content | to_trusted"></p>
																			<span class="date">{{review.CreateDate}}</span>
																		</div>
																		<div class="clearfix"></div>
																	</div>
																</div>
																<div class="input-container">
																	<input type="text" ng-model="commentToAdd" placeholder="Your review" class="form-control">
																	<button class="button button-green send-button" ng-click="addComment()"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
																</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="js/jqueryUi/jquery-ui.js"></script>
        <script type="text/javascript" src="js/angular.js"></script>
        <script type="text/javascript" src="jsPlugins/valdr/valdr.js"></script>
        <script type="text/javascript" src="jsPlugins/valdr/valdr-message.min.js"></script>
        <script type="text/javascript" src="js/model/mainModel.js"></script>
        <script type="text/javascript" src="js/controller/indexController.js"></script>
        <script type="text/javascript" src="jsPlugins/tether/js/tether.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="jsPlugins/remodal/remodal.min.js"></script>
    </body>
</html>