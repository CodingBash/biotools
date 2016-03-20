package edu.ilstu.reversecomplementapplication.models;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.AbstractSequence;

/**
 * Container that wraps a {@link List} with application-specific functionality
 * 
 * @author Bash
 *
 */
public class SequenceContainer
{
	/**
	 * Interface {@link List} to contain an {@link AbstractSequence}<
	 * {@link NucleotideCompound}>
	 */
	private List<AbstractSequence<NucleotideCompound>> sequenceContainer;

	/**
	 * No-args constructor
	 */
	public SequenceContainer()
	{

	}

	/**
	 * Initiate the list with any implementation
	 * 
	 * @param sequenceContainer
	 */
	public SequenceContainer(List<AbstractSequence<NucleotideCompound>> sequenceContainer)
	{
		this.setSequenceContainer(sequenceContainer);
	}

	/**
	 * Get the sequenceContainer
	 * 
	 * @return sequenceContainer if available
	 */
	public List<AbstractSequence<NucleotideCompound>> getSequenceContainer()
	{
		if (sequenceContainer != null)
		{
			return sequenceContainer;
		}
		return null;
	}

	/**
	 * Set the sequence container
	 * 
	 * @param sequenceContainer
	 */
	public void setSequenceContainer(List<AbstractSequence<NucleotideCompound>> sequenceContainer)
	{
		this.sequenceContainer = sequenceContainer;
	}

	/**
	 * Add a sequence to the sequenceContainer at the end
	 * 
	 * @param sequence
	 *            to add
	 */
	public void addSequenceToContainer(AbstractSequence<NucleotideCompound> sequence)
	{
		if (sequenceContainer != null)
		{
			sequenceContainer.add(sequence);
		}
	}

	/**
	 * Add a sequence to the sequenceContainer in any index
	 * 
	 * @param index
	 *            in sequenceContainer to insert
	 * @param sequence
	 *            to add
	 */
	public void addSequenceToContainer(int index, AbstractSequence<NucleotideCompound> sequence)
	{
		if (sequenceContainer != null)
		{
			sequenceContainer.add(index, sequence);
		}
	}

	/**
	 * Get a sequence to the sequenceContainer in any index
	 * 
	 * @param index
	 *            in sequenceContainer to insert
	 * 
	 * @return the {@link AbstractSequence}<> in the index
	 */
	public AbstractSequence<NucleotideCompound> getSequenceInContainer(int index)
	{
		if (sequenceContainer != null)
		{
			return sequenceContainer.get(index);
		}
		return null;
	}

	/**
	 * Remove sequence in container in any index
	 * 
	 * @param index
	 *            to remove
	 * @throws IndexOutOfBoundsException
	 *             if out of bounds index
	 * 
	 * @return the removed {@link AbstractSequence}<NucleotideCompound>
	 */
	public AbstractSequence<NucleotideCompound> removeSequenceInContainer(int index) throws IndexOutOfBoundsException
	{
		if (sequenceContainer != null)
		{
			return sequenceContainer.remove(index);
		}
		return null;
	}

	/**
	 * Remove last sequence in container
	 * 
	 * @return the removed {@link AbstractSequence}<NucleotideCompound>
	 */
	public AbstractSequence<NucleotideCompound> removeLastSequenceInContainer()
	{
		if (sequenceContainer != null)
		{
			return sequenceContainer.remove(sequenceContainer.size() - 1);
		}
		return null;
	}

	/**
	 * Remove first sequence in container
	 * 
	 * @return the removed {@link AbstractSequence}<NucleotideCompound>
	 */
	public AbstractSequence<NucleotideCompound> removeFirstSequenceInContainer()
	{
		if (sequenceContainer != null)
		{
			return sequenceContainer.remove(0);
		}
		return null;
	}

	/**
	 * Clear the container
	 */
	public void removeAllSequencesInContainer()
	{
		if (sequenceContainer != null)
		{
			sequenceContainer.clear();
		}
	}

	/**
	 * Remove selected indexes from the sequenceContainer
	 * 
	 * @param indexList
	 *            list of indexes to remove
	 * @throws IndexOutOfBoundsException
	 *             if index is out of bounds
	 * 
	 * @return the removed {@link List} of {@link AbstractSequence}
	 *         <NucleotideCompound>
	 */
	public List<AbstractSequence<NucleotideCompound>> removeSelectedSequencesInContainer(int[] indexList)
			throws IndexOutOfBoundsException
	{
		if (sequenceContainer != null)
		{
			List<AbstractSequence<NucleotideCompound>> sequenceList = new LinkedList<AbstractSequence<NucleotideCompound>>();
			Arrays.sort(indexList);
			for (int index = indexList.length - 1; index >= 0; index--)
			{
				sequenceList.add(this.removeSequenceInContainer(indexList[index]));
			}
			return sequenceList;
		}
		return null;
	}

	/**
	 * Edit a sequence in the container
	 * 
	 * @param index
	 *            to edit
	 * @param sequence
	 *            to replace
	 */
	public void editSequenceInContainer(int index, AbstractSequence<NucleotideCompound> sequence)
	{
		if (sequenceContainer != null)
		{
			this.removeSequenceInContainer(index);
			this.addSequenceToContainer(index, sequence);
		}
	}

	public int size()
	{
		if (sequenceContainer != null)
		{
			return sequenceContainer.size();
		}
		return -1;
	}

}
