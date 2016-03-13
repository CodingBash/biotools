/**
 * Submit a non AJAX post request
 * 
 * @author Tidied Darin via Stack Overflow
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

$(document).ready(function() {
	$(function() {
		$(document).on("click", ".redirect-to-home", function() {
			window.location.href = "home";
		})
	});
	$(function() {
		$(document).on("click", ".redirect-to-gettingstarted", function() {
			window.location.href = "gettingstarted";
		})
	});
	$(function() {
		$(document).on("click", ".redirect-to-tools", function() {
			window.location.href = "tools";
		})
	});
	$(function() {
		$(document).on("click", ".redirect-to-aboutme", function() {
			window.location.href = "aboutme";
		})
	});
	$(function() {
		$(document).on("click", ".redirect-to-donations", function() {
			window.location.href = "donations";
		})
	});

});
