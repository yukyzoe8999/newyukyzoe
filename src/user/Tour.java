package user;

public class Tour {
	int id;
	int location_num;
	String location;
	String recap;
	String information;
	
	
	public Tour(int id, int location_num, String location, String recap, String information) {
		this.id = id;
		this.location_num = location_num;
		this.location = location;
		this.recap = recap;
		this.information = information;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLocation_num() {
		return location_num;
	}
	public void setLocation_num(int location_num) {
		this.location_num = location_num;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRecap() {
		return recap;
	}
	public void setRecap(String recap) {
		this.recap = recap;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	
	
}
