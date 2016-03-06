/**
 * @author Bash
 */
$(document)
		.ready(
				function() {

					/**
					 * When clicked, copy the sequence in the result textarea to
					 * the main input textarea
					 */
					$("#copy-analyzed").click(
							function() {
								$("textarea#sequence-input").val(
										$("#sequence textarea").val().trim());
							});

					/**
					 * When clicked, submit an HTTP Post request to save
					 * sequence in textarea to server under the URL
					 * /submitSequence.do
					 */
					$("#sequence-save").click(function() {
						nonAjaxPostRequest("saveSequence.do", "post", {
							"sequence" : $("#sequence-input").val().trim()
						});
					});

					/**
					 * When clicked, submit an HTTP Post request to delete
					 * sequence in server under the URL /deleteSequence.do
					 */
					$(".sequence-delete").click(function() {
						nonAjaxPostRequest("deleteSequence.do", "post", {
							"index" : parseInt($(this).attr("value"))
						});
					});

					/**
					 * 
					 */
					$(".sequence-edit")
							.click(
									function() {
										var trElement = $(this).parents(
												".sequence-element");

										var originalSequence = trElement
												.children(".sequence-string")
												.text().trim();
										var value = "0"; // TODO: Get value.
										// change value from
										// button attr to tr
										// attr.

										var innerHtml = "<form action=\"editSequence.do\" method=\"post\"><input type=\"text\" name=\"sequence\" value=\""
												+ originalSequence
												+ "\"/>"
												+ "<input type=\"hidden\" name=\"index\" value=\""
												+ value + "\"/>" + "</form>";
										trElement.children(".sequence-string")
												.html(innerHtml);

										var innerHtml_2 = "<button class=\"btn btn-info edit-submit\">SUBMIT</button>";
										trElement.children(".edit-button")
												.html(innerHtml_2);

										var innerHtml_3 = "<button class=\"btn btn-warning cancel\">CANCEL</button>";
										trElement.children(".delete-button")
												.html(innerHtml_3);
									});

					/**
					 * 
					 */
					$(".edit-submit").click(
							function() {
								alert("CLICKED");
								$(this).parents(".sequence-element").children(
										".sequence-string").children("form")
										.submit();
							});

					/**
					 * 
					 */
					$(".cancel").click(function() {
						
					});
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
				});
