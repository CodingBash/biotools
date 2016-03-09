<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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