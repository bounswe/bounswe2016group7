<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <% if(session.getAttribute("username") != null){
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "/home"); 
            }   
        %>
        <jsp:include page="partial/cssFiles.jsp" />
    </head>
    <body ng-app="mainApp">
        <script type="text/javascript">
            var recentTopics = ${recentTopics};
						var topTopics = ${topTopics};
						var auth = [];
        </script>
        <jsp:include page="partial/getToolbar.jsp" />
        <div class="content no-margin">
      <div class="header">
        <div class="header-action">
          <h1>LEARNING NEW THINGS MADE EASY.</h1>
          <h3>EXPERIENCE IT NOW.</h3>
          <button data-remodal-target="register-modal" class="button button-orange">SIGN UP NOW</button>
        </div>
      </div>
      <div class="container" id="landing-content-container">
        <div class="panel">
          <div class="panel-header">
            <span class="title">Recent Topics</span>
            <a href="#">>> See all</a>
          </div>
          <div class="row" ng-controller="indexController">
            <div ng-repeat="topic in recentTopics" class="col-xs-6 col-sm-4 col-md-3 panel-column">
              <div class="topic-thumb">
                <p class="topic-title">{{topic.topic_name}}</p>
                <span>Comments: {{topic.commentNumber}}</span>
                <a href="/topic/{{topic.topic_id}}">Topic Page</a>
                <div class="clearfix"></div>
              </div>
            </div>
          </div>
        </div>
        <div class="panel">
          <div class="panel-header">
            <span class="title">Trending Topics</span>
            <a href="#">>> See all</a>
          </div>
          <div class="row" ng-controller="indexController">
            <div ng-repeat="topic in topTopics" class="col-xs-6 col-sm-4 col-md-3 panel-column">
              <div class="topic-thumb">
                <p class="topic-title">{{topic.topic_name}}</p>
                <span>Comments: {{topic.commentNumber}}</span>
                <a href="/topic/{{topic.topic_id}}">Topic Page</a>
                <div class="clearfix"></div>
              </div>
            </div>
          </div>
        </div>				
        <div class="panel">
          <div class="panel-header centered">
            <span class="title">Categories</span>
          </div>
          <ul class="categories">
            <li><a href="#">Art</a></li>
            <li><a href="#">Sports</a></li>
            <li><a href="#">Technology</a></li>
            <li><a href="#">Photography</a></li>
            <li><a href="#">Music</a></li>
            <li><a href="#">History</a></li>
          </ul>
          <div class="centered">
            <input id="topic-search" type="text" class="form-control" placeholder="Search Other Topics">
          </div>
          <div class="row">
          </div>
        </div>
      </div>
    </div>
    <jsp:include page="partial/footer.jsp" />
    <script src="/js/controller/indexController.js" type="text/javascript"></script>
    </body>
</html> 