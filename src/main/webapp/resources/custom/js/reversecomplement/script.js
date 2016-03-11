/**
 * @author Bash
 */
$(document).ready(
		function() {
			/**
			 * Page Initial loading
			 */
			if ($(".sequence-element").length) {
				$(".deleteall-modal-appearance").show();
			} else {
				$(".deleteall-modal-appearance").hide();
			}
			/**
			 * Holds the index of the \<tr\> element to pass to the modal
			 * delete button
			 */
			var deleteIndexHolder = null;

			/**
			 * Holds the \<tr\> element to insert back in if the user presses
			 * cancel when editing
			 */
			var holder = null;
			var deleteSelectionArray = new Array();

			/**
			 * Page Initial loading
			 */
			if ($(".sequence-element").length) {
				$(".deleteall-modal-appearance").show();
			} else {
				$(".deleteall-modal-appearance").hide();
			}

			if (deleteSelectionArray.length) {
				$(".delete-selected-button").show();
			} else {
				$(".delete-selected-button").hide();
			}
			/**
			 * COPY THE RESULT TO THE FORM TEXTAREA
			 */
			$("#copy-analyzed").click(function() {
				$("textarea#sequence-input").val($("#sequence textarea").val().trim());
			});

			/**
			 * SUBMIT THE FORM
			 */
			$("#sequenceForm-submit").click(function() {
				$("form#sequenceForm").submit();
			});

			/**
			 * SAVE THE SEQUENCE
			 */
			$("#sequence-save").click(function() {
				nonAjaxPostRequest("saveSequence.do", "post", {
					"sequence" : $("#sequence-input").val().trim()
				});
			});

			var deleteSelectionArray = new Array();
			/**
			 * DELETE CHECKBOX SELECTED
			 */
			$(function() {
				$(document).on("change", ".checkbox-delete-selection", function() {
					// Add or remove from array
					if ($(this).is(":checked")) {
						var value = $(this).parents(".sequence-element").attr("value");
						if (deleteSelectionArray.indexOf(value) === -1) {
							deleteSelectionArray.push(value);
						}
					} else {
						var value = $(this).parents(".sequence-element").attr("value");
						if (deleteSelectionArray.indexOf(value) !== -1) {
							var valueIndex = deleteSelectionArray.indexOf(value);
							if (valueIndex != -1) {
								deleteSelectionArray.splice(valueIndex, 1);
							}
						}
					}
					var size = deleteSelectionArray.length;
					// Update Delete Button
					if (deleteSelectionArray.length) {
						$(".delete-selected-button").children("span").text(deleteSelectionArray.length);
						$(".delete-selected-button").show("slow");

					} else {
						$(".delete-selected-button").hide("slow");
					}
				});
			});

			/**
			 * DELETE SELECTED
			 */
			$(function() {
				$(document).on("click", ".sequence-delete-selected", function() {
					nonAjaxPostRequest("deleteSelectedSequences.do", "POST", {
						"indexList" : deleteSelectionArray
					});
				});
			});

			/**
			 * DELETE MODAL APPEARANCE BUTTON
			 */
			$(function() {
				$(document).on("click", ".delete-modal-appearance", function() {
					deleteIndexHolder = $(this).parents(".sequence-element").attr("value");
				});
			});

			/**
			 * DELETE THE SEQUENCE
			 */
			$(function() {
				$(document).on("click", ".sequence-delete", function() {
					nonAjaxPostRequest("deleteSequence.do", "post", {
						"index" : parseInt(deleteIndexHolder)
					});
					deleteIndexHolder = null;
				});
			});

			/**
			 * DELETE ALL SEQUENCES
			 */
			$(function() {
				$(document).on("click", ".sequence-delete-all", function() {
					nonAjaxPostRequest("deleteAllSequences.do", "post", null);
				});
			});

			/**
			 * INITIATE THE EDIT
			 */
			$(function() {
				$(document).on(
						"click",
						".sequence-edit",
						function() {
							var trElement = $(this).parents(".sequence-element");
							holder = trElement.clone();
							// TODO: Optimize, dont recall
							// elements
							// several times
							var originalSequence = trElement.children(".sequence-data").text().trim();
							var value = trElement.attr("value"); // TODO:
							// Get
							// value.
							// change value from
							// button attr to tr
							// attr.

							var innerHtml = "<form action=\"editSequence.do\" method=\"post\"><input type=\"text\" name=\"sequence\" value=\"" + originalSequence + "\"/>"
									+ "<input type=\"hidden\" name=\"index\" value=\"" + value + "\"/>" + "</form>";
							trElement.children(".sequence-data").html(innerHtml);

							var innerHtml_2 = "<button class=\"btn btn-info edit-submit\">SUBMIT</button>";
							trElement.children(".button-first").html(innerHtml_2);

							var innerHtml_3 = "<button class=\"btn btn-warning cancel\">CANCEL</button>";
							trElement.children(".button-second").html(innerHtml_3);
						});
			});
			/**
			 * SUBMIT THE EDIT CHANGES
			 */
			$(function() {
				$(document).on("click", ".edit-submit", function() {
					$(this).parents(".sequence-element").children(".sequence-data").children("form").submit();
					holder = null;
				});
			});

			/**
			 * CANCEL THE EDIT
			 */
			$(function() {
				$(document).on("click", ".cancel", function() {
					if (holder !== null) {
						$(this).parents(".sequence-element").html(holder.html());
						holder = null;
					}

				});
			});

			/**
			 * EDIT THE INPUT STRING
			 */
			$("#sequence-input").focusout(function() {
				var stringSequence = $("#sequence-input").val();
				stringSequence = stringSequence.trim();
				if (stringSequence.indexOf(">") !== -1) {
					if (stringSequence.charAt(0) === '>') {
						if (stringSequence.indexOf("\n") !== -1) {
							var fastaHeader = stringSequence.substring(0, stringSequence.indexOf("\n") + 1);
							var sequence = stringSequence.substring(stringSequence.indexOf("\n", 1) + 1, stringSequence.length);
							sequence = sequence.replace(/ /g, "");
							sequence = sequence.replace(/\r?\n|\r/g, "");
							sequence = sequence.toUpperCase();
							stringSequence = fastaHeader + sequence;
						}
					}
				}
				$("#sequence-input").val(stringSequence);
			})
		});