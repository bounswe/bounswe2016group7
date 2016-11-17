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
                                    <div class="title-picture" style="background-image: url('./images/header1.jpeg');"></div>
                                    <p></p>
                                </div>
                                <div class="col-xs-7 col-md-9">
                                    <h3>${profiledUser.firstname} ${profiledUser.lastname}<span></span></h3>
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