package com.homework.yunus;
/**
 * Data model to represent table on the database
 * @author Yunus
 *
 */
public class ModelYunus {

	private String Country;
	private String Capital;
	
	/**
	 * Constructor of the Model
	 * @param a Country
	 * @param b Capital
	 */
	public ModelYunus(String a, String b)
	{
		Country = a;
		Capital = b;
	}
	
	/**
	 * Gets country string
	 * @return country name
	 */
	public String getCountry() {
		return Country;
	}
	
	/**
	 * Gets capital string
	 * @return capital name
	 */
	public String getCapital() {
		return Capital;
	}

}
