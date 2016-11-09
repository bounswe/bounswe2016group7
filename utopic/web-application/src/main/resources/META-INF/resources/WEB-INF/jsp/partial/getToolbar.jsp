
<% if(session.getAttribute("username") != null){
    %><jsp:include page="toolbar.jsp"/><%
    }else{
    %><jsp:include page="visitorToolbar.jsp"/><%
   }    
%>