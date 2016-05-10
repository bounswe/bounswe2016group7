package com.homework.aydin;
/**
 * Data model(pair) that represents the tuples in database.
 * @author The Formal Boogieman
 *
 */
public class ModelAydin {
	
	private String President;
	private String Spouse;
	/**
	 * Constructor.
	 * @param a President
	 * @param b Spouse
	 */
	public ModelAydin(String a, String b)
	{
		President = a;
		Spouse = b;
	}
	/**
	 * Returns president names.
	 * @return President name.
	 */
	public String getPresident() {
		return President;
	}
	/**
	 * Returns spouse names.
	 * @return Spouse name.
	 */
	public String getSpouse() {
		return Spouse;
	}

}
