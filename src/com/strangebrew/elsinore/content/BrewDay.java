package com.strangebrew.elsinore.content;

import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.json.JSONException;

public final class BrewDay {


	// generate the date time parameters
	DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat sFormat = new SimpleDateFormat("HH:mm:ss");
	DateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// the actual dates
	Date updated = null;
	Date startDay = null;
	Date mashIn = null;
	Date mashOut = null;
	Date spargeStart = null;
	Date spargeEnd = null;
	Date boilStart = null;
	Date chillStart = null;
	Date chillEnd = null;

	//each of the setters can be via string or date
	// not checking the existing data for now, to allow multiple brew days without restarting the server
	public Date parseDateString(String dateString) {
		Date dDate = null;

		// this better be a datestamp string
		try {
			dDate = new Date(Long.parseLong(dateString));
		} catch (NumberFormatException e) {
			System.out.println("Error: " + dateString + " could not be parsed as a long, trying date");
			try{
				dDate = lFormat.parse(dateString);
			} catch (ParseException p) {
				System.out.println("Could not parse date, giving up ya hoser");
			}
		}
		
		// check to see if we're null
		if(dDate == null) {
			System.out.println("Unparseable string provided " + dateString);
		}
		return dDate;
	}


	// start
	public Date getStart() {
		return startDay;
	}

	public String getStartString() {
		try {
			return lFormat.format(startDay);
		} catch (Exception e) {
			return null;
		}
	}

	public void setStart(Date startIn) {
		startDay = startIn;
	}

	public void setStart(String startIn) {
		setStart(parseDateString(startIn));
	}

	// mash in
	public Date getMashIn() {
		return mashIn;
	}

	public String getMashInString() {
		try {
			return lFormat.format(mashIn);
		} catch (Exception e) {
			return null;
		}
	}

	public void setMashIn(Date mashInIn) {
		mashIn = mashInIn;
	}

	public void setMashIn(String mashInIn) {
		setMashIn(parseDateString(mashInIn));
	}

	// mash out
	public Date getMashOut() {
		return mashOut;
	}

	public String getMashOutString() {
		try {
			return lFormat.format(mashOut);
		} catch (Exception e) {
			return null;
		}
	}

	public void setMashOut(Date mashOutIn) {
		mashOut = mashOutIn;
	}

	public void setMashOut(String mashOutIn) {
		setMashOut(parseDateString(mashOutIn));
	}

	// sparge start
	public Date getSpargeStart() {
		return spargeStart;
	}

	public String getSpargeStartString() {
		try {
			return lFormat.format(spargeStart);
		} catch (Exception e) {
			return null;
		}
	}

	public void setSpargeStart(Date spargeStartIn) {
		spargeStart = spargeStartIn;
	}

	public void setSpargeStart(String spargeStartIn) {
		setSpargeStart(parseDateString(spargeStartIn));
	}

	// sparge end
	public Date getSpargeEnd() {
		return spargeEnd;
	}

	public String getSpargeEndString() {
		try {
			return lFormat.format(spargeEnd);
		} catch (Exception e) {
			return null;
		}
	}

	public void setSpargeEnd(Date spargeEndIn) {
		spargeEnd = spargeEndIn;
	}

	public void setSpargeEnd(String spargeEndIn) {
		setSpargeEnd(parseDateString(spargeEndIn));
	}

	// boil start
	public Date getBoilStart() {
		return boilStart;
	}

	public String getBoilStartString() {
		try {
			return lFormat.format(boilStart);
		} catch (Exception e) {
			return null;
		}
	}

	public void setBoilStart(Date boilStartIn) {
		boilStart = boilStartIn;
	}

	public void setBoilStart(String boilStartIn) {
		setBoilStart(parseDateString(boilStartIn));
	}

	// chill start
	public Date getChillStart() {
		return chillStart;
	}

	public String getChillStartString() {
		try {
			return lFormat.format(chillStart);
		} catch (Exception e) {
			return null;
		}
	}

	public void setChillStart(Date chillStartIn) {
		chillStart = chillStartIn;
	}

	public void setChillStart(String chillStartIn) {
		setChillStart(parseDateString(chillStartIn));
	}

	// chill stop
	public Date getChillEnd() {
		return chillEnd;
	}

	public String getChillEndString() {
		try {
			return lFormat.format(chillEnd);
		} catch (Exception e) {
			return null;
		}
	}

	public void setChillEnd(Date chillEndIn) {
		chillEnd = chillEndIn;
	}

	public void setChillEnd(String chillEndIn) {
		setChillEnd(parseDateString(chillEndIn));
	}
	
	public Date getUpdate() {
		return updated;
	}
	
	public String getUpdatedString() {
		try {
			return lFormat.format(updated);
		} catch (Exception e) {
			return null;
		}
	}

	public void setUpdated(Date updatedIn) {
		updated = updatedIn;
	}

	public void setUpdated(String updatedIn) {
		setUpdated(parseDateString(updatedIn));
	}
	
	// get the JSON
	public JSONObject brewDayStatus() {
		JSONObject status = new JSONObject();
		try {
		if(startDay != null) {
			status.put("startDay", lFormat.format(startDay));
		}
		if(mashIn != null) {
			status.put("mashIn", lFormat.format(mashIn));
		}
		if(mashOut != null) {
			status.put("mashOut", lFormat.format(mashOut));
		}
		if(spargeStart != null) {
			status.put("spargeStart", lFormat.format(spargeStart));
		}
		if(spargeEnd != null) {
			status.put("spargeEnd", lFormat.format(spargeEnd));
		}
		if(boilStart != null) {
			status.put("boilStart", lFormat.format(boilStart));
		}
		if(chillStart != null) {
			status.put("chillStart", lFormat.format(chillStart));
		}
		if(chillEnd != null) {
			status.put("chillEnd", lFormat.format(chillEnd));
		}
		} catch (JSONException e) {
			System.out.println("Couldn't generate Date Status");
			e.printStackTrace();

		}

		
		return status;
	}


	
		

}