package hr.dao;

/**
 * DeliveryDriverDto
 *
 * Data Transfer Object for DeliveryDriver
 *
 * Modifications:
 *
 * 		04/20/2024 - jhui - Created
 * 	    11/24/2024 - thihaaung - modified
 */

public class DeliveryDriverDto extends BaseDto {
    int driver_id;
    String lastName;
    String firstName;
    String driver_licence;
    String driver_ph;
    
    public DeliveryDriverDto() {
        super();
    }

    public void setDriverId(int id) {
        driver_id = id;
    }

    public int getDriverId() {
        return driver_id;
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

    public void setDriver_licence(String email) {
        driver_licence = email;}

    public String getDriver_licence() {
        return driver_licence;}

    public void setDriver_ph(String ph) {
        driver_ph = ph;
    }

    public String getDriver_ph() {
        return driver_ph;
    }

}
