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

	/**
	 * String sequence editing: Trim, FASTA conversion, and to upper case
	 * 
	 * @param stringSequence
	 *            string to be edited
	 * @return edited string
	 * @throws Exception
	 *             if FASTA unable to convert
	 */
	public String editStringSequence(String stringSequence) throws Exception
	{
		// Trim
		stringSequence = stringSequence.trim();
		// Check FASTA
		if (stringSequence.charAt(0) == ('>'))
		{
			if (stringSequence.indexOf(System.getProperty("line.separator")) != -1)
			{
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
		stringSequence = stringSequence.replace(System.getProperty("line.separator"), "");
		stringSequence = stringSequence.replace(" ", "");
		// To Uppercase
		stringSequence = stringSequence.toUpperCase();

		return stringSequence;
	}

	/**
	 * Extract the FASTA header from a FASTA sequence
	 * 
	 * @param stringSequence
	 *            FASTA sequence
	 * @return FASTA header
	 * @throws Exception
	 *             if FASTA unable to convert
	 */
	public String extractFastaHeader(String stringSequence) throws Exception
	{
		String fastaHeader = "";
		stringSequence = stringSequence.trim();
		if (stringSequence.charAt(0) == ('>'))
		{
			if (stringSequence.indexOf(System.getProperty("line.separator")) != -1)
			{
				fastaHeader = stringSequence.substring(0, stringSequence.indexOf(System.getProperty("line.separator")));
			} else
			{
				// TODO: Create new exception
				throw new Exception("Error converting from FASTA sequence: Could not find the newline");
			}
		}
		return fastaHeader;
	}

	/**
	 * Converts sequence to a FASTA Sequence
	 * 
	 * @param fastaHeader
	 *            header for FASTA sequence
	 * @param sequence
	 *            sequence for FASTA sequence
	 * @return final FASTA sequence
	 */
	public String convertToFastaSequence(String fastaHeader, String sequence)
	{
		String fastaSequence = "";
		fastaHeader = fastaHeader.replace(System.getProperty("line.separator"), "");

		sequence = sequence.trim();

		// TODO: Test line separator extractor
		if (sequence.substring(0, 1) == System.getProperty("line.separator"))
		{
			sequence = sequence.substring(1, sequence.length());
		}

		fastaSequence = fastaHeader + System.getProperty("line.separator") + sequence;

		return fastaSequence;
	}
}
