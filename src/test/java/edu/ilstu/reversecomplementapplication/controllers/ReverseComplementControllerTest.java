/**
 * 
 */
package edu.ilstu.reversecomplementapplication.controllers;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.RNASequence;
import org.biojava.nbio.core.sequence.template.AbstractSequence;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.reversecomplementapplication.components.ApplicationUtilityImpl;
import edu.ilstu.reversecomplementapplication.models.SequenceContainer;

/**
 * @author Bash
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ReverseComplementControllerTest extends BaseControllerSetup
{

	// TODO: Fix dependency injection mocking
	@Mock
	ApplicationUtilityImpl applicationUtility;

	@InjectMocks
	ReverseComplementController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	/**
	 * Determine if method works properly
	 * 
	 * @method {@link ReverseComplementController#getIndex(javax.servlet.http.HttpSession)}
	 * 
	 * @objective Setup the {@link HttpSession}, call method, and determine if
	 *            {@link ModelAndView} is as expected.
	 * 
	 * @expectedResult "container" in the model is not null
	 * @expectedResult "sequenceContainer" in the model is null
	 * @expectedResult {@link ModelAndView} view name is correct
	 */
	@Test
	public void getIndexTest()
	{
		addFilledListToSequenceContainerToSession();
		ModelAndView mav = controller.getIndex(session);
		assertNotNull(mav.getModelMap().get("container"));
		assertNull(mav.getModelMap().get("sequenceContainer"));
		assertEquals(mav.getViewName(), "reversecomplement/index");
	}

	/**
	 * Determine if method works properly
	 * 
	 * @method {@link ReverseComplementController#submitSequence(String, HttpSession)}
	 * 
	 * @objective Setup the {@link HttpSession}, call method, and determine if
	 *            {@link ModelAndView} is as expected.
	 * 
	 * @expectedResult "container" in the model is not null
	 * @expectedResult "sequenceContainer" in the model is null
	 * @expectedResult {@link ModelAndView} view name is correct
	 * @expectedResult "sequence" in the model is not null and a {@link String}
	 * @expectedResult "oldSequence" in the model is not null and a
	 *                 {@link String}
	 * 
	 */
	@Ignore
	@SuppressWarnings(
	{ "unchecked", "unused" })
	@Test
	public void submitDnaSequenceTest()
	{
		// Add empty sequence container to session
		addFilledListToSequenceContainerToSession();

		/*
		 * Call method and save the mav
		 */
		String stringSequence = "ACTGACTGACTGACTG";
		ModelAndView mav = controller.submitSequence(stringSequence, session);

		// Get the list from the container
		Object modelContainerObject = mav.getModelMap().get("container");

		// Assert the list was there
		assertNotNull(modelContainerObject);

		// Cast model object into the list
		LinkedList<AbstractSequence<?>> container = (LinkedList<AbstractSequence<?>>) modelContainerObject;

		// Ensure the string sequence was NOT set in sequence container
		assertThat(container.get(0).getSequenceAsString(), not(stringSequence));

		// Ensure that the key "sequenceContainer" is not mistakenly added
		assertNull(mav.getModelMap().get("sequenceContainer"));

		/*
		 * Get the two sequence from the model
		 */
		Object modelSequenceObject = mav.getModelMap().get("sequence");
		Object modelOldSequenceObject = mav.getModelMap().get("oldSequence");

		/*
		 * Ensure keys return available objects
		 */
		assertNotNull(modelOldSequenceObject);
		assertNotNull(modelSequenceObject);

		/*
		 * Ensure no exception thrown when converting back to base type
		 */
		String modelSequenceString = (String) modelSequenceObject;
		String modelOldSequenceString = (String) modelOldSequenceObject;

		// Ensure view forward is correct
		assertEquals(mav.getViewName(), "reversecomplement/index");
	}

	/**
	 * Add a null {@link SequenceContainer} to the {@link HttpSession}
	 */
	public void addNullSequenceContainerToSession()
	{
		SequenceContainer sequenceContainer = new SequenceContainer(null);
		session.setAttribute("sequenceContainer", sequenceContainer);
	}

	/**
	 * Add an empty {@link SequenceContainer} to the {@link HttpSession}
	 */
	public void addEmptyListToSequenceContainerToSession()
	{
		LinkedList<AbstractSequence<?>> sequenceList = new LinkedList<AbstractSequence<?>>();
		SequenceContainer sequenceContainer = new SequenceContainer(sequenceList);
		session.setAttribute("sequenceContainer", sequenceContainer);
	}

	/**
	 * Add a filled {@link SequenceContainer} to the {@link HttpSession}
	 */
	public void addFilledListToSequenceContainerToSession()
	{
		LinkedList<AbstractSequence<?>> sequenceList = new LinkedList<AbstractSequence<?>>();
		AbstractSequence<?> sequenceOne = null;
		try
		{
			sequenceOne = new DNASequence("AGCT");
		} catch (CompoundNotFoundException e)
		{
			e.printStackTrace();
		}
		AbstractSequence<?> sequenceTwo = null;
		try
		{
			sequenceTwo = new ProteinSequence("ARNDCEQGHILKMFPSTWYVBZJX");
		} catch (CompoundNotFoundException e)
		{
			e.printStackTrace();
		}
		AbstractSequence<?> sequenceThree = null;
		try
		{
			sequenceThree = new RNASequence("AGCU");
		} catch (CompoundNotFoundException e)
		{
			e.printStackTrace();
		}
		sequenceList.add(sequenceOne);
		sequenceList.add(sequenceTwo);
		sequenceList.add(sequenceThree);

		SequenceContainer sequenceContainer = new SequenceContainer(sequenceList);
		session.setAttribute("sequenceContainer", sequenceContainer);
	}

}
