package application;

public class BilletTableModel {
	private String name;
	private String surname;
	private String departureDate;
	private String returnDate;
	private int quantity;
	private double boughtPrice;
	private double salePrice;//do we not need a destination
	

	public BilletTableModel(String name, String surname, String departureDate, String returnDate, int quantity,
			double boughtPrice, double salePrice) {
		super();
		this.name = name;
		this.surname = surname;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
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
