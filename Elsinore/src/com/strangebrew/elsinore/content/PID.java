package com.strangebrew.elsinore.content;

import android.util.Log;

public class PID extends Device {

	        
    public String mode;
    public double dutycycle = 0;
    public double cycletime = 0;
    public double setpoint = 0D;
    public long elapsedTime = 0;
    public double p_param = 0;
    public double i_param = 0;
    public double k_param = 0;
    public int GPIO = -1;
    public boolean feedback = false;
    
    public PID(String id, String name) {
        super(id, name);

    }
    
    public PID(String id, String name, double aDuty, double aTime, double p, double i, double k, double setpoint, int GPIO) {
    	super(id, name);
    	this.dutycycle = aDuty;
    	this.cycletime = aTime;
    	//this.elapsedTime = elapsed;
    	this.p_param = p;
    	this.i_param = i;
    	this.k_param = k;
    	this.GPIO = GPIO;
    	this.setpoint = setpoint;
    }

    public void Update(double aDuty, double aTime, double p, double i, double k, long elapsed, double temp, String scale, double setpoint) {
    	if(aDuty != 0.0) {
    		this.dutycycle = aDuty;
    	}
    	
    	if(aTime != 0.0) {
    		this.cycletime = aTime;
    	}
    	
    	if(elapsedTime != 0L) {
    		this.elapsedTime = elapsed;
    	}
    	
    	if(p != 0D) {
    		this.p_param = p;
    	}
    	
    	if(i != 0D) {
    		this.i_param = i;
    	}
    	
    	if(k != 0D) {
    		this.k_param = k;
    	}
    	
    	if(temp != 0D) {
    		this.temperature = temp;
    	}
    	
    	if(scale.equalsIgnoreCase("F") || scale.equalsIgnoreCase("C") ) {
			this.scale = scale;
		}
    	
    	if(setpoint != 0D) {
    		this.setpoint = setpoint;
    	}
    }

    public void UpdateParams(double aDuty, double aTime, double p, double i, double k, String mode, double setpoint) {
    	if(aDuty != 0.0) {
    		this.dutycycle = aDuty;
    	}
    	
    	if(aTime != 0.0) {
    		this.cycletime = aTime;
    	}
    	
    	
    	if(p != 0D) {
    		this.p_param = p;
    	}
    	
    	if(i != 0D) {
    		this.i_param = i;
    	}
    	
    	if(k != 0D) {
    		this.k_param = k;
    	}
    	
    	if(mode.equalsIgnoreCase("off") || mode.equalsIgnoreCase("auto") || mode.equalsIgnoreCase("manual")) {
    		this.mode = mode;
    	}
    	
    	if(setpoint != 0D) {
    		this.setpoint = setpoint;
    	}
    	Log.i("POST", "Feedback is true");
    	this.feedback = true;
    }
    
    
}

