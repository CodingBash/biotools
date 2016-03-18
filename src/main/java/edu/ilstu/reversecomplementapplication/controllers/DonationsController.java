package edu.ilstu.reversecomplementapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView getToolsPage()
	{
		ModelAndView mav = new ModelAndView(donationsPage);
		return mav;
	}
}
