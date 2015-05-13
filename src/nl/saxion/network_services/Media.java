package nl.saxion.network_services;

public class Media extends Entity{
	
	private String displayURL;
	private String id;
	private String mediaURL;
	private String sourceStatusId;
	private String type;
	
	public Media(int begin, int end, String displayURL, String id, String mediaURL, String sourceStatusId, String type){
		super(begin, end);
		this.displayURL = displayURL;
		this.id = id;
		this.mediaURL = mediaURL;
		this.sourceStatusId = sourceStatusId;
		this.type = type;
	}

}
