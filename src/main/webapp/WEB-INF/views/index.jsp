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
<script>
	if (window.location.href.indexOf("deleteSequence.do") !== -1) {
		location.hash = "#sequenceList";
	} else if (window.location.href.indexOf("submitSequence.do") !== -1) {
		location.hash = "#sequenceForm";
	} else if (window.location.href.indexOf("editSequence.do") !== -1) {
		location.hash = "#sequenceList";
	} else if (window.location.href.indexOf("saveSequence.do") !== -1) {
		location.hash = "#sequenceForm";
	}
</script>
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
	<div id="modal-delete-confirmation" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="title modal-title">Are you sure you want to delete?</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-danger sequence-delete" data-dismiss="modal">Delete</button>

				</div>
			</div>

		</div>
	</div>
	<div class="container-fluid white-background" id="sequenceForm">
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
				</form>
			</div>
			<div class="col-md-6">
				<div class="result text-center">
					<h2 class="title">The reverse complement is</h2>
					<p id="sequence">
						<textarea name="sequence-holder" rows="20" cols="75" readonly>${sequence}</textarea>
					</p>
				</div>
			</div>
		</div>
		<div class="row padded">
			<div class="col-md-12">
				<div class="btn-group btn-group-justified">
					<button class="btn btn-primary" id="sequenceForm-submit">Submit</button>
					<button class="btn btn-primary" id="copy-analyzed">Replace</button>
					<button class="btn btn-primary" id="sequence-save">Save</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid white-background" id="sequenceList">
		<table class="table table-hover table-striped">
			<tr>
				<th>Sequence #</th>
				<th>Sequence</th>
				<!-- EDIT -->
				<th id="edit-top"></th>
				<!-- DELETE -->
				<th id="delete-top"><button type="button" class="btn btn-danger center-block">DELETE
						ALL</button></th>
				<!-- CHECKBOX -->
				<th id="checkbox-top"></th>
			</tr>
			<!-- TODO: Add type="button" to all buttons -->
			<c:if test="${not empty container}">
				<c:forEach items="${container}" var="sequenceItem" varStatus="iterator">
					<tr class="sequence-element" value="${iterator.index}">
						<td class="sequence-count">#${iterator.index + 1}</td>
						<td class="sequence-string">${sequenceItem}</td>
						<td class="edit-button"><button type="button"
								class="btn btn-info sequence-edit center-block">EDIT</button></td>
						<td class="delete-button">
							<button type="button" class="btn btn-danger delete-modal-appearance center-block"
								data-toggle="modal" data-target="#modal-delete-confirmation">DELETE</button>
						</td>
						<!-- Make checkbox larger, issue centering checkbox -->
						<td><input type="checkbox" class="checkbox-delete-selection center-block"/></td>
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