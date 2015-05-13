package nl.saxion.network_services;

public class UserMention extends Entity{
	
	private String id;
	private String screenName;
	
	public UserMention(int begin, int end, String id, String screenName){
		super(begin, end);
		this.id = id;
		this.screenName = screenName;
	}

}
