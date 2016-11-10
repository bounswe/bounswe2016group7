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
        <div class="row">
          <div class="col-xs-12">
            <div class="panel">
              <div class="row">
                <div class="col-xs-5 col-md-3">
                  <div class="title-picture" style="background-image: url(/images/header1.jpg);"></div>
                  <p>Creator: <a href="#">John Doe</a><br/>
                    <a href="#">150 people are interested</a>
                  <p>
                </div>
                <div class="col-xs-7 col-md-9">
                  <h3>${topic.header} <div class="topic-rating"></div></h3>
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ac tortor nec leo egestas eleifend id ut lectus. In vel laoreet erat, ac cursus metus. Donec tempor luctus turpis, in consectetur felis. Nulla dolor ligula, tincidunt vel lorem at, pellentesque hendrerit justo. Aenean porttitor eleifend quam vitae rutrum. Pellentesque et dolor justo. Fusce nec ex nec erat placerat tincidunt et in nisi. Donec eros quam, sollicitudin et tincidunt eget, tincidunt non velit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae</p>
                  <div class="tags">
                    <div class="topic-tag">book</div>
                    <div class="topic-tag">write</div>
                    <div class="topic-tag">how to write</div>
                  </div>
                </div>  
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-xs-12 col-sm-12 col-md-4">
            <div class="panel">
              <div class="current-topic">
                <a href="#" class="title">${topic.header}</a>
                <a href="#" class="nav">
                  <i style="margin-right:5px;" class="fa fa-chevron-left" aria-hidden="true"></i>
                  Which fonts to use</a>
                <a href="#" class="nav">
                  Which fonts to use
                  <i style="margin-left:5px;" class="fa fa-chevron-right" aria-hidden="true"></i>
                </a>
              </div>
              <div class="recommended">
                <span class="title">Recommended</span>
                <a href="#" class="recommended-topic">Topic 1</a>
                <a href="#" class="recommended-topic">Topic 2</a>
              </div>
            </div>
          </div>
          <div class="col-xs-12 col-sm-12 col-md-8">
            <div class="panel topic-panel">
              <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                  <a class="nav-link active" data-toggle="tab" href="#home" role="tab" aria-controls="home">Materials</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-toggle="tab" href="#profile" role="tab" aria-controls="profile">Discussion</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-toggle="tab" href="#messages" role="tab" aria-controls="messages">Quiz</a>
                </li>
              </ul>

              <div class="tab-content">
                <div class="tab-pane active" id="home" role="tabpanel">
                  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ac tortor nec leo egestas eleifend id ut lectus. In vel laoreet erat, ac cursus metus. Donec tempor luctus turpis, in consectetur felis. Nulla dolor ligula, tincidunt vel lorem at, pellentesque hendrerit justo. Aenean porttitor eleifend quam vitae rutrum. Pellentesque et dolor justo. Fusce nec ex nec erat placerat tincidunt et in nisi. Donec eros quam, sollicitudin et tincidunt eget, tincidunt non velit. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vivamus neque quam, vehicula non tincidunt sed, vestibulum at ex. Duis finibus sem convallis bibendum facilisis. Donec pellentesque pellentesque aliquam. In tempor sapien non neque faucibus, at rutrum lorem gravida. Suspendisse at vehicula odio.</p>
                </div>
                <div class="tab-pane message-tab" id="profile" role="tabpanel">
                  <div class="message-container">
                    <div class="message">
                    <div class="message-point">
                      <i class="fa fa-chevron-up" aria-hidden="true"></i>
                      <span>10</span>
                      <i class="fa fa-chevron-down" aria-hidden="true"></i>
                    </div>
                    <div class="message-content">
                      <span class="title">
                        AydÄ±n Aksoy
                      </span>
                      <button type="button" class="float-button button-green" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
                      <p>Ä°yi bir yazarda olmasÄ± gereken Ã¶zellikler nelerdir?</p>
                      <span class="date">
                        08.11.2016
                      </span>
                    </div>
                    <div class="clearfix"></div>
</div>
                    <div class="message">
                    <div class="message-point">
                      <i class="fa fa-chevron-up" aria-hidden="true"></i>
                      <span>10</span>
                      <i class="fa fa-chevron-down" aria-hidden="true"></i>
                    </div>
                    <div class="message-content">
                      <span class="title">
                        AydÄ±n Aksoy
                      </span>
                      <button type="button" class="float-button button-green" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
                      <p>Ä°yi bir yazarda olmasÄ± gereken Ã¶zellikler nelerdir?</p>
                      <span class="date">
                        08.11.2016
                      </span>
                    </div>
                    <div class="clearfix"></div>
</div>
                    <div class="message">
                    <div class="message-point">
                      <i class="fa fa-chevron-up" aria-hidden="true"></i>
                      <span>10</span>
                      <i class="fa fa-chevron-down" aria-hidden="true"></i>
                    </div>
                    <div class="message-content">
                      <span class="title">
                        AydÄ±n Aksoy
                      </span>
                      <button type="button" class="float-button button-green" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
                      <p>Ä°yi bir yazarda olmasÄ± gereken Ã¶zellikler nelerdir?</p>
                      <span class="date">
                        08.11.2016
                      </span>
                    </div>
                    <div class="clearfix"></div>
</div>
                    <div class="message">
                    <div class="message-point">
                      <i class="fa fa-chevron-up" aria-hidden="true"></i>
                      <span>10</span>
                      <i class="fa fa-chevron-down" aria-hidden="true"></i>
                    </div>
                    <div class="message-content">
                      <span class="title">
                        AydÄ±n Aksoy
                      </span>
                      <button type="button" class="float-button button-green" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
                      <p>Ä°yi bir yazarda olmasÄ± gereken Ã¶zellikler nelerdir?</p>
                      <span class="date">
                        08.11.2016
                      </span>
                    </div>
                    <div class="clearfix"></div>
</div>
                    <div class="message">
                    <div class="message-point">
                      <i class="fa fa-chevron-up" aria-hidden="true"></i>
                      <span>10</span>
                      <i class="fa fa-chevron-down" aria-hidden="true"></i>
                    </div>
                    <div class="message-content">
                      <span class="title">
                        AydÄ±n Aksoy
                      </span>
                      <button type="button" class="float-button button-green" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
                      <p>Ä°yi bir yazarda olmasÄ± gereken Ã¶zellikler nelerdir?</p>
                      <span class="date">
                        08.11.2016
                      </span>
                    </div>
                    <div class="clearfix"></div>
</div>
                    <div class="message">
                    <div class="message-point">
                      <i class="fa fa-chevron-up" aria-hidden="true"></i>
                      <span>10</span>
                      <i class="fa fa-chevron-down" aria-hidden="true"></i>
                    </div>
                    <div class="message-content">
                      <span class="title">
                        AydÄ±n Aksoy
                      </span>
                      <button type="button" class="float-button button-green" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
                      <p>Ä°yi bir yazarda olmasÄ± gereken Ã¶zellikler nelerdir?</p>
                      <span class="date">
                        08.11.2016
                      </span>
                    </div>
                    <div class="clearfix"></div>
</div>
                    <div class="message">
                    <div class="message-point">
                      <i class="fa fa-chevron-up" aria-hidden="true"></i>
                      <span>10</span>
                      <i class="fa fa-chevron-down" aria-hidden="true"></i>
                    </div>
                    <div class="message-content">
                      <span class="title">
                        AydÄ±n Aksoy
                      </span>
                      <button type="button" class="float-button button-green" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
                      <p>Ä°yi bir yazarda olmasÄ± gereken Ã¶zellikler nelerdir?</p>
                      <span class="date">
                        08.11.2016
                      </span>
                    </div>
                    <div class="clearfix"></div>
</div>
                    <div class="message">
                    <div class="message-point">
                      <i class="fa fa-chevron-up" aria-hidden="true"></i>
                      <span>10</span>
                      <i class="fa fa-chevron-down" aria-hidden="true"></i>
                    </div>
                    <div class="message-content">
                      <span class="title">
                        AydÄ±n Aksoy
                      </span>
                      <button type="button" class="float-button button-green" title="Reply"><i class="fa fa-reply" aria-hidden="true"></i></button>
                      <p>Ä°yi bir yazarda olmasÄ± gereken Ã¶zellikler nelerdir?</p>
                      <span class="date">
                        08.11.2016
                      </span>
                    </div>
                    <div class="clearfix"></div>
</div>
                  </div>
                  <div class="input-container">
                    <input type="text" placeholder="Your message" class="form-control">
                    <button class="button button-green send-button"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                  </div>
                </div>
                <div class="tab-pane" id="messages" role="tabpanel">
                  <div class="message question">
                    <div class="question-content">
                      <p><span class="question-number">Q1</span> Dayak nedir neden atÄ±lÄ±r ?</p>
                    </div>
                    <div class="options">
                      <div class="row">
                        <div class="col-xs-12 col-sm-6">
                          <button class="button">option 1</button>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                          <button class="button selected">option 2</button>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                          <button class="button">option 3</button>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                          <button class="button">option 4</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <jsp:include page="partial/footer.jsp" />
    <script>
      $(".topic-rating").starRating({
        initialRating: 3.7,
        hoverColor: "#0a6c8e",
        useGradient: false,
        activeColor: "#fec400",
        readOnly: true,
        starSize: 25
      });
    </script>
  </body>
</html>