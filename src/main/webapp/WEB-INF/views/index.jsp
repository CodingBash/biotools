<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- 
Spring mvc form tags
spring form object and validation
biojava DNA reverse complement
study more on biojava analysis tools
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" />
<link href="<c:url value="resources/custom/css/style.css" />" rel="stylesheet" />
<title>Reverse Complement Application</title>
</head>
<body class="grey-background">
	<div class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header pull-left">
				<button type="button" class="navbar-toggle pull-left" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand pull-right" href="#">Bash</a>
			</div>
			<div class="navbar-collapse collapse" id="myNavbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-pencil"></span> Getting started</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-wrench"></span> Other tools</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-user"></span> About the author</a></li>
					<li><a href="#"><span class="glyphicon glyphicon-usd"></span> Donations</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="jumbotron white-background">
		<h1 class="text-center">Reverse Complement Tool</h1>
		<h2 class="text-center">Compute the reverse complement of a nucleotide sequence</h2>
		<h3 class="text-center">See other tools here</h3>
		<button class="btn center-block header-btn" href="#">Tools</button>
	</div>
	<div class="container-fluid white-background">
		<h2 class="text-center">Paste your sequence into the field and press submit</h2>
	</div>
	<div class="container-fluid white-background">
		<!-- form -->
		<form action="submitSequence.do" method="post" class="text-center" id="sequenceForm">
			<textarea name="sequence" rows="4" cols="50"><c:out value="${oldSequence}" default=""/></textarea>
			<!-- <input type="text" name="sequence" value="<c:out value="${sequence}" default=""/>"/> -->
			<input type="submit" value="Submit!" />
		</form>
		<c:if test="${not empty sequence}">
			<div class="result text-center">
				<h3>The reverse complement is</h3>
				<p>
					<c:out value="${sequence}" />
				</p>
			</div>
		</c:if>
	</div>
</body>
</html>