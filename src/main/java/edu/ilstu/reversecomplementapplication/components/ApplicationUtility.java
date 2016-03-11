package edu.ilstu.reversecomplementapplication.components;

import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.io.FastaReader;
import org.biojava.nbio.core.sequence.template.AbstractSequence;
import org.springframework.stereotype.Service;

/**
 * For application specific utilities
 * 
 * @author Bash
 *
 */
@Service
public class ApplicationUtility
{
	/**
	 * Converts a {@link String}[] to a {@link Integer}[]
	 * 
	 * @param stringArray
	 *            {@link String}[] to convert
	 * @return the final {@link Integer}[]
	 */
	public int[] convertStringArrayToIntArray(String[] stringArray)
	{
		int[] intArray = new int[stringArray.length];
		for (int i = 0; i < intArray.length; i++)
		{
			intArray[i] = Integer.parseInt(stringArray[i]);
		}
		return intArray;
	}

	public String editStringSequence(String stringSequence) throws Exception
	{
		// Trim
		stringSequence = stringSequence.trim();
		// Check FASTA
		if (stringSequence.charAt(0) == ('>'))
		{
			System.out.println("> found");
			if (stringSequence.indexOf(System.getProperty("line.separator")) != -1)
			{
				System.out.println("n found");
				stringSequence = stringSequence.substring(
						stringSequence.indexOf(System.getProperty("line.separator"), 1), stringSequence.length());
				stringSequence = stringSequence.trim();
			} else
			{
				// TODO: Create new exception
				throw new Exception("Error converting from FASTA sequence: Could not find the newline");
			}
		}
		// Remove intermediate spaces
		// TODO: Translate to regex
		stringSequence = stringSequence.replace("/n", "");
		stringSequence = stringSequence.replace(" ", "");
		// To Uppercase
		stringSequence = stringSequence.toUpperCase();

		return stringSequence;

	}
}
