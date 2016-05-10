<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="charset=ISO-8859-1">
<title>Search Results</title>
<style type="text/css">
	*{
		outline:none !important;
	}
	body,html{
		padding:0;
		margin:0;
	}
	
	body{
	    background-image: url("https://static.pexels.com/photos/30732/pexels-photo-30732.jpg");
	    background-position: center bottom;
	    background-size: cover;
    	padding: 20px 0;
    	background-repeat: no-repeat;
    	box-sizing: border-box;
    	min-height:100vh;
    	font-family: sans-serif;
    	margin-bottom:50px;
    }
	
	.results{
	    padding: 20px;
	    margin: 0 20px auto;
	    background: #fff;
	    border-radius: 4px;
	    -webkit-box-shadow: 0px 0px 5px 0px rgba(0,0,0,0.75);
		-moz-box-shadow: 0px 0px 5px 0px rgba(0,0,0,0.75);
		box-shadow: 0px 0px 5px 0px rgba(0,0,0,0.75);
    }
	
	.content{
		display:flex;
		justify-content:center;
		flex-direction:column;
		height:100vh;
	}
	
	.content h3{
	    text-align: center;
	    font-size: 31px;
	    color: #fff;
	    font-family: sans-serif;
	    text-shadow:0px 1px 2px rgba(0, 0, 0, 1);
	}
	
	.search-bar form {
	    width: 50%;
	    margin: 0 auto;
	    display: flex;
	    border-radius: 3px;
	    border: 6px solid rgba(0, 0, 0, 0.36);
	}
	.search-bar form input {
	    flex-basis: 300px;
	    flex-grow: 10;
	    flex-shrink:10;
	    height: 35px;
	    padding: 0 10px;
	    box-sizing: border-box;
	    font-size: 16px;
	    border: 1px solid #E6E6E6;
	    min-width:0;
	}
	.search-bar form button{
		flex-basis:50px;
		flex-grow:1;
		flex-shrink:1;
		background: #8BC34A;
	    border: none;
	    color: #fff;
	    cursor: pointer;
	    font-size:20px;
	}
	.result {
	    display: flex;
	    padding	: 5px;
	    border-bottom: 1px solid rgba(130, 144, 157, 0.16);
	    align-items: center;
	}
	.result input[type="checkbox"] {
	    flex-basis: 5%;
	}
	.result span {
	    flex-basis: 80%;
	}
	.result div {
	    background: #00BCD4;
	    border: none;
	    border-radius: 3px;
	    color: #fff;
	    padding: 10px;
	    cursor: pointer;
	    text-align: center;
	}
	.save-container{
	    position: fixed;
	    left: 0;
	    right: 0;
    	background: rgb(212, 212, 212);
	    bottom: 0;
	    padding: 10px;
   		z-index: 10;
	}
	#save {
	    flex-basis: 50px;
	    flex-grow: 1;
	    flex-shrink: 1;
	    background: #8BC34A;
	    border: none;
	    color: #fff;
	    cursor: pointer;
	    padding: 5px 10px;
	    float: right;
	    border-radius: 3px;
	}
	#mapView{
		width:100%;
		height:300px;
	}
	.result.active{
		background:#efefef;
	}
</style>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div class="results">
	  <h3>Found ${list.size()} Results</h3>
	  <form method="post">
	  	  <input type="hidden" name="action" value="save"/>
	  	  <input type="hidden" name="count" value="${list.size()}"/>
	      <c:forEach items="${list}" var="theater"> 
	      <div class="result">
	      	<input name="check-${theater.getNum()}" type="checkbox"/ >
	      	<span>${theater.getName()}</span>
	      	<input type="hidden" name="name-${theater.getNum()}" value="${theater.getName()}"/>
	      	<div class="show-map">Show on Map</div>
	      	<input name="lat-${theater.getNum()}" class="lat" type="hidden" value="${theater.getLat()}"/>
	      	<input name="lng-${theater.getNum()}" class="lng" type="hidden" value="${theater.getLng()}"/>
	      </div>                  
	      </c:forEach>  
	      <div class="save-container">
	      	<span>You can save search results by checking and clicking save button</span>
	      	<input id="save" type="submit" value="Save">
	      </div>
     	</form>
     	<div id="mapView">
     	</div>
	</div>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js" async defer></script>
<script>
	var clicked=false;
	$(document).ready(function(){
		$('#mapView').hide();
	});
	
	$(document).on('click','.show-map',function(){
		$('.active').removeClass('active');
		$(this).parent('.result').addClass("active");
		var lat1 = $(this).siblings('.lat').val();
		var lng1 = $(this).siblings('.lng').val();
		var title1 = $(this).siblings('span')[0].innerHTML;
		if(clicked==false){
			$('#mapView').show();
			map = new google.maps.Map(document.getElementById('mapView'), {
	          center: {lat: parseFloat(lng1), lng: parseFloat(lat1)},
	          zoom: 12,
	          disableDefaultUI: true
	        });

			var marker = new google.maps.Marker({
			    position: {lat:  parseFloat(lng1), lng: parseFloat(lat1)},
			    map: map,
			    title: title1
			  });
			map.checkResize();
		}else{
			map.setCenter(new google.maps.LatLng(parseFloat(lng1), parseFloat(lat1)));
			var marker = new google.maps.Marker({
			    position: {lat:  parseFloat(lng1), lng: parseFloat(lat1)},
			    map: map,
			    title: title1
			});
			map.checkResize();
		}
	});
</script>
</html>