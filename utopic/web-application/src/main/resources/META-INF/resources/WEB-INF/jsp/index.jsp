<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="partial/cssFiles.jsp" />
    </head>
    <body ng-app="mainApp">
        <jsp:include page="partial/getToolbar.jsp" />
        <div class="content no-margin">
            <div class="header">
                <div class="header-action">
                    <h1>${error}</h1>
                    <h1>AWESOME WORDS CATCHES USER</h1>
                    <h3>MORE AWESOME WORDS</h3>
                    <button data-remodal-target="register-modal" class="button button-orange">SIGN UP NOW</button>
                </div>
            </div>
            <div class="container" id="landing-content-container">
                <div class="panel">
                    <div class="panel-header">
                        <span class="title">Trending Topics</span>
                        <a href="#">>> See all</a>
                    </div>
                    <div class="row">
                        <div class="col-xs-6 col-sm-4 col-md-3 panel-column">
                            <div class="topic-thumb">
                                <p class="topic-title">Topic 1</p>
                                <span>Discussions: 15</span>
                                <span>Comments: 12</span>
                                <span>Interested: 5</span>
                                <a href="#">Topic Page</a>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-md-3 panel-column">
                            <div class="topic-thumb">
                                <p class="topic-title">Topic 1</p>
                                <span>Discussions: 15</span>
                                <span>Comments: 12</span>
                                <span>Interested: 5</span>
                                <a href="#">Topic Page</a>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-md-3 panel-column">
                            <div class="topic-thumb">
                                <p class="topic-title">Topic 1</p>
                                <span>Discussions: 15</span>
                                <span>Comments: 12</span>
                                <span>Interested: 5</span>
                                <a href="#">Topic Page</a>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-4 col-md-3 panel-column">
                            <div class="topic-thumb">
                                <p class="topic-title">Topic 1</p>
                                <span>Discussions: 15</span>
                                <span>Comments: 12</span>
                                <span>Interested: 5</span>
                                <a href="#">Topic Page</a>
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