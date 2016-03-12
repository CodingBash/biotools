package edu.ilstu.reversecomplementapplication.controllers;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.AbstractSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.reversecomplementapplication.components.ApplicationUtility;
import edu.ilstu.reversecomplementapplication.models.SequenceContainer;

/**
 * Controller handles server logic in the index.jsp page
 * 
 * @author Bash
 *
 */
@Controller
public class ReverseComplementController
{
	private static final Logger logger = LoggerFactory.getLogger(ReverseComplementController.class);
	private static final String indexPage = "reversecomplement/index";

	@Autowired
	ApplicationUtility applicationUtility;

	/**
	 * Empty URL field goes to the index.jsp landing page
	 * 
	 * @return redirect to index.jsp
	 */
	@RequestMapping(value = "/")
	public ModelAndView getIndex(HttpSession session)
	{
		ModelAndView mav = new ModelAndView(indexPage);
		// Retrieve sequence container from session
		SequenceContainer sequenceContainer = this.retrieveSequenceContainer(session);

		// Add sequence to the ModelAndView
		mav.addObject("container", sequenceContainer.getSequenceContainer());
		return mav;
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
	public ModelAndView submitSequence(@RequestParam("sequence") String stringSequence, HttpSession session)
	{
		ModelAndView mav = new ModelAndView(indexPage);
		// TODO: Generalize into an abstract base
		DNASequence sequence = null;
		try
		{
			try
			{
				sequence = new DNASequence(applicationUtility.editStringSequence(stringSequence));
			} catch (Exception e)
			{
				e.printStackTrace();
				sequence = new DNASequence(stringSequence);
			}
		} catch (CompoundNotFoundException e)
		{
			e.printStackTrace();
		}

		if (sequence != null)
		{
			mav.addObject("oldSequence", stringSequence);
			try
			{
				sequence = new DNASequence(sequence.getReverseComplement().getSequenceAsString());

				/*
				 * Convert regular sequence to FASTA sequence
				 */
				String fastaHeader = "";
				try
				{
					fastaHeader = applicationUtility.extractFastaHeader(stringSequence);
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String regularSequence = sequence.toString();
				String fastaSequence = applicationUtility.convertToFastaSequence(fastaHeader, regularSequence);

				// Add FASTA sequence to model
				mav.addObject("sequence", fastaSequence);
			} catch (CompoundNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			mav.addObject("sequence", null);
			mav.addObject("oldSequence", null);
		}

		// Retrieve sequence container from session
		SequenceContainer sequenceContainer = this.retrieveSequenceContainer(session);

		// Add sequence to the ModelAndView
		mav.addObject("container", sequenceContainer.getSequenceContainer());

		return mav;
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
		// Retrieve sequence container from session
		SequenceContainer sequenceContainer = this.retrieveSequenceContainer(session);

		// Create the sequence from the @RequestParam
		AbstractSequence<NucleotideCompound> sequence = null;
		try
		{
			try
			{
				sequence = new DNASequence(applicationUtility.editStringSequence(stringSequence));
			} catch (Exception e)
			{
				e.printStackTrace();
				sequence = new DNASequence(stringSequence);
			}
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
		return indexPage;
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

		// Retrieve sequence container from session
		SequenceContainer sequenceContainer = this.retrieveSequenceContainer(session);

		// Create the sequence from the @RequestParam
		AbstractSequence<NucleotideCompound> sequence = null;
		try
		{
			try
			{
				sequence = new DNASequence(applicationUtility.editStringSequence(stringSequence));
			} catch (Exception e)
			{
				e.printStackTrace();
				sequence = new DNASequence(stringSequence);
			}
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
		return indexPage;
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
		/// Retrieve sequence container from session
		SequenceContainer sequenceContainer = this.retrieveSequenceContainer(session);

		// Delete the sequence
		try
		{
			sequenceContainer.removeSequenceInContainer(index);
		} catch (IndexOutOfBoundsException ioobe)
		{
			ioobe.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// Add updated sequenceContainer back to session
		session.setAttribute("sequenceContainer", sequenceContainer);

		// Add sequence to the ModelAndView
		model.addAttribute("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return indexPage;
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
		// Retrieve sequence container from session
		SequenceContainer sequenceContainer = this.retrieveSequenceContainer(session);

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
		return indexPage;
	}

	/**
	 * Delete a list of selected indexes from the list of sequences
	 * 
	 * @param stringIndexList
	 *            list of indexes to remove
	 * @param model
	 *            object container sent to be used in the view
	 * @param session
	 *            Spring session object
	 * @return redirect to index.jsp
	 */
	@RequestMapping(value = "/deleteSelectedSequences.do", method = RequestMethod.POST)
	public String deleteSelectedSequences(@RequestParam("indexList") String[] stringIndexList, Model model,
			HttpSession session)
	{
		// Retrieve sequence container from session
		SequenceContainer sequenceContainer = this.retrieveSequenceContainer(session);

		// Convert String[] to int[]
		int[] intIndexList = applicationUtility.convertStringArrayToIntArray(stringIndexList);

		// Delete the sequences
		try
		{
			sequenceContainer.removeSelectedSequencesInContainer(intIndexList);
		} catch (IndexOutOfBoundsException ioobe)
		{
			ioobe.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// Add updated sequenceContainer back to session
		session.setAttribute("sequenceContainer", sequenceContainer);

		// Add sequence to the ModelAndView
		model.addAttribute("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return indexPage;
	}

	/**
	 * Retrieves {@link SequenceContainer} from the (@link HttpSession}
	 * 
	 * @param session
	 *            Spring session object
	 * @return sequenceContainer from the {@link HttpSession}
	 */
	public SequenceContainer retrieveSequenceContainer(HttpSession session)
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
			sequenceContainer = new SequenceContainer(new LinkedList<AbstractSequence<?>>());
		}

		return sequenceContainer;
	}
}
