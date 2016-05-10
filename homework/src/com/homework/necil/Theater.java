package com.homework.necil;

public class Theater {
	public int frontendNo;
	public String Lat;
	public String Lng;
	public String Name;
	public String Exp;
	void Theater(){}
	/**
	 * @return theater name
	 */
	public String getName(){
		return this.Name;
	}
	/**
	 * @return Lat
	 */
	public String getLat(){
		return this.Lat;
	}
	/**
	 * @return Lng
	 */
	public String getLng(){
		return this.Lng;
	}
	/**
	 * @return frontendNo
	 */
	public String getNum(){
		return Integer.toString(this.frontendNo);
	}
}
