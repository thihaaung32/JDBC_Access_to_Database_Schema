package hr.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import hr.dao.BaseDto;
import hr.dao.DeliveryDriverDao;
import hr.dao.DeliveryDriverDto;
import hr.dao.exception.DaoException;

import static util.jdbc.JdbcConnection.getConnection;

/**
 * DeliveryDriverDaoImpl
 *
 * Implementation for DeliveryDriverDao (Data Access Object) 
 *
 * Modifications:
 *
 * 		04/20/2024 - jhui - Created
 * 		11/24/2024 - thihaaung - modified
 */


public class DeliveryDriverDaoImpl extends BaseDaoImpl implements DeliveryDriverDao {
    String _tableName = "delivery_driver";
    String _primaryKey = "delivery_driver_id";
    Properties _driverQueries = null;

    public DeliveryDriverDaoImpl() {
        super();

        _driverQueries = new Properties();
        try {
            _driverQueries.load(this.getClass().getClassLoader().getResourceAsStream("hr/sql/sql.properties"));
            String query = _driverQueries.getProperty("DRIVER_UPDATE_ID");
            System.out.println("\nUpdate query: \n" + query + "\n");
        }
        catch (IOException io) {
            System.out.println("Exception during sql.properties load: " + io);
        }
    }

    public DeliveryDriverDto get(Integer id) throws DaoException {
        return (DeliveryDriverDto) super.get(id);
    }

    public DeliveryDriverDto getRow(String field, Object value) throws DaoException {
        return (DeliveryDriverDto) super.getRow(field, value);
    }


    /**
     * save
     *
     * Convert the DTO into a SQL row and INSERT into the table
     *
     * @param DeliveryDriverDto t - DTO that contains the values for the new row  
     */
    public void save(DeliveryDriverDto t) throws DaoException {
        String query = getInsertQuery();
        try (var connection = getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            // Assuming DeliveryDriverDto has appropriate getter methods
            preparedStatement.setInt(1, t.getDriverId());
            preparedStatement.setString(2, t.getLastName());
            preparedStatement.setString(3, t.getFirstName());
            preparedStatement.setString(4, t.getDriver_licence());
            preparedStatement.setString(5, t.getDriver_ph());

            preparedStatement.executeUpdate();
            System.out.println("Delivery driver saved successfully.");
        } catch (SQLException e) {
            throw new DaoException("Error while saving delivery_driver: " + e.getMessage());
        }
    }

    /**
     * update
     *
     * Update the corresponding row in the database for the DTO with the 
     * values in params
     *
     * @param DeliveryDriverDto t - pull the primary key out of t
     * @param String[] params - values to update the row 
     *
     */
    public void update(DeliveryDriverDto t, String[] params) throws DaoException {
        String query = getUpdateQuery();
        try (var connection = getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }

            preparedStatement.setInt(params.length + 1, t.getDriverId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Delivery driver updated successfully.");
            } else {
                System.out.println("No delivery driver found with the given ID.");
            }
        } catch (SQLException e) {
            throw new DaoException("Error while updating delivery driver: " + e.getMessage());
        }
    }

    /**
     * delete
     *
     * Delete the corresponding row in the database for the DTO
     *
     * @param DeliveryDriverDto t - pull the primary key out of t
     *
     */
    public void delete(DeliveryDriverDto t) throws DaoException {
        String query = getDeleteQuery();
        try (var connection = getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, t.getDriverId());

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Delivery driver deleted successfully.");
            } else {
                System.out.println("No delivery driver found with the given ID.");
            }
        } catch (SQLException e) {
            throw new DaoException("Error while deleting delivery driver: " + e.getMessage());
        }
    }

    /**
     * convertRStoDto
     *
     * Utility method that copies the values in the ResultSet into the DTO.
     * Needed specific implementation for the method getMultipleRows in the
     * BaseDaoImpl.
     *
     * @param ResultSet result - the source values from a query to the DB
     * @param BaseDto dto - the destination Data Transfer Object
     */
    void convertRStoDto(ResultSet result, BaseDto dto) throws DaoException {
        DeliveryDriverDto driverL = (DeliveryDriverDto) dto;
        try {
            driverL.setDriverId(result.getInt(1));
            driverL.setLastName(result.getString(2));
            driverL.setFirstName(result.getString(3));
            driverL.setDriver_licence(result.getString(4));
            driverL.setDriver_ph(result.getString(5));
        }
        catch (SQLException se) {
            throw new DaoException(se.getMessage());
        }
    }

    /**
     * getAllRowsQuery
     *
     * Returns the query for retrieving all rows for this table
     *
     * @return String - equivalent to "select * from tableName"
     */
    String getAllRowsQuery() {
        return _driverQueries.getProperty("DRIVER_GET_ALL");
    }

    /**
     * getInsertQuery
     *
     * Returns the INSERT query for this table
     *
     * @return String - INSERT query
     */
    String getInsertQuery() {
        return _driverQueries.getProperty("DRIVER_INSERT");
    }

    /**
     * getDeleteQuery
     *
     * Returns the DELETE query for this table
     *
     * @return String - DELETE query
     */
    String getDeleteQuery() {
        return _driverQueries.getProperty("DRIVER_DELETE_ID");
    }

    /**
     * getUpdateQuery
     *
     * Returns the UPDATE query for this table
     *
     * @return String - UPDATE query
     */
    String getUpdateQuery() {
        return _driverQueries.getProperty("DRIVER_UPDATE_ID");
    }

    /**
     * getPrimaryKey
     *
     * Returns the Primary Key for this table
     *
     * @return String - Primary Key 
     */
    String getPrimaryKey() {
        return _primaryKey;
    }


    /**
     * getDto
     *
     * Returns the appropriate Data Transfer Object for this Data Access Object.
     *
     * @return appropriate DTO
     */
    BaseDto getDto() {
        return new DeliveryDriverDto();
    }
}
