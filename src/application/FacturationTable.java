package application;

public class FacturationTable {
	private String name;
	private String surname;
	private double totalPrice;
	private String serviceType;
	private String bookingTime;
	

	public FacturationTable(String name, String surname, double totalPrice, String serviceType, String bookingTime) {
		super();
		this.name = name;
		this.surname = surname;
		this.totalPrice = totalPrice;
		this.serviceType = serviceType;
		this.bookingTime = bookingTime;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

}