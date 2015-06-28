package nl.saxion.network_services;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Class haalt accesstoken op nadat er een verifier token is opgehaald
 * @author Viradj
 *
 */
public class AccessTokenTask extends AsyncTask<String, Void, String> {

	private Model model;
	private CommonsHttpOAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	private MyApplication app;
	
	public AccessTokenTask(Model model){
		this.model = model;
		provider = model.getProvider();
		consumer = model.getConsumer();
	}
	
	/**
	 * De provider haalt met de verifier token die wordt meegegeven de accestoken op
	 */
	@Override
	protected String doInBackground(String... params) {
		try {
			provider.retrieveAccessToken(consumer, params[0]);
			Log.d("token", consumer.getToken());
		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		
	}

}
