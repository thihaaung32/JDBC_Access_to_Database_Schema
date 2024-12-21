package hr.dao;

/**
 * RestaurantDto
 *
 * Data Transfer Object for Restaurant
 *
 * Modifications:
 *
 * 		04/20/2024 - jhui - Created
 * 	    11/24/2024 - thihaaung - modified
 */

public class RestaurantDto extends BaseDto {
	int restaurant_id;
	String restaurant_name;
	String restaurant_ph;
	String email;
	int managerId;
	int restaurant_owner_id;

	public RestaurantDto() {
		super();
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restId) {
		this.restaurant_id = restId;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String name) {
		this.restaurant_name = name;
	}

	public String getRestaurant_ph() {
		return restaurant_ph;
	}

	public void setRestaurant_ph(String phone) {
		this.restaurant_ph = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int id) {
		this.managerId = id;
	}

	public int getRestaurantOwnerId() {
		return restaurant_owner_id;
	}

	public void setRestaurantOwnerId(int id) {
		this.restaurant_owner_id = id;
	}
	
}
