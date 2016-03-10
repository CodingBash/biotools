package edu.ilstu.reversecomplementapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Donations controller
 * 
 * @author Bash
 *
 */
@Controller
public class DonationsController
{
	private static final Logger logger = LoggerFactory.getLogger(DonationsController.class);
	private static final String donationsPage = "donationspage/donations";

	/**
	 * Goes to donations page if the URL contains the value
	 * 
	 * @return donations.jsp
	 */
	@RequestMapping(value = "/donations")
	public String getToolsPage()
	{
		return donationsPage;
	}
}
