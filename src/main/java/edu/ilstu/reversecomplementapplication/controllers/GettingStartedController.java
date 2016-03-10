package edu.ilstu.reversecomplementapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Getting started controller
 * 
 * @author Bash
 *
 */
@Controller
public class GettingStartedController
{

	private static final Logger logger = LoggerFactory.getLogger(GettingStartedController.class);
	private static final String gettingStartedPage = "gettingstartedpage/gettingstarted";

	/**
	 * Goes to getting started page if the URL contains the value
	 * 
	 * @return gettingstarted.jsp
	 */
	@RequestMapping(value = "/gettingstarted")
	public String getToolsPage()
	{
		return gettingStartedPage;
	}
}
