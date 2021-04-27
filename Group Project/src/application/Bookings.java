package application;

public class Bookings {
	
	String Hotels;
	String Email;
	String checkIn;
	String CheckOut;
	int NumRooms;
	int NumAdults;
	int NumChild;
	
	public String getHotels() {
		return Hotels;
	}
	public void setHotels(String hotels) {
		Hotels = hotels;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return CheckOut;
	}
	public void setCheckOut(String checkOut) {
		CheckOut = checkOut;
	}
	public int getNumRooms() {
		return NumRooms;
	}
	public void setNumRooms(int numRooms) {
		NumRooms = numRooms;
	}
	public int getNumAdults() {
		return NumAdults;
	}
	public void setNumAdults(int numAdults) {
		NumAdults = numAdults;
	}
	public int getNumChild() {
		return NumChild;
	}
	public void setNumChild(int numChild) {
		NumChild = numChild;
	}
	
}
