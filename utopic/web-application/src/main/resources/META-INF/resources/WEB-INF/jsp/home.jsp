<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="partial/cssFiles.jsp" />
    </head>
    <body ng-app="mainApp" ng-controller="indexController">
        <% if(session.getAttribute("username") != null){
        %><script type="text/javascript">
            var auth = [];
            <c:forEach items="${authorities}" var="stdn" varStatus="status">
            auth.push('${stdn.name}');
            </c:forEach>
            var activeUsername = "${sessionScope.username}";
            var activeId = "${sessionScope.id}";
            var activeToken = "${sessionScope.token}";
            var recentTopics = ${recentTopics};
            var topTopics = ${topTopics};
            var followedTopics = ${interestedTopics};
        </script><%
            }else{
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", "/"); 
           }    
        %>
        <jsp:include page="partial/getToolbar.jsp"/>
        <jsp:include page="partial/creatorModal.jsp"/>
        <div class="content">
            <div class="container">
                <div class="panel">
                    <div class="row">
                        <div class="col-md-6 col-xs-12">
                            <div class="panel-header">
                                <span class="title">Interested Topics</span>
                                <a href="/show/3">>> See all</a>
                            </div>
                            <div ng-repeat="topic in getUserFollowedTopics">
                                <a href="/topic/{{topic.topicId}}" class="interested-topics">
                                    <h5 ng-bind="topic.header"></h5>
                                    <p ng-bind="topic.description"></p>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12">
                            
                        </div>
                    </div>
                </div>
                <div class="panel">
                    <div class="panel-header">
                        <span class="title">Recent Topics</span>
                        <a href="/show/2">>> See all</a>
                    </div>
                    <div class="row">
                        <div ng-repeat="topic in recentTopics" class="col-xs-6 col-sm-4 col-md-3 panel-column">
                            <div class="topic-thumb">
                                <p class="topic-title">{{topic.topic_name}}</p>
                                <span>Rating: {{topic.rate}}</span>
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
                        <a href="/show/1">>> See all</a>
                    </div>
                    <div class="row">
                        <div ng-repeat="topic in topTopics" class="col-xs-6 col-sm-4 col-md-3 panel-column">
                            <div class="topic-thumb">
                                <p class="topic-title">{{topic.topic_name}}</p>
                                <span>Rating: {{topic.rate}}</span>
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
        <script>var sessionToken = "${token}"</script>
    </body>
</html> 