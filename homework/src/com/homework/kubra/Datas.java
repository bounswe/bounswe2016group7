package com.homework.kubra;

public class Datas {

	private String Planet;
	
	private String Discoverer;

	public Datas(String a, String b)
	{
		Planet = a;
		Discoverer = b;
	}

	public String getPlanet() {
		return Planet;
	}
	
	public String getDiscoverer() {
		return Discoverer;
	}
    public void setPlanet(String Planet) {
        this.Planet = Planet;
     }
    public void setDiscoverer(String Discoverer) {
        this.Discoverer = Discoverer;
     }

    
}

