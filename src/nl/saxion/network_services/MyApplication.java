package nl.saxion.network_services;

import android.app.Application;

public class MyApplication extends Application {
	
	private Model model;
	
	@Override
	public void onCreate() {
		super.onCreate();
		model = new Model();
	}
	
	public Model getModel(){
		return model;
	}

}
