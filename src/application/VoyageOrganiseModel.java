package application;

public class VoyageOrganiseModel {
	private String name;
	private String surname;
	private String destination;
	private int quantity;
	private double boughtPrice;
	private double salePrice;
	private String departureDate;
	private String returnDate;
	
	
	public VoyageOrganiseModel(String name, String surname, String destination, int quantity, double boughtPrice,
			double salePrice, String departureDate, String returnDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.destination = destination;
		this.quantity = quantity;
		this.boughtPrice = boughtPrice;
		this.salePrice = salePrice;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
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


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getBoughtPrice() {
		return boughtPrice;
	}


	public void setBoughtPrice(double boughtPrice) {
		this.boughtPrice = boughtPrice;
	}


	public double getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}


	public String getDepartureDate() {
		return departureDate;
	}


	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}


	public String getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	
	
}
