package edu.ilstu.reversecomplementapplication.models;

import java.util.Arrays;
import java.util.List;

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
	 * {@link ?}>
	 */
	private List<AbstractSequence<?>> sequenceContainer;
	
	/**
	 * Initiate the list with any implementation
	 * 
	 * @param sequenceContainer
	 */
	public SequenceContainer(List<AbstractSequence<?>> sequenceContainer)
	{
		this.setSequenceContainer(sequenceContainer);
	}

	/**
	 * Get the sequenceContainer
	 * 
	 * @return sequenceContainer if available
	 */
	public List<AbstractSequence<?>> getSequenceContainer()
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
	public void setSequenceContainer(List<AbstractSequence<?>> sequenceContainer)
	{
		this.sequenceContainer = sequenceContainer;
	}

	/**
	 * Add a sequence to the sequenceContainer at the end
	 * 
	 * @param sequence
	 *            to add
	 */
	public void addSequenceToContainer(AbstractSequence<?> sequence)
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
	public void insertSequenceToContainer(int index, AbstractSequence<?> sequence)
	{
		if (sequenceContainer != null)
		{
			sequenceContainer.add(index, sequence);
		}
	}

	/**
	 * Remove sequence in container in any index
	 * 
	 * @param index
	 *            to remove
	 * @throws IndexOutOfBoundsException
	 *             if out of bounds index
	 */
	public void removeSequenceInContainer(int index) throws IndexOutOfBoundsException
	{
		if (sequenceContainer != null)
		{
			if (index >= 0 && index <= sequenceContainer.size())
			{
				sequenceContainer.remove(index);
			} else
			{
				throw new IndexOutOfBoundsException();
			}
		}
	}

	/**
	 * Remove last sequence in container
	 */
	public void removeLastSequenceInContainer()
	{
		if (sequenceContainer != null)
		{
			sequenceContainer.remove(sequenceContainer.size());
		}
	}

	/**
	 * Remove first sequence in container
	 */
	public void removeFirstSequenceInContainer()
	{
		if (sequenceContainer != null)
		{
			sequenceContainer.remove(0);
		}
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
	 */
	public void removeSelectedSequencesInContainer(int[] indexList) throws IndexOutOfBoundsException
	{
		if (sequenceContainer != null)
		{
			Arrays.sort(indexList);
			for (int index = indexList.length - 1; index >= 0; index--)
			{
				this.removeSequenceInContainer(indexList[index]);
			}
		}
	}

	/**
	 * Edit a sequence in the container
	 * 
	 * @param index
	 *            to edit
	 * @param sequence
	 *            to replace
	 */
	public void editSequenceInContainer(int index, AbstractSequence<?> sequence)
	{
		if (sequenceContainer != null)
		{
			this.removeSequenceInContainer(index);
			this.insertSequenceToContainer(index, sequence);
		}
	}

}
