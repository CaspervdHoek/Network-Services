package nl.saxion.network_services;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ProfilePhotoTask extends AsyncTask<String, Void, Bitmap> {

	ImageView bitmapImage;
	
	public ProfilePhotoTask(ImageView imageview){
		this.bitmapImage = imageview;
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		String url = params[0];
		Bitmap image = null;
		
		try{
			InputStream in = new java.net.URL(url).openStream();
			image = BitmapFactory.decodeStream(in);
		} catch (Exception e){
			
		}
		
		return image;
	}
	
	protected void onPostExecute(Bitmap result){
		bitmapImage.setImageBitmap(result);
	}
	

}
