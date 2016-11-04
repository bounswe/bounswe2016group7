<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <jsp:include page="partial/header.jsp" />
    <body ng-app="mainApp">
        <div class="remodal landing-modal" ng-controller="indexController" data-remodal-id="login-modal">
            <button data-remodal-action="close" class="remodal-close"></button>
            <br/>
            <button data-remodal-action="close" class="remodal-close"></button>
            <form action="/login" method="POST" name="login"  novalidate valdr-type="Login">
                <div valdr-form-group>
                    <input name="username" id="username" class="form-control" type="text" placeholder="Username or Email" ng-model="user.username"/>
                </div>
                <div valdr-form-group>
                    <input name="password" id="password" class="form-control" type="password" placeholder="Password" ng-model="user.password"/>
                </div>
                <button type="submit" class="button button-green">LOG IN</button>
            </form>   
        </div>
        <div class="remodal landing-modal" ng-controller="indexController" data-remodal-id="register-modal">
            <button data-remodal-action="close" class="remodal-close"></button>
            <form action="/register" method="POST" name="register"  novalidate valdr-type="Register">
                <div valdr-form-group>
                    <input name="firstname" id="firstname" class="form-control" type="text" placeholder="Firstname" ng-model="user.firstname"/>
                </div>
                <div valdr-form-group>
                    <input name="lastname" id="lastname" class="form-control" type="text" placeholder="Lastname" ng-model="user.lastname"/>
                </div>
                <div valdr-form-group>
                    <input name="username" id="username" class="form-control" type="text" placeholder="Username" ng-model="user.username"/>
                </div>
                <div valdr-form-group>	
                    <input name="email" id="email" class="form-control" type="text" placeholder="Email" ng-model="user.email"/>
                </div>
                <div valdr-form-group>	
                    <input name="password" id="password" class="form-control" type="password" placeholder="Password" ng-model="user.password"/>
                </div>
                <div valdr-form-group>	
                    <h1 id="selam" ng-bind="user.password"></h1>
                    <input name="repassword" id="repassword" class="form-control" type="password" ng-model="repassword" placeholder="Re-Password"/>
                </div>
                <div class="form-group" id="register-status">
                    <span>Gender</span>
                    <div class="status-container">
                        <label for="status3">Male</label>
                        <input type="radio" class="form-control" name="gender" value="m" id="status3">
                    </div>
                    <div class="status-container">
                        <label for="status4">Female</label>
                        <input type="radio" class="form-control" name="gender" value="f" id="status4">
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="form-group" id="register-status">
                    <span>I am a/an...</span>
                    <div class="status-container">
                        <label for="status2">Creator</label>
                        <input type="radio" class="form-control" name="status" value="creator" id="status2">
                    </div>
                    <div class="status-container">
                        <label for="status1">Explorer</label>
                        <input type="radio" class="form-control" name="status" value="explorer" id="status1">
                    </div>
                    <div class="clearfix"></div>
                </div>
                <button type="submit" class="button button-green">SIGN UP</button>
            </form>
          
        </div>
        <nav class="navbar navbar-fixed-top navbar-dark" ng-controller="indexController">
            <div class="container">
                <div class="toggle" ng-click="toggleMenu()">
                    <i class="fa fa-bars" aria-hidden="true"></i>
                </div>
                <a class="navbar-brand" href="#"><img src="images/logo.png"/></a>
                <div id="search-container">
                    <form>
                        <input type="text" placeholder="Search"/>
                        <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                    </form>
                </div>
                <ul class="nav navbar-nav pull-md-right">
                    <li><a class="nav-item nav-link" href="#login-modal">LOG IN</a></li>
                    <li><a href="#register-modal" class="nav-item nav-link button button-orange">SIGN UP</a></li>
                </ul>
            </div>
        </nav>
        <div class="content no-margin">
            <div class="header">
                <div class="header-action">
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