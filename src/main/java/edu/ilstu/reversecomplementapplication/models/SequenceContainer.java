package edu.ilstu.reversecomplementapplication.models;

import java.util.List;

import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.AbstractSequence;

public class SequenceContainer
{
	private List<AbstractSequence<NucleotideCompound>> sequenceContainer;

	public SequenceContainer(List<AbstractSequence<NucleotideCompound>> sequenceContainer)
	{
		this.setSequenceContainer(sequenceContainer);
	}

	public List<AbstractSequence<NucleotideCompound>> getSequenceContainer()
	{
		return sequenceContainer;
	}

	public void setSequenceContainer(List<AbstractSequence<NucleotideCompound>> sequenceContainer)
	{
		this.sequenceContainer = sequenceContainer;
	}

	public void addSequenceToContainer(AbstractSequence<NucleotideCompound> sequence)
	{
		sequenceContainer.add(sequence);
	}

	public void insertSequenceToContainer(int index, AbstractSequence<NucleotideCompound> sequence)
	{
		sequenceContainer.add(index, sequence);
	}

	public void removeSequenceInContainer(int index) throws IndexOutOfBoundsException
	{
		if (index >= 0 && index <= sequenceContainer.size())
		{
			sequenceContainer.remove(index);
		} else
		{
			throw new IndexOutOfBoundsException();
		}
	}

	public void removeLastSequenceInContainer()
	{
		sequenceContainer.remove(sequenceContainer.size());
	}

	public void removeFirstSequenceInContainer()
	{
		sequenceContainer.remove(0);
	}

	public void editSequenceInContainer(int index, AbstractSequence<NucleotideCompound> sequence)
	{
		this.removeSequenceInContainer(index);
		this.insertSequenceToContainer(index, sequence);
	}

}
