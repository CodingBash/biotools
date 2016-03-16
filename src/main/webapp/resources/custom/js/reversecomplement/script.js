/**
 * @author Bash
 */
$(document).ready(function() {
	/*
	 * Processes on page load
	 */
	// START
	/**
	 * Holds the index of the \<tr\> element to pass to the modal delete button
	 */
	var deleteIndexHolder = null;

	/**
	 * Holds the \<tr\> element to insert back in if the user presses cancel
	 * when editing
	 */
	var $holder = null;
	var deleteSelectionArray = new Array();

	/**
	 * DECIDING TO DISPLAY DELETEALL BUTTON
	 */
	if ($(".sequence-element").length) {
		$(".deleteall-modal-appearance").show();
	} else {
		$(".deleteall-modal-appearance").hide();
	}

	/**
	 * DECIDING TO DISPLAY DELETESELECTED BUTTON
	 */
	if (deleteSelectionArray.length) {
		$(".delete-selected-button").show();
	} else {
		$(".delete-selected-button").hide();
	}

	/**
	 * HIDING THE SEQUENCE EDIT BUTTONS
	 */
	// TODO: Just call changeVisibilityOfElements();
	$(".display-false").hide();
	$(".display-true").show();
	// END
	/*
	 * 
	 * 
	 */

	/*
	 * Event handling
	 */
	// START
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
		$(document).on("click", ".sequence-edit", function() {
			makeTableElementEditable($(this));
		});
	});
	/**
	 * SUBMIT THE EDIT CHANGES
	 */
	$(function() {
		$(document).on("click", ".edit-submit", function() {
			$(this).parents(".sequence-element").children(".sequence-data").children("form").submit();
			$holder = null;
		});
	});

	/**
	 * CANCEL THE EDIT
	 */
	$(function() {
		$(document).on("click", ".edit-cancel", function() {
			cancelEdit($(this));
		});
	});

	/**
	 * EDIT THE INPUT STRING
	 */
	$("#sequence-input").focusout(function() {
		var stringSequence = $("#sequence-input").val();
		stringSequence = cleanStringSequence(stringSequence)
		$("#sequence-input").val(stringSequence);
	})
	// END
	/*
	 * 
	 * 
	 */

	/*
	 * For dynamic table input changing
	 * 
	 */
	// START
	var lastValue = "";
	/**
	 * WHEN TABLE INPUT STRING CLICKED, ASSUME EDIT
	 */
	$(function() {
		$(document).on("click", ".sequence-data-textarea", function() {
			lastValue = $(this).val();
			makeTableElementEditable($(this));
		});
	});

	/**
	 * EDIT THE TABLE INPUT STRING
	 */
	$(function() {
		$(document).on("focusout", ".sequence-data-textarea", function() {
			var stringSequence = $(this).val();
			stringSequence = cleanStringSequence(stringSequence)
			$(this).val(stringSequence);
			if (stringSequence === lastValue) {
				cancelEdit($(this));
			}
		});
	});

	function visibilityForStandardButtonState($trElement) {
		// Hide
		changeToHide($trElement.find(".edit-submit"));

		// Show
		changeToShow($trElement.find(".sequence-edit"));

		// Hide
		changeToHide($trElement.find(".edit-cancel"));

		// Show
		changeToShow($trElement.find(".delete-modal-appearance"));
	}

	function visibilityForEditingButtonState($trElement) {
		// Hide
		changeToHide($trElement.find("button.sequence-edit"));
		// Show
		changeToShow($trElement.find("button.edit-submit"));

		// Hide
		changeToHide($trElement.find("button.delete-modal-appearance"));

		// Show
		changeToShow($trElement.find("button.edit-cancel"));
	}

	function changeToHide($element) {
		$element.removeClass("display-true");
		$element.addClass("display-false")
		// element.toggleClass("display-true display-false");
	}

	function changeToShow($element) {
		$element.removeClass("display-false");
		$element.addClass("display-true");
		// element.toggleClass("display-false display-true");
	}
	function changeVisibilityOfElements() {
		console.log("visibility actions");
		$(".display-false").hide();
		$(".display-true").show();
	}
	// END
	/*
	 * 
	 * 
	 */

	/*
	 * Consistent functions
	 */
	// START
	var holderArray = new Array();
	/**
	 * MAKE TABLE ELEMENT EDITABLE
	 * 
	 * @param thisElement
	 *            an element contained inside the \<tr\> .sequence-element
	 */
	function makeTableElementEditable($thisElement) {
		console.log("Make Editable");
		var $trElement = $thisElement.parents(".sequence-element");

		var index = parseInt($trElement.attr("value"));
		if (holderArray[index] === "") {
			holderArray[index] = $trElement.children(".sequence-data-textarea").val();
		}
		console.log($trElement.find("button.sequence-edit").hasClass("btn"));
		console.log("Displaying edit buttons");
		visibilityForEditingButtonState($trElement);
		changeVisibilityOfElements();
		console.log("Buttons canged");
		/*
		 * var innerHtml_2 = "<button class=\"btn btn-info center-block
		 * edit-submit\">SUBMIT</button>";
		 * $trElement.children(".button-first").html(innerHtml_2);
		 * 
		 * var innerHtml_3 = "<button class=\"btn btn-warning center-block
		 * cancel\">CANCEL</button>";
		 * $trElement.children(".button-second").html(innerHtml_3);
		 */
	}

	/**
	 * CLEAN STRING SEQUENCE
	 * 
	 * @param stringSequence
	 *            string to clean
	 */
	function cleanStringSequence(stringSequence) {
		stringSequence = stringSequence.trim();
		if (stringSequence.indexOf(">") !== -1) {
			if (stringSequence.charAt(0) === '>') {
				/*
				 * Is a FASTA sequence
				 */
				if (stringSequence.indexOf("\n") !== -1) {
					var fastaHeader = stringSequence.substring(0, stringSequence.indexOf("\n") + 1);
					var sequence = stringSequence.substring(stringSequence.indexOf("\n", 1) + 1, stringSequence.length);
					sequence = sequence.replace(/ /g, "");
					sequence = sequence.replace(/\r?\n|\r/g, "");
					sequence = sequence.toUpperCase();
					stringSequence = fastaHeader + sequence;
				}
			}
		} else {
			/*
			 * Not a FASTA sequence
			 */
			stringSequence = stringSequence.replace(/ /g, "");
			stringSequence = stringSequence.replace(/\r?\n|\r/g, "");
			stringSequence = stringSequence.toUpperCase();
		}
		return stringSequence
	}

	/**
	 * CANCEL EDIT
	 * 
	 * @param thisElement
	 *            an element contained inside the \<tr\> .sequence-element
	 */
	function cancelEdit($thisElement) {
		var $trElement = $thisElement.parents(".sequence-element");
		var index = parseInt($trElement.attr("value"));
		$thisElement.parents(".sequence-element").html(holderArray[index]);
		holderArray[index] = "";

		visibilityForStandardButtonState($trElement);

		changeVisibilityOfElements();

		/*
		 * var innerHtml_2 = "<button class=\"btn btn-info center-block
		 * edit-submit\">SUBMIT</button>";
		 * $trElement.children(".button-first").html(innerHtml_2);
		 * 
		 * var innerHtml_3 = "<button class=\"btn btn-warning center-block
		 * edit-cancel\">CANCEL</button>";
		 * $trElement.children(".button-second").html(innerHtml_3);
		 */

	}
	// END
	/*
	 * 
	 * 
	 */
});