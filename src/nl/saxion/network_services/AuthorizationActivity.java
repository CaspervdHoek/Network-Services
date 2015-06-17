package nl.saxion.network_services;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AuthorizationActivity extends Activity {
	
	private WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginscreen);
		
		webview = (WebView) findViewById(R.id.webView1);
		
		WebviewTask wv = new WebviewTask(webview);
		wv.execute();
		
		webview.setWebViewClient(new WebViewClient() {
			
			public boolean shoulderOverrideUrlLoading(WebView view, String url){
				if (url.startsWith("http://9gag.com")){
					return true;
					// succes!
				}
				return false;
			}
		});
		
		
		
	}

}
