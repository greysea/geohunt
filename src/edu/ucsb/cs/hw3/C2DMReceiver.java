package edu.ucsb.cs.hw3;

import com.google.android.c2dm.C2DMBaseReceiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class C2DMReceiver extends C2DMBaseReceiver{
	final static String email = "ucsb.cs.176b@gmail.com";
	public C2DMReceiver() {
		// TODO Auto-generated constructor stub
		super(email);
	}

	public void onRegistered(Context context, String registrationId)
	{
	
	};
	
	@Override
	public void onError(Context context, String errorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.e("C2DM", "message: good");
	}

}