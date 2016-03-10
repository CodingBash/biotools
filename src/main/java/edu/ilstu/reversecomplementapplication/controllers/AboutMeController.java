package edu.ilstu.reversecomplementapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * About me controller
 * 
 * @author Bash
 *
 */
@Controller
public class AboutMeController
{
	private static final Logger logger = LoggerFactory.getLogger(AboutMeController.class);
	private static final String aboutMePage = "aboutmepage/aboutme";

	/**
	 * Goes to about me page if the URL contains the value
	 * 
	 * @return aboutme.jsp
	 */
	@RequestMapping(value = "/aboutme")
	public String getToolsPage()
	{
		return aboutMePage;
	}
}
