package nl.saxion.network_services;


import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import android.os.AsyncTask;
import android.util.Base64;

public class SearchTweetTask extends AsyncTask {
	
	private static final String API_KEY = "fmPbMIYHurHra0Np8qEhh9IS8";
	private static final String API_SECRET = "8YcCA0V7dUwgzvsLWMTJfaX6xSMVdrREMjHKXEX0z6MBqz2j7e";
	
	String authString = API_KEY + ":" + API_SECRET;
	String base64 = Base64.encodeToString(authString.getBytes(), Base64.NO_WRAP);
	
	HttpPost request = new HttpPost("https://api.twitter.com/oauth2/token");
	
	

	@Override
	protected Object doInBackground(Object... params) {
		request.setHeader("Authorization", "Basic " + base64);
		request.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		try {
			request.setEntity(new StringEntity("grant_type=client_credentials"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
					