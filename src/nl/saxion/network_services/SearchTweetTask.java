package nl.saxion.network_services;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

public class SearchTweetTask extends AsyncTask<String, Double, String> {
	
	private static final String API_KEY = "fmPbMIYHurHra0Np8qEhh9IS8";
	private static final String API_SECRET = "8YcCA0V7dUwgzvsLWMTJfaX6xSMVdrREMjHKXEX0z6MBqz2j7e";
	
	String authString = API_KEY + ":" + API_SECRET;
	String base64 = Base64.encodeToString(authString.getBytes(), Base64.NO_WRAP);
	
	HttpClient client = new DefaultHttpClient();
	ResponseHandler<String> handler = new BasicResponseHandler();
	
	String token;
	
	private void getBearerToken(){
		
		HttpPost request = new HttpPost("https://api.twitter.com/oauth2/token");
				
		request.setHeader("Authorization", "Basic " + base64);
		request.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
		String result;
		try {
			request.setEntity(new StringEntity("grant_type=client_credentials"));
			result = client.execute(request, handler);
			
			JSONObject response = new JSONObject(result);
			
			token = response.getString("access_token");
			Log.d("test getToken", token);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected String doInBackground(String... params) {
		
		if(token == null){
			getBearerToken();
		}
		
		HttpGet httpGet = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q=appelflap");
		httpGet.setHeader("Authorization", "Bearer " + token);
		String searchJSON = null;
		try {
			searchJSON = client.execute(httpGet, handler);
			Log.d("result", searchJSON);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchJSON;
	}

	

}
					