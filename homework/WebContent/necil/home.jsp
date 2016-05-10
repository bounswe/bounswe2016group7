<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="charset=ISO-8859-1">
<title>Necil Albayrak Homework</title>
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
</style>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<div id="background">
	</div>
	<div class="content">
		<h3>Find Theaters Near City Centers</h3>
		<div class="search-bar">
			<form method="post">
				<input name="keyword" type="text" placeholder="City Name"/>
				<input name="radius" type="text" placeholder="Search Radius (km)"/>
				<input name="action" type="hidden" value="search">
				<button type="submit">&#128269;</button>
			</form>
		</div>
	</div>
</body>
</html>