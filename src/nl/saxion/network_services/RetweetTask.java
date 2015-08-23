package nl.saxion.network_services;

import java.io.IOException;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class RetweetTask extends AsyncTask<String, Double, String> {
	
	private Model model;
	private CommonsHttpOAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	private ProfileActivity activity;
	
	HttpClient client = new DefaultHttpClient();
	ResponseHandler<String> handler = new BasicResponseHandler();
	
	public RetweetTask(Model model, ProfileActivity activity){
		this.model = model;
		this.provider = model.getProvider();
		this.consumer = model.getConsumer();
	}

	@Override
	protected String doInBackground(String... params) {
		
		HttpPost request = new HttpPost("https://api.twitter.com/1.1/statuses/retweet/" + params[0] + ".json");
		String result = null;
		
		try {
			consumer.sign(request);
			result = client.execute(request, handler);
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
