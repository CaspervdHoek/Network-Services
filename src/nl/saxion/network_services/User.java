package nl.saxion.network_services;

public class User {

	private String id;
	private String description;
	private int followersCount;
	private int friendsCount;
	private int listedCount;
	private String location;
	private String name;
	private String profileBackgroundColor;
	private String profileBackgroundImageURL;
	private String profileBannerURL;
	private String profileImageURL;
	private boolean profileUseBackgroundImage;
	private boolean accountProtected;
	private String screenName;
	private Tweet status;
	
	public User(String id, String description, int followersCount, int friendsCount, int listedCount,
			String location, String name, String profileBackgroundColor, String profileBackgroundImageURL,
			String profileBannerURL, String profileImageURL, boolean profileUseBackgroundImage,
			boolean accountProtected, String screenName, Tweet status){
		
		this.id = id;
		this.description = description;
		this.followersCount = followersCount;
		this.friendsCount = friendsCount;
		this.listedCount = listedCount;
		this.location = location;
		this.name = name;
		this.profileBackgroundColor = profileBackgroundColor;
		this.profileBackgroundImageURL = profileBackgroundImageURL;
		this.profileBannerURL = profileBannerURL;
		this.profileImageURL = profileImageURL;
		this.profileUseBackgroundImage = profileUseBackgroundImage;
		this.accountProtected = accountProtected;
		this.screenName = screenName;
		this.status = status;
	}
}
