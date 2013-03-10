package com.strangebrew.elsinore.content;

public class Device {

	public String id;
	public String name;
	public double temperature = 0;
	public String scale = "F";
	
	public Device (String id, String name) {
		this.id = id;
		this.name = name;
	}
	
    public String toString() {
        return name;
    }
}
