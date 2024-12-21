package hr.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import hr.dao.BaseDto;
import hr.dao.CustomerDao;
import hr.dao.CustomerDto;
import hr.dao.exception.DaoException;

import static util.jdbc.JdbcConnection.getConnection;

/**
 * CustomerDaoImpl
 * 
 * Implementation for CustomerDao (Data Access Object) 
 *   
 * Modifications:
 * 
 * 		04/20/2024 - jhui - Created
 * 		11/24/2024 - thihaaung - modified
 */


public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao {
	String _tableName = "customer";
	String _primaryKey = "customer_id";
	Properties _cusQueries = null;
	
	public CustomerDaoImpl() {
		super();
		
		_cusQueries = new Properties();
		try {
			_cusQueries.load(this.getClass().getClassLoader().getResourceAsStream("hr/sql/sql.properties"));
			//String query = _cusQueries.getProperty("CUS_UPDATE_ID");
			//System.out.println("\nUpdate query: \n" + query);
		}
		catch (IOException io) {
			System.out.println("Exception during sql.properties load: " + io);
		}
	}
	
	public CustomerDto get(Integer id) throws DaoException {
		return (CustomerDto) super.get(id);
	}
	
	public CustomerDto getRow(String field, Object value) throws DaoException {
		return (CustomerDto) super.getRow(field, value);
	}
	
	
	/**
	 * save
	 * 
	 * Convert the DTO into a SQL row and INSERT into the table
	 *   
	 * @param CustomerDto t - DTO that contains the values for the new row  
	 */
	public void save(CustomerDto t) throws DaoException {
		String query = getInsertQuery();
		try (var connection = getConnection();
			 var preparedStatement = connection.prepareStatement(query)) {

			// Assuming CustomerDto has appropriate getter methods
			preparedStatement.setInt(1, t.getCustomerId());
			preparedStatement.setString(2, t.getLastName());
			preparedStatement.setString(3, t.getFirstName());
			preparedStatement.setString(4, t.getCustomer_ph());
			preparedStatement.setString(5, t.getCustomer_email());

			preparedStatement.executeUpdate();
			System.out.println("Customer saved successfully.");
		} catch (SQLException e) {
			throw new DaoException("Error while saving customer: " + e.getMessage());
		}
	}

	/**
	 * update
	 * 
	 * Update the corresponding row in the database for the DTO with the 
	 * values in params
	 *   
	 * @param CustomerDto t - pull the primary key out of t
	 * @param String[] params - values to update the row 
	 * 	 
	 */
	public void update(CustomerDto t, String[] params) throws DaoException {
		String query = getUpdateQuery();
		try (var connection = getConnection();
			 var preparedStatement = connection.prepareStatement(query)) {

			for (int i = 0; i < params.length; i++) {
				preparedStatement.setString(i + 1, params[i]);
			}

			preparedStatement.setInt(params.length + 1, t.getCustomerId());

			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Customer updated successfully.");
			} else {
				System.out.println("No customer found with the given ID.");
			}
		} catch (SQLException e) {
			throw new DaoException("Error while updating customer: " + e.getMessage());
		}
	}
    
	/**
	 * delete
	 * 
	 * Delete the corresponding row in the database for the DTO
	 *   
	 * @param CustomerDto t - pull the primary key out of t
	 * 	 
	 */
	public void delete(CustomerDto t) throws DaoException {
		String query = getDeleteQuery();
		try (var connection = getConnection();
			 var preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, t.getCustomerId());

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
    	CustomerDto cusl = (CustomerDto) dto;
    	try {
    		cusl.setCustomerId(result.getInt(1));
    		cusl.setLastName(result.getString(2));
    		cusl.setFirstName(result.getString(3));
    		cusl.setCustomer_ph(result.getString(4));
			cusl.setCustomer_email(result.getString(5));
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
		return _cusQueries.getProperty("CUS_GET_ALL");
    }
    
    /**
     * getInsertQuery
     * 
     * Returns the INSERT query for this table
     * 
     * @return String - INSERT query
     */
    String getInsertQuery() {
		return _cusQueries.getProperty("CUS_INSERT");
    }
    
    /**
     * getDeleteQuery
     * 
     * Returns the DELETE query for this table
     * 
     * @return String - DELETE query
     */
    String getDeleteQuery() {
		return _cusQueries.getProperty("CUS_DELETE_ID");
    }
    
    /**
     * getUpdateQuery
     * 
     * Returns the UPDATE query for this table
     * 
     * @return String - UPDATE query
     */
    String getUpdateQuery() {
		return _cusQueries.getProperty("CUS_UPDATE_ID");
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
    	return new CustomerDto();
    }
}
