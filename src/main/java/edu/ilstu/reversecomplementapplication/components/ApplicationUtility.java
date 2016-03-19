package edu.ilstu.reversecomplementapplication.components;

/**
 * For application specific utilities
 * 
 * @author Bash
 *
 */
public interface ApplicationUtility
{
	/**
	 * Converts a {@link String}[] to a {@link Integer}[]
	 * 
	 * @param stringArray
	 *            {@link String}[] to convert
	 * @return the final {@link Integer}[]
	 */
	public int[] convertStringArrayToIntArray(String[] stringArray);

	/**
	 * String sequence editing: Trim, FASTA conversion, and to upper case
	 * 
	 * @param stringSequence
	 *            string to be edited
	 * @return edited string
	 * @throws Exception
	 *             if FASTA unable to convert
	 */
	public String editStringSequence(String stringSequence) throws Exception;

	/**
	 * Extract the FASTA header from a FASTA sequence
	 * 
	 * @param stringSequence
	 *            FASTA sequence
	 * @return FASTA header
	 * @throws Exception
	 *             if FASTA unable to convert
	 */
	public String extractFastaHeader(String stringSequence) throws Exception;

	/**
	 * Extract the sequence from a FASTA sequence
	 * 
	 * @param stringSequence
	 *            FASTA sequence
	 * @return sequence
	 * @throws Exception
	 *             if FASTA unable to convert
	 */
	public String extractSequence(String stringSequence) throws Exception;

	/**
	 * Converts sequence to a FASTA Sequence
	 * 
	 * @param fastaHeader
	 *            header for FASTA sequence
	 * @param sequence
	 *            sequence for FASTA sequence
	 * @return final FASTA sequence
	 */
	public String convertToFastaSequence(String fastaHeader, String sequence);

	/**
	 * Determine if sequence is a DNA type
	 * 
	 * @param sequence
	 *            to evaluate
	 * @return determination
	 */
	public boolean isDNA(String sequence);

	/**
	 * Determine if sequence is a RNA type
	 * 
	 * @param sequence
	 *            to evaluate
	 * @return determination
	 */
	public boolean isRNA(String sequence);
}
