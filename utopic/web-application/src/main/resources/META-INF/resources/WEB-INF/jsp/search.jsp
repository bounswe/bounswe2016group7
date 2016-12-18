<%-- 
    Document   : search
    Created on : Dec 10, 2016, 5:13:20 PM
    Author     : necil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <jsp:include page="partial/cssFiles.jsp" />
    </head>
    <body ng-app="mainApp">
       
       <script type="text/javascript">
            var auth = [];
            <c:forEach items="${authorities}" var="stdn" varStatus="status">
                    auth.push('${stdn.name}');
            </c:forEach>
            var topics = ${topics}
        </script>

    <jsp:include page="partial/getToolbar.jsp"/>
    <jsp:include page="partial/creatorModal.jsp"/>
    <div class="content" ng-controller="searchController">
            <div class="container">
                <div class="search-container">
                    <div ng-repeat="topic in topics" class="search-item panel">
                        <div class="row">
                            <div class="col-xs-12 col-sm-4 col-md-3">
                                <div style="background-image:url('/images/header1.jpg');" class="search-image"></div>
                            </div>
                            <div class="col-xs-12 col-sm-8 col-md-9">
                                <h4>
                                    <span ng-bind="topic.header"></span>
                                    
                                    <span class="rate-text">
                                        <div class="topic-rating" id="topic-rating-{{topic.id}}"></div>
                                        <span>({{topic.rate}})</span>
                                    </span>
                                </h4>
                                <p ng-bind="topic.description"></p>
                                <span>Comments: <span ng-bind="topic.commentNumber"></span></span>
                            </div>
                        </div>
                        <a href="/topic/{{topic.id}}"></a>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="partial/footer.jsp" />
        <script type="text/javascript" src="/js/controller/searchController.js"></script>
    </body>
</html>
