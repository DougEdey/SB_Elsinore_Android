package com.strangebrew.elsinore;

import com.strangebrew.elsinore.R;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

	public static final String KEY_SERVER_NAME_PREFERENCE = "pref_key_server_name";
    public static final String KEY_SERVER_PORT_PREFERENCE = "pref_key_server_port";
    private static final String KEY_SERVER_REFRESH_PREFERENCE = "pref_key_server_refresh";
    
    private EditTextPreference mServerNamePreference;
    private EditTextPreference mServerPortPreference;
    private EditTextPreference mServerRefreshPreference;
    
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	

        // Load the XML preferences file
        addPreferencesFromResource(R.layout.preference);

        // Get a reference to the preferences
        mServerNamePreference = (EditTextPreference)getPreferenceScreen().findPreference(KEY_SERVER_NAME_PREFERENCE);
        mServerPortPreference = (EditTextPreference)getPreferenceScreen().findPreference(KEY_SERVER_PORT_PREFERENCE);
        mServerRefreshPreference = (EditTextPreference)getPreferenceScreen().findPreference(KEY_SERVER_REFRESH_PREFERENCE);
        
        mServerNamePreference.setSummary(this.getActivity().getSharedPreferences("elsinore", 0).getString(KEY_SERVER_NAME_PREFERENCE, "")); 
        mServerPortPreference.setSummary(this.getActivity().getSharedPreferences("elsinore", 0).getString(KEY_SERVER_PORT_PREFERENCE, "")); 
        mServerRefreshPreference.setSummary(this.getActivity().getSharedPreferences("elsinore", 0).getString(KEY_SERVER_REFRESH_PREFERENCE, "5")); 
        

	}
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
	        // Let's do something a preference value changes
	    if (key.equals(KEY_SERVER_NAME_PREFERENCE)) {
	    	mServerNamePreference.setSummary(sharedPreferences.getString(key, "")); 
	    }
	    else if (key.equals(KEY_SERVER_PORT_PREFERENCE)) {
	        mServerPortPreference.setSummary(sharedPreferences.getString(key, "")); 
	    } else if (key.equals(KEY_SERVER_REFRESH_PREFERENCE)) {
	        mServerRefreshPreference.setSummary(sharedPreferences.getString(key, "")); 
	    }
	}
	
	@Override
	public void onPause() {
        super.onPause();

        // Unregister the listener whenever a key changes            
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);    
    }
	
	@Override
	public void onResume() {
        super.onResume();

        // Setup the initial values
        mServerNamePreference = (EditTextPreference)getPreferenceScreen().findPreference(KEY_SERVER_NAME_PREFERENCE);
        mServerPortPreference = (EditTextPreference)getPreferenceScreen().findPreference(KEY_SERVER_PORT_PREFERENCE);
        mServerRefreshPreference = (EditTextPreference)getPreferenceScreen().findPreference(KEY_SERVER_REFRESH_PREFERENCE);

        // Set up a listener whenever a key changes            
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
}