package test;

import java.util.List;

import hr.dao.DeliveryDriverDao;
import hr.dao.DeliveryDriverDto;
import hr.dao.exception.DaoException;
import hr.dao.impl.DeliveryDriverDaoImpl;

/**
 * DeliveryDriver
 *
 * Test class to access for DeliveryDriver.
 *
 * Modifications:
 *
 * 		04/20/2024 - jhui - Created
 * 	    11/24/2024 - thihaaung - modified
 */

public class DeliveryDriver {

    public DeliveryDriver() {

    }

    public static void main(String[] args) throws DaoException {
        System.out.println("Entering test.DeliveryDriver.main");

        DeliveryDriverDao driverDao = new DeliveryDriverDaoImpl();
        DeliveryDriverDto driverDto = null;
        List<DeliveryDriverDto> driverList = null;


        try {
            int test = 4;
            switch(test) {
                case 1:
                    driverDto = driverDao.get(Integer.valueOf(1));
                    System.out.println("Returned DeliveryDriver(1):" + driverDto.toJson());
                    break;

                case 2:
                    driverDto = driverDao.getRow("lname", "Doe");
                    System.out.println("Returned DeliveryDriver(1):" + driverDto.toJson());
                    break;

                case 3:
                    driverList = driverDao.getRows("fname", "John");
                    for (int i = 0; i < driverList.size(); i++) {
                        driverDto = driverList.get(i);
                        System.out.println("\nReturned DeliveryDriver(" + driverDto.getDriverId() + "):" + driverDto.toJson());
                    }
                    break;

                default:
                    driverList = driverDao.getAll();
                    for (int i = 0; i < driverList.size(); i++) {
                        driverDto = driverList.get(i);
                        System.out.println("\nReturned DeliveryDriver(" + driverDto.getDriverId() + "):" + driverDto.toJson());
                    }
            }
        }
        catch (Throwable th) {
            System.out.println(th.getMessage());
        }



    }

}
