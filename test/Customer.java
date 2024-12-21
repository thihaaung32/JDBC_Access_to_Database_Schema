package test;

import java.util.List;

import hr.dao.CustomerDao;
import hr.dao.CustomerDto;
import hr.dao.exception.DaoException;
import hr.dao.impl.CustomerDaoImpl;

/**
 * Customer
 * 
 * Test class to access for Customer.
 * 
 * Modifications:
 *
 * 		04/20/2024 - jhui - Created
 * 	    11/24/2024 - thihaaung - modified
 */

public class Customer {

	public Customer() {
		
	}

	public static void main(String[] args) throws DaoException {
		System.out.println("Entering test.Customer.main");
		
		CustomerDao emplDao = new CustomerDaoImpl();
		CustomerDto emplDto = null;
		List<CustomerDto> empls = null;


		try {
			int test = 4;
			switch(test) {
				case 1: 
					emplDto = emplDao.get(Integer.valueOf(1));
					System.out.println("Returned Customer(1):" + emplDto.toJson());
					break;
					
				case 2:
					emplDto = emplDao.getRow("lname", "Doe");
					System.out.println("Returned Customer(1):" + emplDto.toJson());
					break;
					
				case 3:
					empls = emplDao.getRows("fname", "John");
					for (int i = 0; i < empls.size(); i++) {
						emplDto = empls.get(i);
						System.out.println("\nReturned Customer(" + emplDto.getCustomerId() + "):" + emplDto.toJson());
					}
					break;
				
				default:
					empls = emplDao.getAll();
					for (int i = 0; i < empls.size(); i++) {
						emplDto = empls.get(i);
						System.out.println("\nReturned Customer(" + emplDto.getCustomerId() + "):" + emplDto.toJson());
					}
			}			
		}
		catch (Throwable th) {
			System.out.println(th.getMessage());
		}

	}

}
