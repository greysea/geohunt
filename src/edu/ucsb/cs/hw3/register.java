package edu.ucsb.cs.hw3;

import java.io.IOException;

import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class register extends Activity {
	final String token = "token";
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        Intent starter = getIntent();
        String t = starter.getStringExtra(token);
    	String q = null;
    	int cancel = 0;
        try {
        	String s;
        	http://marrow.cs.ucsb.edu:3000/checkin?token=3fcdcf00836d82e87c0b27a0555ca6bb78b6cc28&&longitude=-33&&latitude=123.4&&cid=1
			 q = phoneAPI.checkin("http://marrow.cs.ucsb.edu:3000/", 1, -33, 123.4, t);
        	cancel = phoneAPI.cancel_checkin("http://marrow.cs.ucsb.edu:3000/", 1, t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("mess", q);
		Log.d("cancel ", Integer.toString(cancel));
		
    }



	
}
