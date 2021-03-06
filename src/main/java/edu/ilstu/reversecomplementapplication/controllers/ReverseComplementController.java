package edu.ilstu.reversecomplementapplication.controllers;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.RNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.AbstractSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.reversecomplementapplication.components.ApplicationUtilityImpl;
import edu.ilstu.reversecomplementapplication.models.Sequence;
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
	private static final String reverseComplementPage = "reversecomplementtool/reversecomplement";

	@Autowired
	ApplicationUtilityImpl applicationUtility;

	/**
	 * Empty URL field goes to the index.jsp landing page
	 * 
	 * @return redire8ct to index.jsp
	 */
	@RequestMapping(value = "/reversecomplement")
	public ModelAndView getIndex(HttpSession session)
	{
		ModelAndView mav = new ModelAndView(reverseComplementPage);
		addFormAttribute();
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
	public ModelAndView submitSequence(@ModelAttribute("sequenceFormAttribute") Sequence sequenceModel,
			HttpSession session)
	{
		ModelAndView mav = new ModelAndView(reverseComplementPage);

		// Get stringSequence
		String stringSequence = sequenceModel.getSequence();

		// Create the sequence from the @RequestParam
		AbstractSequence<NucleotideCompound> abstractSequence = createSequence(stringSequence);
		/*
		 * Get and set reverse complement
		 */
		if (abstractSequence != null && abstractSequence.getLength() != 0)
		{
			try
			{
				if (abstractSequence instanceof DNASequence)
				{
					abstractSequence = new DNASequence(
							((DNASequence) abstractSequence).getReverseComplement().getSequenceAsString());
				} else if (abstractSequence instanceof RNASequence)
				{
					abstractSequence = new RNASequence(
							((RNASequence) abstractSequence).getReverseComplement().getSequenceAsString());
				}
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

				String regularSequence = abstractSequence.toString();
				String fastaSequence = applicationUtility.convertToFastaSequence(fastaHeader, regularSequence);
				sequenceModel.setResultSequence(fastaSequence);
				// Add FASTA sequence to model

			} catch (CompoundNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		// addFormAttribute(sequenceModel);
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
	public ModelAndView saveSequence(@RequestParam("sequence") String stringSequence,
			@ModelAttribute("sequenceFormAttribute") Sequence sequenceModel, HttpSession session)
	{
		ModelAndView mav = new ModelAndView(reverseComplementPage);

		// Retrieve sequence container from session
		SequenceContainer sequenceContainer = this.retrieveSequenceContainer(session);

		// Create the sequence from the @RequestParam
		AbstractSequence<NucleotideCompound> abstractSequence = createSequence(stringSequence);

		// Add the DNASequence
		sequenceContainer.addSequenceToContainer(abstractSequence);

		// Add updated sequenceContainer back to session
		session.setAttribute("sequenceContainer", sequenceContainer);

		// Add sequence to the ModelAndView
		mav.addObject("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return mav;
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
	public ModelAndView editSequence(@RequestParam("sequence") String stringSequence, @RequestParam("index") int index,
			@ModelAttribute("sequenceFormAttribute") Sequence sequenceModel, HttpSession session)
	{
		ModelAndView mav = new ModelAndView(reverseComplementPage);

		// Retrieve sequence container from session
		SequenceContainer sequenceContainer = this.retrieveSequenceContainer(session);

		// Create the sequence from the @RequestParam
		AbstractSequence<NucleotideCompound> abstractSequence = createSequence(stringSequence);

		// Make the edit changes in the sequence container
		sequenceContainer.editSequenceInContainer(index, abstractSequence);

		// Add updated sequenceContainer back to session
		session.setAttribute("sequenceContainer", sequenceContainer);

		// Add sequence to the ModelAndView
		mav.addObject("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return mav;
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
	public ModelAndView deleteSequence(@RequestParam("index") int index,
			@ModelAttribute("sequenceFormAttribute") Sequence sequence, HttpSession session)
	{
		ModelAndView mav = new ModelAndView(reverseComplementPage);

		// Retrieve sequence container from session
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
		mav.addObject("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return mav;
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
	public ModelAndView deleteAllSequences(@ModelAttribute("sequenceFormAttribute") Sequence sequenceModel,
			HttpSession session)
	{
		ModelAndView mav = new ModelAndView(reverseComplementPage);

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
		mav.addObject("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return mav;
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
	public ModelAndView deleteSelectedSequences(@RequestParam("indexList") String[] stringIndexList,
			@ModelAttribute("sequenceFormAttribute") Sequence sequenceModel, HttpSession session)
	{
		ModelAndView mav = new ModelAndView(reverseComplementPage);

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
		mav.addObject("container", sequenceContainer.getSequenceContainer());

		// Refresh the index page
		return mav;
	}

	/**
	 * Retrieves {@link SequenceContainer} from the (@link HttpSession}
	 * 
	 * @param session
	 *            Spring session object
	 * @return sequenceContainer from the {@link HttpSession}
	 */
	private SequenceContainer retrieveSequenceContainer(HttpSession session)
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

		return sequenceContainer;
	}

	/**
	 * Creates an {@link AbstractSequence}<{@link NucleotideCompound}> from a
	 * {@link String} sequence
	 * 
	 * @param stringSequence
	 *            sequence to become an {@link AbstractSequence}<
	 *            {@link NucleotideCompound}>
	 * @return the {@link AbstractSequence}<{@link NucleotideCompound}>
	 */
	private AbstractSequence<NucleotideCompound> createSequence(String stringSequence)
	{
		AbstractSequence<NucleotideCompound> sequence = null;
		String formattedSequence = "";
		try
		{
			formattedSequence = applicationUtility.editStringSequence(stringSequence);
		} catch (Exception e3)
		{
			e3.printStackTrace();
		}
		if (applicationUtility.isDNA(formattedSequence))
		{
			try
			{
				sequence = new DNASequence(formattedSequence);
			} catch (Exception e)
			{
				e.printStackTrace();
				try
				{
					sequence = new DNASequence(stringSequence);
				} catch (CompoundNotFoundException e1)
				{
					e1.printStackTrace();
				}
			}
		} else if (applicationUtility.isRNA(formattedSequence))
		{
			try
			{
				sequence = new RNASequence(formattedSequence);
			} catch (Exception e)
			{
				e.printStackTrace();
				try
				{
					sequence = new DNASequence(stringSequence);
				} catch (CompoundNotFoundException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		return sequence;
	}

	@ModelAttribute("sequenceFormAttribute")
	private Sequence addFormAttribute()
	{
		Sequence sequence = new Sequence();
		return sequence;
	}

	@ModelAttribute("sequenceFormAttribute")
	private Sequence addFormAttribute(Sequence sequence)
	{
		return sequence;
	}
}
