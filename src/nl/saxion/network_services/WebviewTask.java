package nl.saxion.network_services;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewTask extends AsyncTask<String, Void, String> {

	private static final String OAUTH_REQUEST_URL = "https://api.twitter.com/oauth/request_token";
	private static final String OAUTH_ACCESSTOKEN_URL = "https://api.twitter.com/oauth/access_token";
	private static final String OAUT_AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
	private static final String CONSUMER_KEY = "fmPbMIYHurHra0Np8qEhh9IS8";
	private static final String CONSUMER_SECRET = "8YcCA0V7dUwgzvsLWMTJfaX6xSMVdrREMjHKXEX0z6MBqz2j7e";
	private static final String OAUTH_CALLBACK_URL = "http://9gag.com";
	private CommonsHttpOAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	private WebView webview;
	private Model model;
	
	public WebviewTask(WebView webview, Model model){
		this.webview = webview;
		this.model = model;
		provider = model.getProvider();
		consumer = model.getConsumer();
	}
	
	@Override
	protected String doInBackground(String... params) {
		String url = null;
		try {
			url = provider.retrieveRequestToken(consumer, OAUTH_CALLBACK_URL);
			Log.d("p url", url);
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return url;
	}


	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
				
		webview.setWebViewClient(new WebViewClient() {
			
			public boolean shoulderOverrideUrlLoading(WebView view, String url){
				if (url.startsWith("http://9gag.com")){
					Log.d("override", "succes");
					return true;
					// succes!
				}
				return false;
			}
		});
		
		webview.loadUrl(result);

	}

}
