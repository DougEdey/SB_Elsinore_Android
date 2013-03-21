package com.strangebrew.elsinore.content;

import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView.GraphViewData;

public class Device {

	public String id;
	public String name;
	public double temperature = 0;
	public String scale = "F";
	
	public GraphViewSeries deviceSeries = new GraphViewSeries(new GraphViewData[0]);
	public double elapsed;
	
	public Device (String id, String name) {
		this.id = id;
		this.name = name;
	}
	
    public String toString() {
        return name;
    }
}
