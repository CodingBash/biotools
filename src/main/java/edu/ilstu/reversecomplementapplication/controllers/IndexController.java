package edu.ilstu.reversecomplementapplication.controllers;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.AbstractSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ilstu.reversecomplementapplication.models.SequenceContainer;

/**
 * Controller handles server logic in the index.jsp page
 * 
 * @author Bash
 *
 */
@Controller
public class IndexController
{
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	/**
	 * Empty URL field goes to the index.jsp landing page
	 * 
	 * @return redirect to index.jsp
	 */
	@RequestMapping(value = "/")
	public String getHomePage(Model model, HttpSession session)
	{
		// Insert session attribute into generic object
		Object objectedSequenceContainer = session.getAttribute("sequenceContainer");

		// Create a SequenceContainer object
		SequenceContainer sequenceContainer = null;

		// Check if the object is an instance of a SequenceContainer
		if (objectedSequenceContainer instanceof SequenceContainer)
		{
			sequenceContainer = (SequenceContainer) objectedSequenceContainer;
			// Add sequence to the ModelAndView
			model.addAttribute("container", sequenceContainer.getSequenceContainer());
		}

		return "index";
	}

	/**
	 * Get the reverse complement of the submitted DNAsequence
	 * 
	 * @param stringSequence
	 *            String of sequence to be submitted
	 * @param model
	 *            object container sent to be used in the view
	 * @return redirect to index.jsp
	 */
	@RequestMapping(value = "/submitSequence.do", method = RequestMethod.POST)
	public String submitSequence(@RequestParam("sequence") String stringSequence, Model model, HttpSession session)
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

		// Insert session attribute into generic object
		Object objectedSequenceContainer = session.getAttribute("sequenceContainer");

		// Create a SequenceContainer object
		SequenceContainer sequenceContainer = null;

		// Check if the object is an instance of a SequenceContainer
		if (objectedSequenceContainer instanceof SequenceContainer)
		{
			sequenceContainer = (SequenceContainer) objectedSequenceContainer;
			// Add sequence to the ModelAndView
			model.addAttribute("container", sequenceContainer.getSequenceContainer());
		}
		return "index";
	}

	/**
	 * Save sequence in server container
	 * 
	 * @param stringSequence
	 *            String of sequence to be saved
	 * @param model
	 *            object container sent to be used in the view
	 * @param session
	 *            Spring session object
	 * @return redirect to index.jsp
	 */
	@RequestMapping(value = "/saveSequence.do", method = RequestMethod.POST)
	public String saveSequence(@RequestParam("sequence") String stringSequence, Model model, HttpSession session)
	{
		// Insert session attribute into generic object
		Object objectedSequenceContainer = session.getAttribute("sequenceContainer");

		// Create a SequenceContainer object
		SequenceContainer sequenceContainer = null;

		// Check if the object is an instance of a SequenceContainer
		if (objectedSequenceContainer instanceof SequenceContainer)
		{
			sequenceContainer = (SequenceContainer) objectedSequenceContainer;
		}
		// If not, make a new SequenceContainer
		else
		{
			sequenceContainer = new SequenceContainer(new LinkedList<AbstractSequence<NucleotideCompound>>());
		}

		// Create the sequence from the @RequestParam
		AbstractSequence<NucleotideCompound> sequence = null;
		try
		{
			sequence = new DNASequence(stringSequence);
		} catch (CompoundNotFoundException e)
		{
			e.printStackTrace();
			// TODO: Error handling
		}

		// Add the DNASequence
		sequenceContainer.addSequenceToContainer(sequence);

		// Add updated sequenceContainer back to session
		session.setAttribute("sequenceContainer", sequenceContainer);

		// Add sequence to the ModelAndView
		model.addAttribute("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return "index";
	}

	/**
	 * Edit sequence in server container
	 * 
	 * @param stringSequence
	 *            String of sequence to be saved
	 * @param model
	 *            object container sent to be used in the view
	 * @param session
	 *            Spring session object
	 * @return redirect to index.jsp
	 */// TODO: Error handling.
	@RequestMapping(value = "/editSequence.do", method = RequestMethod.POST)
	public String editSequence(@RequestParam("sequence") String stringSequence, @RequestParam("index") int index,
			Model model, HttpSession session)
	{

		// Insert session attribute into generic object
		Object objectedSequenceContainer = session.getAttribute("sequenceContainer");

		// Create a SequenceContainer object
		SequenceContainer sequenceContainer = null;

		// Check if the object is an instance of a SequenceContainer
		if (objectedSequenceContainer instanceof SequenceContainer)
		{

			sequenceContainer = (SequenceContainer) objectedSequenceContainer;
		}
		// If not, make a new SequenceContainer
		else
		{
			sequenceContainer = new SequenceContainer(new LinkedList<AbstractSequence<NucleotideCompound>>());
		}

		// Create the sequence from the @RequestParam
		AbstractSequence<NucleotideCompound> sequence = null;
		try
		{
			sequence = new DNASequence(stringSequence);
			// Edit the sequence
			sequenceContainer.editSequenceInContainer(index, sequence);
		} catch (CompoundNotFoundException e)
		{
			e.printStackTrace();

		}

		// Add updated sequenceContainer back to session
		session.setAttribute("sequenceContainer", sequenceContainer);

		// Add sequence to the ModelAndView
		model.addAttribute("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return "index";
	}

	/**
	 * Delete sequence in server container
	 * 
	 * @param model
	 *            object container sent to be used in the view
	 * @param session
	 *            Spring session object
	 * @return redirect to index.jsp
	 */// TODO: Don't delete, validate request
	@RequestMapping(value = "/deleteSequence.do", method = RequestMethod.POST)
	public String deleteSequence(@RequestParam("index") int index, Model model, HttpSession session)
	{
		// Insert session attribute into generic object
		Object objectedSequenceContainer = session.getAttribute("sequenceContainer");

		// Create a SequenceContainer object
		SequenceContainer sequenceContainer = null;

		// Check if the object is an instance of a SequenceContainer
		if (objectedSequenceContainer instanceof SequenceContainer)
		{

			sequenceContainer = (SequenceContainer) objectedSequenceContainer;
		}
		// If not, make a new SequenceContainer
		else
		{
			sequenceContainer = new SequenceContainer(new LinkedList<AbstractSequence<NucleotideCompound>>());
		}

		// Delete the sequence
		try
		{
			sequenceContainer.removeSequenceInContainer(index);
		} catch (IndexOutOfBoundsException ioobe)
		{
			ioobe.printStackTrace();
		}

		// Add updated sequenceContainer back to session
		session.setAttribute("sequenceContainer", sequenceContainer);

		// Add sequence to the ModelAndView
		model.addAttribute("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return "index";
	}

	/**
	 * Delete all sequences in server container
	 * 
	 * @param model
	 *            object container sent to be used in the view
	 * @param session
	 *            Spring session object
	 * @return redirect to index.jsp
	 */
	@RequestMapping(value = "/deleteAllSequences.do", method = RequestMethod.POST)
	public String deleteAllSequences(Model model, HttpSession session)
	{
		// Insert session attribute into generic object
		Object objectedSequenceContainer = session.getAttribute("sequenceContainer");

		// Create a SequenceContainer object
		SequenceContainer sequenceContainer = null;

		// Check if the object is an instance of a SequenceContainer
		if (objectedSequenceContainer instanceof SequenceContainer)
		{

			sequenceContainer = (SequenceContainer) objectedSequenceContainer;
		}
		// If not, make a new SequenceContainer
		else
		{
			sequenceContainer = new SequenceContainer(new LinkedList<AbstractSequence<NucleotideCompound>>());
		}

		// Delete the sequence
		try
		{
			sequenceContainer.removeAllSequencesInContainer();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// Add updated sequenceContainer back to session
		session.setAttribute("sequenceContainer", sequenceContainer);

		// Add sequence to the ModelAndView
		model.addAttribute("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return "index";
	}
}
