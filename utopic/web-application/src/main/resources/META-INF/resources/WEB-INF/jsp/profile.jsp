<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags always come first -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <link rel="stylesheet" href="jsPlugins/tether/css/tether.min.css"/>
    <link rel="stylesheet" href="jsPlugins/remodal/remodal-default-theme.css"/>
    <link rel="stylesheet" href="jsPlugins/remodal/remodal.css"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="jsPlugins/font-awesome-4.6.3/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="css/custom.css"/>
  </head>
  <body ng-app="mainApp">
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
    <div class="content">
			<div class="container-fluid">
				<div class ="row">
					<div class ="col-xs-12">
						<div class="panel">
							<div class="row">
                <div class="col-xs-5 col-md-3">
									<div class="title-picture" style="background-image: url('./images/header1.jpeg');"></div>
									<p></p>
									
								</div>
								<div class="col-xs-7 col-md-9">
									<h3>ROB BOSS<span></span></h3>
									<p>American Painters Association</p>
									<div class="badges"></div>
										<div class="topic-tag">Wizard</div>
                    <div class="topic-tag">Adept Creator</div>
                    <div class="topic-tag">Unmarked</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-3">
					<div class="panel">
						I am an American painter and a registered art instructor at American Painters Association. I am 52 years old. I am painting for 30+ yeards and my motto is "There is no mistakes, there is only happy accidents.".
						
						Email: roboss@art.info
						Phone: +987 65 58
						Website: roboss.art.net
					</div>	
				</div>
				<div class="col-xs-12 col-sm-12 col-md-9">
            <div class="panel">
              <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                  <a class="nav-link active" data-toggle="tab" href="#home" role="tab" aria-controls="home">Quiz Progress</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-toggle="tab" href="#profile" role="tab" aria-controls="profile">My Topics</a>
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
									<p>REVIEWS</p>
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