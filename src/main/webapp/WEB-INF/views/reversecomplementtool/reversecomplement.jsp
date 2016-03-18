<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<!-- stylesheets -->
<link href="<c:url value="resources/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet" />
<link href="<c:url value="resources/custom/css/style.css" />" rel="stylesheet" />
<link href="<c:url value="resources/custom/css/reversecomplement/style.css" />" rel="stylesheet" />
<title>BioTools: Reverse-Complement</title>

<!-- Code to run before page loads -->
<script>
	if (window.location.href.indexOf("deleteSequence.do") !== -1) {
		location.hash = "#sequenceList";
	} else if (window.location.href.indexOf("submitSequence.do") !== -1) {
		location.hash = "#sequenceForm";
	} else if (window.location.href.indexOf("editSequence.do") !== -1) {
		location.hash = "#sequenceList";
	} else if (window.location.href.indexOf("saveSequence.do") !== -1) {
		location.hash = "#sequenceForm";
	} else if (window.location.href.indexOf("deleteAllSequences.do") !== -1) {
		location.hash = "#sequenceForm";
	}
</script>
<jsp:include page="head/allHead.jsp" />
</head>
<body class="white-background">

	<!-- include code for the header -->
	<jsp:include page="header/allHeaders.jsp" />

	<!-- included code for the body -->
	<jsp:include page="body/allBody.jsp" />

	<!-- included code for the footer -->
	<jsp:include page="footer/allFooters.jsp" />

	<!-- scripts -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="<c:url value="/resources/bootstrap/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/custom/js/script.js"/>"></script>
	<script src="<c:url value="/resources/custom/js/reversecomplement/script.js"/>"></script>
</body>
</html>