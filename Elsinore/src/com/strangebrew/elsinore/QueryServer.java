package com.strangebrew.elsinore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.strangebrew.elsinore.content.Data;
import com.strangebrew.elsinore.content.Device;
import com.strangebrew.elsinore.content.PID;
import com.strangebrew.elsinore.content.Temp;

    public class QueryServer extends AsyncTask<String, Void, Void>  {
    	
        public static final int MSG_SET_VALUE = 0;
		private String url = "";
    	private String server = "";
    	private ListActivity activity;
    	private Context context;
    	private ProgressDialog dialog;

    	public QueryServer(ListActivity listActivity) {
    		this.activity = listActivity;
    		context = listActivity;
    		dialog = new ProgressDialog(context);
    	}
    	

		public Void doInBackground (String ...iData) {
    		Log.i("QS", "Async doinBG runing");
    		Void v = null;
    		if(iData.length > 0) {
    			server = iData[0];
    			
    			getData();
    			
    		}
    		return v;
    	}
    	
    	

    	public void getData() {
    		
    				
    		Log.i("server", "Server: " + server);
    		// loop and get the data until we're broken
    		try {
    				
    			DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
    			final HttpParams httpParams = httpclient.getParams();
    			HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
    			HttpConnectionParams.setSoTimeout(httpParams, 5000);
    			// construct the URL
    			
    			if(server.contains("http://")) { 
    				url = server;
    			} else {
    				url = "http://" + server;
    			}
    			Log.i("URL", "Server: " + url);
    			if(!server.equals("") || !url.equals("")){
    				
    				//System.out.println("URL " + url + "/getstatus");
    				HttpGet httpget = new HttpGet(url + "/getstatus");
    				// Depends on your web service
    				httpget.setHeader("Content-type", "application/json");
    				
    				InputStream inputStream = null;
    				String result = null;
    				JSONObject inObject = null;
    				HttpResponse response = httpclient.execute(httpget);     
    				//System.out.println("R");
    				HttpEntity entity = response.getEntity();
    	
    				inputStream = entity.getContent();
    				// json is UTF-8 by default i beleive
    				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
    				StringBuilder sb = new StringBuilder();
    	
    				String line = null;
    				while ((line = reader.readLine()) != null)
    				{
    				    sb.append(line + "\n");
    				}
    				result = sb.toString();
    				
    				//System.out.println(result);
    				
    				
    				inObject = new JSONObject(result);
    				
    				// iterate the list for objects
    				Iterator<?> keys = inObject.keys();

    		        while( keys.hasNext() ){
    		            String key = (String)keys.next();
    		            if( inObject.get(key) instanceof JSONObject ){
    		            	iDataCheck((JSONObject)inObject.get(key), key);
    		            }
    		        }
    		        
    		        try {
    					Thread.sleep(500);
    				} catch (InterruptedException e1) {
    					// TODO Auto-generated catch block
    					//e1.printStackTrace();
    				}
    			}
    		} catch (UnknownHostException e) {
    			
    			Log.i("Host Error", "Host " + url + " could not be found");	
    			try {
    				Thread.sleep(10000);
    			} catch (InterruptedException e1) {
    				// TODO Auto-generated catch block
    				//e1.printStackTrace();
    			}
    			
    		} catch (IOException ioe) {
    			ioe.printStackTrace();
    		} catch (IllegalArgumentException iae) {
    			System.out.println("IAE: " + url);
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			try {
    				Thread.sleep(10000);
    			} catch (InterruptedException e1) {
    				// TODO Auto-generated catch block
    				//e1.printStackTrace();
    			}
    		}
    		
    		Log.i("Server", "Finished getting data: " + Data.ITEMS.size());
    	}
    	
    	private Device getDevice(String iName) {
    		if(Data.ITEMS.size() == 0) {
    			return null;
    		}
        	Iterator<Device> iterator = Data.ITEMS.iterator();
     
        	if(Data.ITEMS.get(0).getClass() == PID.class &&  
        			Data.ITEMS.get(0).name.toLowerCase(Locale.CANADA).startsWith(iName.toLowerCase(Locale.CANADA)))
    			return (Device) Data.ITEMS.get(0);
    		while (iterator.hasNext()) {
    			Device temp = iterator.next();
    			if(temp.getClass() == PID.class && 
    					temp.name.toLowerCase(Locale.CANADA).startsWith(iName.toLowerCase(Locale.CANADA)))
    				return (Device)temp;
    		}
    		
        	return null;
    		
    	}
    	
    	private PID getPID(String iName) {
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
    	
    	
    	private Temp getTemp(String iName) {
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
    	
    	
    	private void iDataCheck(JSONObject iData, String device) {
    	
    		try {
    			if(device.indexOf("pid") != -1){
    				// this is the default mannerism, we have a PID
    							
    				// we have a temperature now
    				
    				device = device.toUpperCase(Locale.CANADA);
    				device = device.replaceAll("_", " ");
    				
    				PID tPID = this.getPID(device);
    				if(tPID == null) {
    					//Log.i("No device", "adding: " + Integer.toString(ITEMS.size()+1) + device);
    					
    					Data.addItem(new PID(Integer.toString(Data.ITEMS.size()+1), device));
    					tPID = this.getPID(device);
    					//Log.i("added", "new PID: " + tPID.name);
    				}
    				
    				
    		        tPID.temperature = Double.parseDouble(iData.getString("temp"));
    				tPID.scale = iData.getString("scale");
    				
    		        // check the GPIO
    		        if(iData.getInt("gpio") == -1) {
    		        	return;
    		        }
    		        
    		        // check to see if we need to feedback to the server
    		        
    		        if(tPID.feedback) {
    		        	//Log.i("POST", "Creating params");
    		        	String urlParameters = "dutycycle="+tPID.dutycycle +
    		        			"&cycletime=" + tPID.cycletime +
    		        			"&mode="+tPID.mode +
    		        			"&setpoint=" + tPID.setpoint + 
    		        			"&k=" +tPID.k_param +
    		        			"&i=" +tPID.i_param +
    		        			"&p=" +tPID.p_param;
    		        	//Log.i("Post", "Params: " + url + "/?" + urlParameters);
    		        	urlParameters = url + "/?" + urlParameters;
    		        	// Create a new HttpClient and Post Header
    		        	HttpClient httpclient = new DefaultHttpClient();
    		        	HttpPost httppost = new HttpPost(url);
    		        	
    		        	try {
    		        	    // Add your data
    		        	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    		        	    nameValuePairs.add(new BasicNameValuePair("form", tPID.name.substring(0, tPID.name.indexOf(" "))));
    		        	    nameValuePairs.add(new BasicNameValuePair("mode", tPID.mode));
    		        	    nameValuePairs.add(new BasicNameValuePair("dutycycle", Double.toString(tPID.dutycycle)));
    		        	    nameValuePairs.add(new BasicNameValuePair("cycletime", Double.toString(tPID.cycletime)));
    		        	    nameValuePairs.add(new BasicNameValuePair("setpoint", Double.toString(tPID.setpoint)));
    		        	    nameValuePairs.add(new BasicNameValuePair("p", Double.toString(tPID.p_param)));
    		        	    nameValuePairs.add(new BasicNameValuePair("i", Double.toString(tPID.i_param)));
    		        	    nameValuePairs.add(new BasicNameValuePair("k", Double.toString(tPID.k_param)));
    		        	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

    		        	    // Execute HTTP Post Request
    		        	    HttpResponse response = httpclient.execute(httppost);

    		        	} catch (ClientProtocolException e) {
    		        	    // TODO Auto-generated catch block
    		        	} catch (IOException e) {
    		        	    // TODO Auto-generated catch block
    		        	}		        	
    		        	tPID.feedback = false;
    		        	
    		        } else {
    		        
    			        // after this we should have good Data for the PID
    			        tPID.mode = iData.getString("mode");
    			        tPID.dutycycle = iData.getDouble("duty");
    			        tPID.cycletime = iData.getDouble("cycle");
    			        tPID.elapsedTime = iData.getLong("elapsed");
    			        tPID.k_param = iData.getDouble("k");
    			        tPID.i_param = iData.getDouble("i");
    			        tPID.p_param = iData.getDouble("p");
    			        tPID.setpoint = iData.getDouble("setpoint");
    			        
    		        }
    		        
    			} else {
    				// just probing the temperature
    				// we have a temperature now
    				device = device.toUpperCase(Locale.CANADA);
    				device = device.replaceAll("_", " ");
    				
    				Temp tTemp = this.getTemp(device);
    				
    				
    				if(tTemp == null) {
    					//Log.i("No device", "adding: " + Integer.toString(ITEMS.size()+1) + device);
    					
    					Data.addItem(new Temp(Integer.toString(Data.ITEMS.size()+1), device));
    					tTemp = this.getTemp(device);
    				}
    				
    				tTemp.scale = iData.getString("scale");
    				tTemp.temperature = Double.parseDouble(iData.getString("temp"));
    				
    				//Data.ITEMS.remove(tTemp);
    			}
    			
    			
    		} catch (JSONException je) {
    			Log.i("error", device + je.getMessage());
    			je.printStackTrace();
    		} catch (NumberFormatException ne) {
    			ne.printStackTrace();
    		} 
    	}


    	@Override
    	 protected void onPostExecute(Void result) {
    		
    		/* To Do */
    		
    	}

         

    	
    	



    	
    }