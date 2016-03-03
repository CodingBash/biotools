package edu.ilstu.reversecomplementapplication.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController
{
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/")
	public String getHomePage()
	{
		return "index";
	}

	@RequestMapping(value = "/submitSequence.do", method = RequestMethod.POST)
	public String submitSequence(@RequestParam("sequence") String stringSequence, Model model)
	{
		DNASequence sequence = null;
		try
		{
			sequence = new DNASequence(stringSequence);
		} catch (CompoundNotFoundException e)
		{
			e.printStackTrace();
		}

		if (sequence != null)
		{
			model.addAttribute("oldSequence", sequence.toString());
			try
			{
				sequence = new DNASequence(sequence.getReverseComplement().getSequenceAsString());
			} catch (CompoundNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("sequence", sequence.toString());
		} else
		{
			model.addAttribute("sequence", null);
			model.addAttribute("oldSequence", null);
		}
		return "index";
	}

	@RequestMapping(value = "/saveSequence.do", method = RequestMethod.POST)
	public String saveSequence(@RequestParam("sequence") String stringSequence, Model model, HttpSession session)
	{
		List<DNASequence> sequenceContainer = (LinkedList<DNASequence>) session.getAttribute("sequenceContainer");
		if (sequenceContainer == null)
		{
			sequenceContainer = new LinkedList<DNASequence>();
		}
		DNASequence sequence = null;
		try
		{
			sequence = new DNASequence(stringSequence);
		} catch (CompoundNotFoundException e)
		{
			e.printStackTrace();
		}

		sequenceContainer.add(sequence);
		session.setAttribute("sequenceContainer", sequenceContainer);
		model.addAttribute("sequenceContainer", sequenceContainer);
		return "index";
	}
}
