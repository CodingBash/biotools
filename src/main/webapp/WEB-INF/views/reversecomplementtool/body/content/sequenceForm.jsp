<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container-fluid white-background" id="sequenceFormSection">
	<div class="row">
		<div class="col-md-6">
			<h2 class="text-center same-height-1">Paste your sequence into the field and press "Submit".
				Press "Save" to move to table.</h2>

			<!-- form test2 -->
			<form:form modelAttribute="sequenceFormAttribute"
				action="${pageContext.request.contextPath}/submitSequence.do" method="post"
				cssClass="text-center form-horizontal" id="sequenceForm" role="form">
				<div class="form-group">
					<label for="sequence-input" class="sr-only control-label">Sequence</label>
					<form:textarea path="sequence" rows="20" cols="75" id="sequence-input" />
				</div>
			</form:form>


			<div class="text-center">
				<div class="btn-group margin-top-small">
					<button class="btn btn-primary" id="sequenceForm-submit">Submit</button>
					<button class="btn btn-primary" id="sequence-save">Save</button>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="result text-center">
				<h2 class="text-center same-height-1">The reverse-complement analyzed from the left is in
					the field below. Press "Replace" to move to left.</h2>
				<div id="sequence">
					<textarea rows="20" cols="75" readonly>${sequenceFormAttribute.resultSequence}</textarea>
				</div>
			</div>

			<button class="btn btn-primary center-block margin-top-small" id="copy-analyzed">Copy
				Left</button>
		</div>
	</div>
</div>