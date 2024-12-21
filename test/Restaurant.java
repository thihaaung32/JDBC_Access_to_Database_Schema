package test;

import java.util.List;

import hr.dao.RestaurantDao;
import hr.dao.RestaurantDto;
import hr.dao.impl.RestaurantDaoImpl;

/**
 * Restaurant
 * 
 * Test class to access for Restaurant.
 * 
 * Modifications:
 *
 * 		04/20/2024 - jhui - Created
 * 	    11/24/2024 - thihaaung - modified
 */

public class Restaurant {

	public Restaurant() {
		
	}

	public static void main(String[] args) {
		System.out.println("Entering test.Restaurant.main");
		
		RestaurantDao deptDao = new RestaurantDaoImpl();
		RestaurantDto deptDto = null;
		List<RestaurantDto> depts = null;
		
		try {
			int test = 4;
			switch(test) {
				case 1: 
					deptDto = deptDao.get(Integer.valueOf(1001));
					System.out.println("Returned Restaurant(1):" + deptDto.toJson());
					break;
					
				case 2:
					deptDto = deptDao.getRow("restaurant_name", "Burger King");
					System.out.println("Returned Restaurant(1):" + deptDto.toJson());
					break;
					
				case 3:
					depts = deptDao.getRows("manager_id", "100");
					for (int i = 0; i < depts.size(); i++) {
						deptDto = depts.get(i);
						System.out.println("\nReturned Restaurant(" + deptDto.getRestaurant_id() + "):" + deptDto.toJson());
					}
					break;
				
				default:
					depts = deptDao.getAll();
					for (int i = 0; i < depts.size(); i++) {
						deptDto = depts.get(i);
						System.out.println("\nReturned Restaurant(" + deptDto.getRestaurant_id() + "):" + deptDto.toJson());
					}
			}			
		}
		catch (Throwable th) {
			System.out.println(th.getMessage());
		}
	}
}
