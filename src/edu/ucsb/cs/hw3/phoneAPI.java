package edu.ucsb.cs.hw3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.c2dm.C2DMessaging;

public class phoneAPI {
	static final String email = "ucsb.cs.176b@gmail.com";
	static final String status = "status";
	static final String code = "code";
	static final String message = "message";
	static final String response = "response";
	static final String token = "token";
	static final String distance = "distance";
	static final String angle = "angle";
	static final String N = "N";
	static final String error = "ERROR!";

	private static JSONObject parse(HttpURLConnection reg) throws IOException,
			JSONException {
		InputStream rd = new BufferedInputStream(reg.getInputStream());

		int c;
		String resp = "";
		while ((c = rd.read()) != -1) {
			char d = (char) c;
			String temp = Character.toString(d);
			resp = resp + temp;
		}
		rd.close();
		Log.d("response", resp);

		JSONTokener toke = new JSONTokener(resp);
		JSONObject p = new JSONObject(toke);
		return p;
	}

	// http://marrow.cs.ucsb.edu:3000/query?token=3fcdcf00836d82e87c0b27a0555ca6bb78b6cc28&&longitude=-30.45&&latitude=23.4&&cid=1
	public static int getN(String token, String server) throws IOException,
			JSONException {
		String u = server + "getN?token=" + token;

		Log.d("url", u);
		URL url = new URL(u);
		HttpURLConnection reg = (HttpURLConnection) url.openConnection();

		// query response
		JSONObject p;
		JSONObject stat;
		try {
			p = parse(reg);
			stat = p.getJSONObject(status);
		} finally {
			reg.disconnect();
		}
		if (stat.getInt(code) == 0) {
			// success
			// send token to register class
			JSONObject res = p.getJSONObject(response);
			int n = res.getInt(N);
			return n;
		}
		// else fail, return fail error
		return -1;
	}

	// c2dm stuff
	public static String receive_notification() {
		String s = null;

		return s;

	}

	// http://marrow.cs.ucsb.edu:3000/query?token=3fcdcf00836d82e87c0b27a0555ca6bb78b6cc28&&longitude=-30.45&&latitude=23.4&&cid=1
	public static double[] query(String server, int cid, double lat,
			double lon, String token) throws IOException, JSONException {
		double dis, ang;

		// send query
		String u = server + "query?token=" + token + "&&" + "longitude="
				+ Double.toString(lon) + "&&" + "latitude="
				+ Double.toString(lat) + "&&" + "cid=" + Integer.toString(cid);
		Log.d("url", u);
		URL url = new URL(u);
		HttpURLConnection reg = (HttpURLConnection) url.openConnection();

		// query response
		JSONObject p;
		JSONObject stat;
		try {
			p = parse(reg);
			stat = p.getJSONObject(status);
		} finally {
			reg.disconnect();
		}
		if (stat.getInt(code) == 0) {
			// success
			// send token to register class
			JSONObject res = p.getJSONObject(response);
			dis = res.getDouble(distance);
			ang = res.getDouble(angle);
			double[] loc = { dis, ang };
			return loc;

		}
		// else failed, return fail error
		double[] err = { -123456789, -123456789 };
		return err;
	}

	// http://marrow.cs.ucsb.edu:3000/checkin?token=3fcdcf00836d82e87c0b27a0555ca6bb78b6cc28&&longitude=-33&&latitude=123.4&&cid=1
	public static String checkin(String server, int cid, double lon, double lat,
			String token) throws IOException, JSONException {
		// send query
		String u = server + "checkin?token=" + token + "&&" + "longitude="
				+ Double.toString(lon) + "&&" + "latitude="
				+ Double.toString(lat) + "&&" + "cid=" + Integer.toString(cid);
		Log.d("url", u);
		URL url = new URL(u);
		HttpURLConnection reg = (HttpURLConnection) url.openConnection();

		// query response
		JSONObject p;
		JSONObject stat;
		try {
			p = parse(reg);
			stat = p.getJSONObject(status);
		} finally {
			reg.disconnect();
		}
		if (stat.getInt(code) == 0) {
			// success
			String mess = stat.getString(message);
			Log.d("message", mess);
			return mess;
		}
		// else failed, return fail error
		
		// toast error messages
		// - check in more than once
		// - out of range
		// - check in at other cids before uploaded pics
		return error;

	}

	// http://marrow.cs.ucsb.edu:3000/cancel_checkin?token=3fcdcf00836d82e87c0b27a0555ca6bb78b6cc28&&cid=1
	public static int cancel_checkin(String server, int cid, String token) throws IOException, JSONException {
		// send query
		String u = server + "cancel_checkin?token=" + token + "&&" + "cid=" + Integer.toString(cid);
		Log.d("url", u);
		URL url = new URL(u);
		HttpURLConnection reg = (HttpURLConnection) url.openConnection();

		// query response
		JSONObject p;
		JSONObject stat;
		try {
			p = parse(reg);
			stat = p.getJSONObject(status);
		} finally {
			reg.disconnect();
		}
		if (stat.getInt(code) == 0) {
			// success
			return 1;
		}
		// else failed, return fail error
		return 0;
	}

	public static String upload(String server, int cid, String token,
			String image) {
		String s = null;
		return s;
	}

	public static int get_picture(String server) {
		return 0;
	}

	public static int getCurrentLocation() {
		return 0;
	}

	public static void showMap() {

	}
}
