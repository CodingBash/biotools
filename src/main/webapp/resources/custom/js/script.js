/**
 * @author Bash
 */
$(document).ready(function() {

	/**
	 * When clicked, copy the sequence in the result textarea to the main input
	 * textarea
	 */
	$("#copy-analyzed").click(function() {
		$("textarea#sequence-input").val($("#sequence textarea").val().trim());
	});

	/**
	 * When clicked, submit an HTTP Post request to save sequence in textarea to
	 * server under the url /submitSequence.do
	 */
	$("#save-sequence").click(function() {
		nonAjaxPostRequest("saveSequence.do", "post", {
			"sequence" : $("#sequence-input").val().trim()
		});
	});

	/**
	 * Submit a non AJAX post request
	 * 
	 * @author Tidied Darin
	 */
	function nonAjaxPostRequest(action, method, input) {
		'use strict';
		var form;
		form = $('<form />', {
			action : action,
			method : method,
			style : 'display: none;'
		});
		if (typeof input !== 'undefined' && input !== null) {
			$.each(input, function(name, value) {
				$('<input />', {
					type : 'hidden',
					name : name,
					value : value
				}).appendTo(form);
			});
		}
		form.appendTo('body').submit();
	}
});
