package edu.ucsb.cs.hw3;

import com.google.android.c2dm.C2DMBaseReceiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class C2DMReceiver extends C2DMBaseReceiver{
	public static final String email = "ucsb.cs.176b@gmail.com";
	public C2DMReceiver() {
		super(email);
		// TODO Auto-generated constructor stub
	}

	public void onRegistered(Context context, String registrationId)
	{
		Log.e("C2DM", "Registration ID arrived: Fantastic!!!");
		Log.e("C2DM", registrationId);
	
	};
	
	@Override
	public void onError(Context context, String errorId) {
		// TODO Auto-generated method stub
		Log.e("C2DM", "fuck");
		
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.e("C2DM", "message: good");
	}

}