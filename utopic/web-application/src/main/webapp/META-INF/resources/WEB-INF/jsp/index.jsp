<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
     <head>
    <!-- Required meta tags always come first -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link href="/jsPlugins/tether/css/tether.min.css" rel="stylesheet" type="text/css"/>
    <link href="/jsPlugins/remodal/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link href="/jsPlugins/remodal/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/jsPlugins/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/custom.css" rel="stylesheet" type="text/css"/>
  </head><body ng-app="mainApp">
    <div class="remodal landing-modal" ng-controller="indexController" data-remodal-id="login-modal">
      <button data-remodal-action="close" class="remodal-close"></button>
				<br/>
        <button data-remodal-action="close" class="remodal-close"></button>
      <form name="login"  novalidate valdr-type="Login">
        <div valdr-form-group>
          <input name="username" id="username" class="form-control" type="text" placeholder="Username or Email" ng-model="user.username"/>
        </div>
        <div valdr-form-group>
          <input name="password" id="password" class="form-control" type="password" placeholder="Password" ng-model="user.password"/>
        </div>
        <button type="submit" data-remodal-action="confirm" class="button button-green">LOG IN</button>
      </form>   
    </div>
    <div class="remodal landing-modal" ng-controller="indexController" data-remodal-id="register-modal">
      <button data-remodal-action="close" class="remodal-close"></button>
      <form name="register"  novalidate valdr-type="Register">
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
          <span>I am a/an...</span>
          <div class="status-container">
            <label for="status2">Creator</label>
            <input type="radio" class="form-control" name="status" id="status2">
          </div>
          <div class="status-container">
            <label for="status1">Explorer</label>
            <input type="radio" class="form-control" name="status" id="status1">
          </div>
          <div class="clearfix"></div>
        </div>
        <button type="submit" data-remodal-action="confirm" class="button button-green">SIGN UP</button>
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
      <script src="/js/jquery-3.1.0.min.js" type="text/javascript"></script>
    <!--<script type="text/javascript" src="js/jqueryUi/jquery-ui.js"></script>-->
    <script src="/js/angular.js" type="text/javascript"></script>
    <script src="/jsPlugins/valdr/valdr.min.js" type="text/javascript"></script>
    <script src="/jsPlugins/valdr/valdr-message.min.js" type="text/javascript"></script>
    <script src="/js/model/mainModel.js" type="text/javascript"></script>
    <script src="/js/controller/indexController.js" type="text/javascript"></script>
    <script src="/jsPlugins/tether/js/tether.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/jsPlugins/remodal/remodal.min.js" type="text/javascript"></script>
  </body>
   <!-- <body>
        <form method="post" action="/login">
            <center>
            <table border="1" width="30%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2">Login Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="uname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="pass" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Login" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">Yet Not Registered!! <a href="/login">Register Here</a></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
    </body> -->
</html> 