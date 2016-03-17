<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid white-background" id="sequenceList">
	<table class="table table-hover table-striped">
		<tr>
			<th id="table-sequence-number">Sequence #</th>
			<th id="table-sequence-data">Sequence</th>
			<!-- EDIT -->
			<th id="table-button-first"></th>
			<!-- DELETE -->
			<th id="table-button-second">
				<button type="button" class="btn btn-danger deleteall-modal-appearance center-block"
					data-toggle="modal" data-target="#modal-deleteall-confirmation">DELETE ALL</button>
			</th>
			<th id="table-checkbox">
				<button type="button" class="btn btn-danger delete-selected-button" data-toggle="modal"
					data-target="#modal-deleteselected-confirmation">
					DELETE SELECTED <span class="badge"></span>
				</button>
			</th>
		</tr>
		<!-- TODO: Add type="button" to all buttons -->
		<c:if test="${not empty container}">
			<c:forEach items="${container}" var="sequenceItem" varStatus="iterator">
				<tr class="sequence-element" value="${iterator.index}">
					<td class="sequence-number">#${iterator.index + 1}</td>
					<td class="sequence-data">
						<form action="editSequence.do" method="POST">
							<textarea cols="50" rows="10" name="sequence" class="sequence-data-textarea"><c:out
									value="${sequenceItem}" /></textarea>
							<input type="hidden" name="index" value="${iterator.index}" />
						</form>
					</td>
					<td class="button-first">
						<button type="button" class="btn btn-info sequence-edit center-block display-true">EDIT</button>

						<button class="btn btn-info center-block edit-submit display-false">SUBMIT</button>
					</td>
					<td class="button-second">
						<button type="button" class="btn btn-danger delete-modal-appearance center-block display-true"
							data-toggle="modal" data-target="#modal-delete-confirmation">DELETE</button>

						<button class="btn btn-warning center-block edit-cancel display-false">CANCEL</button>
					</td>
					<!-- Make checkbox larger, issue centering checkbox -->
					<td><input type="checkbox" class="checkbox-delete-selection center-block" /></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</div>