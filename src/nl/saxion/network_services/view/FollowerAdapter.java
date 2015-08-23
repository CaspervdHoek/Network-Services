package nl.saxion.network_services.view;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.network_services.ProfilePhotoTask;
import nl.saxion.network_services.R;
import nl.saxion.network_services.Tweet;
import nl.saxion.network_services.User;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FollowerAdapter extends ArrayAdapter<User> {
	
	private LayoutInflater inflater;
	private int resource;

	public FollowerAdapter(Context context, int resource, ArrayList<User> objects) {
		super(context, resource, objects);
		inflater = LayoutInflater.from(context);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(resource, parent, false);
		}
		
		TextView screenName = (TextView) convertView.findViewById(R.id.realnameF);
		TextView name = (TextView) convertView.findViewById(R.id.usernameF);
		ImageView foto = (ImageView) convertView.findViewById(R.id.profilepicture);
		
		User user = getItem(position);
		Log.d("User emptiness test", user.getName());
		ProfilePhotoTask ppt = new ProfilePhotoTask(foto);
		
		Log.d("TextView emptiness test", Boolean.toString(name == null));
		
		name.setText(user.getName());
		Log.d("User emptiness test2", user.getName());
		screenName.setText("@" + user.getScreenName());
		Log.d("User emptiness test3", user.getName());
		screenName.setTextColor(Color.GRAY);
		if (user.getProfileImageURL() != null){
			ppt.execute(user.getProfileImageURL());
		}
		
		return convertView;
	}

}
