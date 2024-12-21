package hr.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import hr.dao.BaseDto;
import hr.dao.exception.DaoException;

import hr.dao.RestaurantDao;
import hr.dao.RestaurantDto;

import static util.jdbc.JdbcConnection.getConnection;

/**
 * RestaurantDaoImpl
 *
 * Implementation for RestaurantDao (Data Access Object)
 *
 * Modifications:
 *
 * 		04/20/2024 - jhui - Created
 * 		11/24/2024 - thihaaung - modified
 */

public class RestaurantDaoImpl extends BaseDaoImpl implements RestaurantDao {
	String _tableName = "restaurant";
	String _primaryKey = "restaurant_id";
	Properties _restQueries = null;
	
	public RestaurantDaoImpl() {
		super();
		
		_restQueries = new Properties();
		try {
			_restQueries.load(this.getClass().getClassLoader().getResourceAsStream("hr/sql/sql.properties"));
			//String query = _empQueries.getProperty("REST_UPDATE");
			//System.out.println("Update query: \n" + query);
		}
		catch (IOException io) {
			System.out.println("Exception during sql.properties load: " + io);
		}
	}
	
	public RestaurantDto get(Integer id) throws DaoException {
		return (RestaurantDto) super.get(id);
	}
	
	public RestaurantDto getRow(String field, Object value) throws DaoException {
		return (RestaurantDto) super.getRow(field, value);
	}
	
	
	/**
	 * save
	 * 
	 * Convert the DTO into a SQL row and INSERT into the table
	 *   
	 * @param RestaurantDto t - DTO that contains the values for the new row
	 */
    public void save(RestaurantDto t) throws DaoException {
		String query = getInsertQuery();
		try (var connection = getConnection();
			 var preparedStatement = connection.prepareStatement(query)) {

			// Assuming CustomerDto has appropriate getter methods
			preparedStatement.setInt(1, t.getRestaurant_id());
			preparedStatement.setString(2, t.getRestaurant_name());
			preparedStatement.setInt(3, t.getManagerId());
			preparedStatement.setString(4, t.getRestaurant_ph());
			preparedStatement.setString(5, t.getEmail());
			preparedStatement.setInt(6, t.getRestaurantOwnerId());

			preparedStatement.executeUpdate();
			System.out.println("Restaurant saved successfully.");
		} catch (SQLException e) {
			throw new DaoException("Error while saving restaurant: " + e.getMessage());
		}
    }
    
	/**
	 * update
	 * 
	 * Update the corresponding row in the database for the DTO with the 
	 * values in params
	 *   
	 * @param RestaurantDto t - pull the primary key out of t
	 * @param String[] params - values to update the row 
	 * 	 
	 */
    public void update(RestaurantDto t, String[] params) throws DaoException {
		String query = getUpdateQuery();
		try (var connection = getConnection();
			 var preparedStatement = connection.prepareStatement(query)) {

			// Assuming `params` contains the updated values in the correct order
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setString(i + 1, params[i]);
			}

			// Set the primary key value at the end
			preparedStatement.setInt(params.length + 1, t.getRestaurant_id());

			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Restaurant updated successfully.");
			} else {
				System.out.println("No restaurant found with the given ID.");
			}
		} catch (SQLException e) {
			throw new DaoException("Error while updating restaurant: " + e.getMessage());
		}
    }
    
	/**
	 * delete
	 * 
	 * Delete the corresponding row in the database for the DTO
	 *   
	 * @param RestaurantDto t - pull the primary key out of t
	 * 	 
	 */
    public void delete(RestaurantDto t) throws DaoException {
		String query = getDeleteQuery();
		try (var connection = getConnection();
			 var preparedStatement = connection.prepareStatement(query)) {

			// Set the primary key value
			preparedStatement.setInt(1, t.getRestaurant_id());

			int rowsDeleted = preparedStatement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Customer deleted successfully.");
			} else {
				System.out.println("No customer found with the given ID.");
			}
		} catch (SQLException e) {
			throw new DaoException("Error while deleting customer: " + e.getMessage());
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
    	RestaurantDto rest = (RestaurantDto) dto;
    	try {
    		rest.setRestaurant_id(result.getInt(1));
    		rest.setRestaurant_name(result.getString(2));
    		rest.setManagerId(result.getInt(3));
    		rest.setRestaurant_ph(result.getString(4));
    		rest.setEmail(result.getString(5));
			rest.setRestaurantOwnerId(result.getInt(6));
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
		return _restQueries.getProperty("REST_GET_ALL");
    }
    
    /**
     * getInsertQuery
     * 
     * Returns the INSERT query for this table
     * 
     * @return String - INSERT query
     */
    String getInsertQuery() {
		return _restQueries.getProperty("REST_INSERT");
    }
    
    /**
     * getDeleteQuery
     * 
     * Returns the DELETE query for this table
     * 
     * @return String - DELETE query
     */
    String getDeleteQuery() {
		return _restQueries.getProperty("REST_DELETE_ID");
    }
    
    /**
     * getUpdateQuery
     * 
     * Returns the UPDATE query for this table
     * 
     * @return String - UPDATE query
     */
    String getUpdateQuery() {
		return _restQueries.getProperty("REST_UPDATE_ID");
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
    	return new RestaurantDto();
    }
}
