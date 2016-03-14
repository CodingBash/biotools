/**
 * 
 */
package edu.ilstu.reversecomplementapplication.models;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.RNASequence;
import org.biojava.nbio.core.sequence.template.AbstractSequence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Bash
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SequenceContainerTest
{

	@Mock
	protected AbstractSequence<?> abstractSequence;

	protected SequenceContainer sequenceContainer;
	protected List<AbstractSequence<?>> listContainer;

	/**
	 * Setup
	 */
	@Before
	public void setup()
	{
		listContainer = null;
		sequenceContainer = null;
	}

	/**
	 * Determine if constructor works properly
	 * 
	 * @method {@link SequenceContainer#SequenceContainer(List)}
	 * 
	 * @objective Create a {@link List}, pass to constructor, retrieve through
	 *            getter, determine if same reference. For LinkedList and
	 *            ArrayList.
	 * 
	 * @expectedResult {@link List}s are same reference
	 */
	@Test
	public void testSequenceContainerEmptyConstructor()
	{
		/*
		 * Test with LinkedList
		 */
		listContainer = new LinkedList<AbstractSequence<?>>();
		sequenceContainer = new SequenceContainer(listContainer);
		assertTrue(sequenceContainer.getSequenceContainer() == listContainer);

		/*
		 * Test with ArrayList
		 */
		listContainer = new ArrayList<AbstractSequence<?>>();
		sequenceContainer = new SequenceContainer(listContainer);
		assertTrue(sequenceContainer.getSequenceContainer() == listContainer);
	}

	/**
	 * Determine if methods works properly
	 * 
	 * @method {@link SequenceContainer#setSequenceContainer(List)}
	 * @method {@link SequenceContainer#getSequenceContainer()}
	 * 
	 * @expectedResult {@link List}s are same reference
	 * @expectedResult {@link SequenceContainer} without a sequenceContainer
	 *                 returned null
	 */
	@Test
	public void testSetAndGetSequenceContainer()
	{
		/*
		 * Test with LinkedList
		 */
		listContainer = new LinkedList<AbstractSequence<?>>();
		sequenceContainer = new SequenceContainer();
		assertNull(sequenceContainer.getSequenceContainer());
		sequenceContainer.setSequenceContainer(listContainer);
		assertTrue(sequenceContainer.getSequenceContainer() == listContainer);

		/*
		 * Test with ArrayList
		 */
		listContainer = new ArrayList<AbstractSequence<?>>();
		sequenceContainer = new SequenceContainer();
		assertNull(sequenceContainer.getSequenceContainer());
		sequenceContainer.setSequenceContainer(listContainer);
		assertTrue(sequenceContainer.getSequenceContainer() == listContainer);
	}

	/**
	 * Determine if method works properly
	 * 
	 * @method {@link SequenceContainer#addSequenceToContainer(AbstractSequence)}
	 * 
	 * @expectedResult {@link SeqeunceContainer} with a null seqeunceContainer
	 *                 instance does not throw an error.
	 * @expectedResult Respected elements equal eachother
	 */
	@Test
	public void testAddSequenceToContainer()
	{
		/*
		 * Test without a seqeuenceContainer (Ensure no error is thrown)
		 */
		sequenceContainer = new SequenceContainer();
		try
		{
			sequenceContainer.addSequenceToContainer(new DNASequence("AGCT"));
		} catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}
		try
		{
			sequenceContainer.addSequenceToContainer(new RNASequence("AGCU"));
		} catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}

		/*
		 * Test with a sequenceContainer
		 */
		listContainer = new LinkedList<AbstractSequence<?>>();
		sequenceContainer = new SequenceContainer(listContainer);

		AbstractSequence<?> dnaSequence = null;
		try
		{
			dnaSequence = new DNASequence("AGCT");
			sequenceContainer.addSequenceToContainer(dnaSequence);
		} catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}

		AbstractSequence<?> rnaSequence = null;
		try
		{
			rnaSequence = new RNASequence("AGCU");
			sequenceContainer.addSequenceToContainer(rnaSequence);
		} catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}

		List<AbstractSequence<?>> retrievedList = sequenceContainer.getSequenceContainer();
		assertEquals(retrievedList.get(0), dnaSequence);
		assertEquals(retrievedList.get(1), rnaSequence);
	}

	/**
	 * Determine if the method works properly
	 * 
	 * @method {@link SequenceContainer#insertSequenceToContainer(int, AbstractSequence)}
	 * @method {@link SeuquenceContainer
	 * @expectedResult
	 */
	@Test
	public void testInsertSequenceToContainer()
	{
		listContainer = new LinkedList<AbstractSequence<?>>();
		listContainer = getFilledSequenceList();
		sequenceContainer = new SequenceContainer();
		try
		{
			sequenceContainer.insertSequenceToContainer(123, new DNASequence("AGCT"));
			sequenceContainer.getSequenceInContainer(0);
		} catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}

		int index = 1;
		AbstractSequence<?> sequence = null;
		try
		{
			sequence = new DNASequence("AGCT");
		} catch (CompoundNotFoundException e)
		{
			e.printStackTrace();
			fail();
		}
		System.out.println(listContainer.size());
		sequenceContainer.setSequenceContainer(listContainer);
		sequenceContainer.insertSequenceToContainer(index, sequence);
		assertTrue(sequenceContainer.getSequenceInContainer(index) == sequence);// assert
	}

	/**
	 * Determine if the method works properly
	 * 
	 * @method {@link SequenceContainer#removeSequenceInContainer(int)}
	 * 
	 * @expectedResult
	 */
	@Test
	public void testRemoveSequenceInContainer()
	{

	}

	/**
	 * Determine if the method works properly
	 * 
	 * @method {@link SequenceContainer#removeLastSequenceInContainer()}
	 * 
	 * @expectedResult
	 */
	@Test
	public void testRemoveLastSequenceInContainer()
	{

	}

	/**
	 * Determine if the method works properly
	 * 
	 * @method {@link SequenceContainer#removeFirstSequenceInContainer()}
	 * 
	 * @expectedResult
	 */
	@Test
	public void testRemoveFirstSequenceInContainer()
	{

	}

	/**
	 * Determine if the method works properly
	 * 
	 * @method {@link SequenceContainer#removeAllSequencesInContainer()}
	 * 
	 * @expectedResult
	 */
	@Test
	public void testRemoveAllSequencesInContainer()
	{

	}

	/**
	 * Determine if the method works properly
	 * 
	 * @method {@link SequenceContainer#removeSelectedSequencesInContainer(int[])}
	 * 
	 * @expectedResult
	 */
	@Test
	public void testRemoveSelectedSequencesInContainer1()
	{

	}

	/**
	 * Determine if the method works properly
	 * 
	 * @method {@link SequenceContainer#editSequenceInContainer(int, AbstractSequence)}
	 * 
	 * @expectedResult
	 */
	@Test
	public void testEditSequenceInContainer()
	{

	}

	/**
	 * Get filled {@link List}
	 */
	public List<AbstractSequence<?>> getFilledSequenceList()
	{
		List<AbstractSequence<?>> sequenceList = new LinkedList<AbstractSequence<?>>();
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

		return sequenceList;
	}

}
