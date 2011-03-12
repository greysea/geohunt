package edu.ucsb.cs.hw3;

import java.io.BufferedReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.c2dm.C2DMessaging;

public class main extends Activity {
	Context context = null;

	// fixed server now; return back to other server later
	public static final String server = "http://marrow.cs.ucsb.edu:3000/";

	public static final String email = "ucsb.cs.176b@gmail.com";
	public static final String status = "status";
	public static final String code = "code";
	public static final String message = "message";
	public static final String response = "response";
	public static final String token = "token";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = this;

		String id = C2DMessaging.getRegistrationId(context);
		if (id != null && !"".equals(id)) {
			// don't register because already registered
			Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
		} else {
			// get registration id
			C2DMessaging.register(main.this, email);
			id = C2DMessaging.getRegistrationId(context);
			Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
		}
	}

	public void onClick(View v) throws IOException, JSONException {
		final EditText name = (EditText) findViewById(R.id.name);
		// final EditText server = (EditText) findViewById(R.id.server);
		String n = name.getText().toString();
		// String ser = server.getText().toString();

		if (n.length() == 0 || server.length() == 0) {
			// need to specify both name and server
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("You need to specify both name and server")
					.setCancelable(true).setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});

			AlertDialog alert = builder.create();
			alert.show();
		} else {
			// register with server
			// send name and server to register class

			String u = server.toString() + "register?name=" + n	+ "&&" + "registration_id=" + C2DMessaging.getRegistrationId(getApplicationContext()) + "&&" + "phonetype=android";

			Log.d("url", u);

			URL url = new URL(u);
			HttpURLConnection reg = (HttpURLConnection) url.openConnection();

			try {
				InputStream rd = new BufferedInputStream(reg.getInputStream());
				
				int c;
				String resp = ""; 
				while((c = rd.read()) != -1)
				{
					char d = (char) c;
					String temp = Character.toString(d);
					resp= resp+ temp;
				}
				Log.d("response", resp);
	
				JSONTokener toke = new JSONTokener(resp);
				JSONObject parse = new JSONObject(toke);

				JSONObject stat = parse.getJSONObject(status.toString());
				JSONObject res = parse.getJSONObject(response.toString());

				if (stat.getInt(code) == 0) {
					// success
					String t = res.getString(token);
					Intent in = new Intent(context, register.class);
					in.putExtra(token, t);
					startActivity(in);
				} else if (stat.getInt(code) == 99) {
					// failed to create user
					Toast.makeText(context, "Failed to create user",
							Toast.LENGTH_SHORT).show();
				}
				rd.close();
			} finally {
	
				reg.disconnect();
			}

		}
	}
}