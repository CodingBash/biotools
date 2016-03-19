package edu.ilstu.reversecomplementapplication.models;

import java.io.Serializable;

public class Sequence implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3994405282674744918L;

	private String sequence;
	private String resultSequence;

	public String getSequence()
	{
		return sequence;
	}

	public String getResultSequence()
	{
		return resultSequence;
	}

	public void setResultSequence(String resultSequence)
	{
		this.resultSequence = resultSequence;
	}

	public void setSequence(String sequence)
	{
		this.sequence = sequence;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
