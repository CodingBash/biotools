package edu.ilstu.reversecomplementapplication.components;

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
}
