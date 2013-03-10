package com.strangebrew.elsinore.content;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;



public class Data {
    /**
     * An array of items.
     */
    public static ArrayList<Device> ITEMS = new ArrayList<Device>();

    /**
     * A map of items, by ID.
     */
    public static Map<String, Device> ITEM_MAP = new HashMap<String, Device>();

    static {
        // Add 3 sample items.
    	
        
    }

    public static void addItem(PID item) {
    	
    	ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    
    public static void addItem(Temp item) {
    	ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
		
	}
    
    public static Device getDevice(String iName) {
    	Iterator<Device> iterator = ITEMS.iterator();
    	
		while (iterator.hasNext()) {
			if(iterator.next().name.equals(iName))
				return iterator.next();
		}
		
    	return null;
    }

	

    public static Device getFuzzyDevice(String iName) {
		if(Data.ITEMS.size() == 0) {
			return null;
		}
    	Iterator<Device> iterator = Data.ITEMS.iterator();
 
    	if(Data.ITEMS.get(0).name.toLowerCase(Locale.CANADA).startsWith(iName.toLowerCase(Locale.CANADA)))
			return (Device) Data.ITEMS.get(0);
		while (iterator.hasNext()) {
			Device temp = iterator.next();
			if(temp.name.toLowerCase(Locale.CANADA).startsWith(iName.toLowerCase(Locale.CANADA)))
				return (Device)temp;
		}
		
    	return null;
		
	}
	
	public static PID getPID(String iName) {
		if(Data.ITEMS.size() == 0) {
			return null;
		}
    	Iterator<Device> iterator = Data.ITEMS.iterator();
 
    	if(Data.ITEMS.get(0).getClass() == PID.class &&  Data.ITEMS.get(0).name.equalsIgnoreCase(iName))
			return (PID) Data.ITEMS.get(0);
		while (iterator.hasNext()) {
			Device temp = iterator.next();
			if(temp.getClass() == PID.class && temp.name.equalsIgnoreCase(iName))
				return (PID)temp;
		}
		
    	return null;
    }
	
	
	public static Temp getTemp(String iName) {
		if(Data.ITEMS.size() == 0) {
			return null;
		}
    	Iterator<Device> iterator = Data.ITEMS.iterator();
 
    	if(Data.ITEMS.get(0).getClass() == Temp.class &&  Data.ITEMS.get(0).name.equalsIgnoreCase(iName))
			return (Temp) Data.ITEMS.get(0);
		while (iterator.hasNext()) {
			Device temp = iterator.next();
			if(temp.getClass() == Temp.class && temp.name.equalsIgnoreCase(iName))
				return (Temp)temp;
		}
		
    	return null;
    }
    /**
     * A dummy item representing a piece of content.
     */
   
   
}
