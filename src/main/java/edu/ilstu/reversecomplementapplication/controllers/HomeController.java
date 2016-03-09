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
 * @author Basheer
 *
 */
@Controller
public class HomeController
{

	private static final Logger logger = LoggerFactory.getLogger(ReverseComplementController.class);
	private static final String homePage = "homepage/home";

	/**
	 * /homepage goes to the home.jsp landing page
	 * 
	 * @return redirect to home.jsp
	 */
	@RequestMapping(value = "/homepage")
	public String getHomePage(Model model, HttpSession session)
	{
		return homePage;
	}
}
