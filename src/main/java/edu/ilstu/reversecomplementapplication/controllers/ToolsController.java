package edu.ilstu.reversecomplementapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Tools controller
 * 
 * @author Bash
 *
 */
@Controller
public class ToolsController
{
	private static final Logger logger = LoggerFactory.getLogger(ToolsController.class);
	private static final String toolsPage = "toolspage/tools";

	/**
	 * Goes to tools page if the URL contains the value
	 * 
	 * @return tools.jsp
	 */
	@RequestMapping(value = "/tools")
	public ModelAndView getToolsPage()
	{
		ModelAndView mav = new ModelAndView(toolsPage);
		return mav;
	}
}
