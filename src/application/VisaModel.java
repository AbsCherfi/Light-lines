package application;

public class VisaModel {
	private String name;
	private String surname;
	private String destination;
	private String type;
	private int quantity;
	private double boughtPrice;
	private double salePrice;

	
	
	public VisaModel(String name, String surname, String destination, String type, int quantity, double boughtPrice,
			double salePrice) {
		super();
		this.name = name;
		this.surname = surname;
		this.destination = destination;
		this.type = type;
		this.quantity = quantity;
		this.boughtPrice = boughtPrice;
		this.salePrice = salePrice;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
