package edu.ucsb.cs.hw3;

import java.io.IOException;

import org.json.JSONException;

import com.google.android.maps.MapActivity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;

import android.app.Activity;
import android.content.Context;


public class register extends MapActivity {

	final String token = "token";
	final String checkin = "Checkin";
	final String canchk = "Cancel Checkin";
	final String upload = "Upload";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		
	}

	/*
	 * @Override(non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 1, checkin);
		menu.add(1, 2, 2, canchk);
		menu.add(1, 3, 3, upload);
		return super.onCreateOptionsMenu(menu);

	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.checkin:
			checkin();
			return true;
		case R.id.canchk:
			canchk();
			return true;
		case R.id.upload:
			upload();
			return true;
		}
		return false;
	}
*/
	public void checkin()
	{
		
	}
	
	public void canchk()
	{
		
	}
	
	public void upload()
	{
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	


}
