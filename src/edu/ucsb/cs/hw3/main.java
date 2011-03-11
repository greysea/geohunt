package edu.ucsb.cs.hw3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.ucsb.cs.c2dm.C2DMessaging;

public class main extends Activity {
	Context context = null;
	final public String email = "ucsb.cs.176b@gmail.com";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;
        String id = C2DMessaging.getRegistrationId(context);
  		Toast.makeText(main.this, id, Toast.LENGTH_SHORT).show();
    }
    
    public void onClick(View v) {
    	
    	
          
    	final EditText name = (EditText) findViewById(R.id.name);
        final EditText server = (EditText) findViewById(R.id.server);
        
		Intent in = new Intent(context, register.class);
		if( name.getText().length() == 0 || server.getText().length() == 0)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("You need to specify both name and server")
			       .setCancelable(true)
			       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });

			AlertDialog alert = builder.create();	
			alert.show();
		}
		else
		{
			startActivity(in);
		}
	}
}