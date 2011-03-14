package edu.ucsb.cs.hw3;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.location.LocationProvider;
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
	static final String n = "n";
	static final String error = "Error!";

	public static JSONObject parse(URLConnection reg) throws IOException,
			JSONException {
		DataInputStream rd = new DataInputStream(reg.getInputStream());
		String c;
		String resp = "";
		try {
			while ((c = rd.readLine()) != null) {
				resp = resp + c;
			}
			Log.d(response, resp);
		} finally {
			rd.close();
		}

		JSONTokener toke = new JSONTokener(resp);
		JSONObject p = new JSONObject(toke);
		return p;
	}

	// http://marrow.cs.ucsb.edu:3000/query?token=3fcdcf00836d82e87c0b27a0555ca6bb78b6cc28&&longitude=-30.45&&latitude=23.4&&cid=1
	public static int getN(String token, String server) throws IOException,
			JSONException {
		String u = server + "get_n?token=" + token;

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
			int num = res.getInt(n);
			return num;
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
	public static String checkin(String server, int cid, double lon,
			double lat, String token) throws IOException, JSONException {
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

		int err = stat.getInt(code);
		if (err == 0) {
			// success
			String mess = stat.getString(message);
			Log.d("message", mess);
			return mess;
		}
		// else failed, return fail error
		else {
			//String e = error(err);
			return error;
		}
		// toast error messages
		// - check in more than once
		// - out of range
		// - check in at other cids before uploaded pics
	}

	// http://marrow.cs.ucsb.edu:3000/cancel_checkin?token=3fcdcf00836d82e87c0b27a0555ca6bb78b6cc28&&cid=1
	public static int cancel_checkin(String server, int cid, String token)
			throws IOException, JSONException {
		// send query
		String u = server + "cancel_checkin?token=" + token + "&&" + "cid="
				+ Integer.toString(cid);
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


	public static void showMap() {

	}

	/*
	 * #1 - already registered # 10 - out of location's range, 11 - not locked
	 * to right location, 12 - locked to other location # 20 - no game in
	 * progress # 80 - server error # 90 - non-unique registration_id, 96 -
	 * invalid location, 98 - invalid token, 99 - missing parameters
	 */
	public static String error(int err) {
		String s;
		switch (err) {
		case 1:
			s = "Already Registered";
			break;
		case 10:
			s = "Out of location range";
			break;
		case 11:
			s = "Not locked to right location";
			break;
		case 12:
			s = "locked to other location";
		case 20:
			s = "no game in progress";
			break;
		case 80:
			s = "server error";
			break;
		case 90:
			s = "no unique registration_id";
			break;
		case 96:
			s = "invalid location";
			break;
		case 98:
			s = "invalid token";
			break;
		case 99:
			s = "missing parameters";
			break;
		default:
			s = error;
		}
		return s;
	}
}
