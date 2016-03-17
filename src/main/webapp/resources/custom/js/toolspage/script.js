/**
 * 
 */
$(document).ready(function() {
	$(function() {
		$(document).on("click", ".redirect-to-reverse-complement", function() {
			alert("HERE");
			window.location.href = "reversecomplement";
		})
	});
});
