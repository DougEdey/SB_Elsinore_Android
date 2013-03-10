package com.strangebrew.elsinore.content;

public class Temp extends Device {
	public Temp (String id, String name) {
		super(id, name);
		
	}
	
	public void Update(double temp, String scale) {
		this.temperature = temp;
		
		
		
		if(scale.equalsIgnoreCase("F") || scale.equalsIgnoreCase("C") ) {
			this.scale = scale;
		}
	}
}