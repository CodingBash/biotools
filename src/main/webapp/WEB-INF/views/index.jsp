<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- 
Spring mvc form tags
spring form object and validation
biojava DNA reverse complement
study more on biojava analysis tools

why does two textareas switch automatically?
Dont show replace when there is no reverse component
Dont have two columns when there is no 
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" />
<link href="<c:url value="resources/custom/css/style.css" />" rel="stylesheet" />
<title>Reverse Complement Application</title>
</head>
<body class="white-background">
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
		<button class="btn center-block header-btn">Tools</button>
	</div>
	<div class="container-fluid white-background">
		<div class="row">
			<div class="col-md-6">
				<h2 class="text-center title">Paste your sequence into the field and press submit</h2>
				<!-- form -->
				<form action="submitSequence.do" method="post" class="text-center form-horizontal"
					id="sequenceForm" role="form">
					<div class="form-group">
						<label for="sequence-input" class="sr-only control-label">Sequence</label>
						<textarea name="sequence" rows="20" cols="75" id="sequence-input"><c:out
								value="${oldSequence}" default="" /></textarea>
						<!-- <input type="text" name="sequence" value="<c:out value="${sequence}" default=""/>"/> -->
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<button class="btn btn-default center-block" id="copy-analyzed">Replace</button>
				<button class="btn btn-default center-block" id="sequence-save">Save</button>
			</div>
			<c:if test="${not empty sequence}">
				<div class="col-md-6">
					<div class="result text-center">
						<h2 class="title">The reverse complement is</h2>
						<p id="sequence">
							<textarea name="sequence-holder" rows="20" cols="75" readonly>${sequence}</textarea>
						</p>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<div class="container-fluid white-background">
		<table class="table table-hover table-striped">
			<tr>
				<th>Sequence #</th>
				<th>Sequence</th>
				<th></th>
				<th></th>
			</tr>
			<c:if test="${not empty container}">
				<c:forEach items="${container}" var="sequenceItem" varStatus="iterator">
					<tr>
						<td>#${iterator.index + 1}</td>
						<td>${sequenceItem}</td>
						<td><button class="btn btn-info sequence-edit" value="${iterator.index}">EDIT</button></td>
						<td><button class="btn btn-danger sequence-delete" value="${iterator.index}">DELETE</button></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>






	</div>
	<!-- scripts -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/custom/js/script.js"/>"></script>
</body>
</html>