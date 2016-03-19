package edu.ilstu.reversecomplementapplication.controllers;

import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.RNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.AbstractSequence;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpSession;

import edu.ilstu.reversecomplementapplication.components.ApplicationUtilityImpl;
import edu.ilstu.reversecomplementapplication.models.SequenceContainer;

public abstract class BaseControllerSetup
{

	@Spy
	protected HttpSession session = new MockHttpSession();

}
