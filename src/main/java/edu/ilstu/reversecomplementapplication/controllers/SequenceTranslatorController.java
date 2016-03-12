/**
 * 
 */
package edu.ilstu.reversecomplementapplication.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bash
 *
 */
@Controller
public class SequenceTranslatorController
{

	private static final Logger logger = LoggerFactory.getLogger(SequenceTranslatorController.class);
	private static final String sequenceTranslatorPage = "sequencetranslatorpage/sequencetranslator";

	/**
	 * /homepage goes to the home.jsp landing page
	 * 
	 * @return redirect to home.jsp
	 */
	@RequestMapping(value = "/sequencetranslatorpage")
	public String getHomePage(Model model, HttpSession session)
	{
		return sequenceTranslatorPage;
	}
}
