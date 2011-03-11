package edu.ucsb.cs.hw3;

import android.content.Context;
import android.content.Intent;

import edu.ucsb.cs.c2dm.C2DMBaseReceiver;
public class CS2MReceiver extends C2DMBaseReceiver{

	public CS2MReceiver(String senderId) {
		super(senderId);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onError(Context context, String errorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
	}

}
