package hr.dao;

/**
 * CustomerDto
 * 
 * Data Transfer Object for Customer
 *   
 * Modifications:
 *
 * 		04/20/2024 - jhui - Created
 * 	    11/24/2024 - thihaaung - modified
 */

public class CustomerDto extends BaseDto {
	int customer_id;
	String lastName;
	String firstName;
	String customer_ph;
	String customer_email;

	public CustomerDto() {
		super();
	}
	
	public void setCustomerId(int id) {
		customer_id = id;
	}
	
	public int getCustomerId() {
		return customer_id;
	}
	
	public void setLastName(String name) {
		lastName = name;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String name) {
		firstName = name;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setCustomer_ph(String ph) {
		customer_ph = ph;
	}
	
	public String getCustomer_ph() {
		return customer_ph;
	}

	public void setCustomer_email(String email) {customer_email = email;}

	public String getCustomer_email() {return customer_email;}

}
