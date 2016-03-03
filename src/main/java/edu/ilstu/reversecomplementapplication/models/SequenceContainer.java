package edu.ilstu.reversecomplementapplication.models;

import java.util.LinkedList;
import java.util.List;

import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.AbstractSequence;

public class SequenceContainer
{
	private List<AbstractSequence<NucleotideCompound>> sequenceContainer;

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

	public void removeSequenceInContainer(int index)
	{
		sequenceContainer.remove(index);
	}

	public void removeLastSequenceInContainer()
	{
		sequenceContainer.remove(sequenceContainer.size());
	}

	public void removeFirstSequenceInContainer()
	{
		sequenceContainer.remove(0);
	}

}
