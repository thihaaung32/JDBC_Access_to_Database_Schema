package hr.dao;

import java.util.List;

import hr.dao.exception.DaoException;

/**
 * CustomerDao
 * 
 * Interface for Data Access Object, CustomerDao 
 *   
 * Modifications:
 * 
 * 		04/20/2024 - jhui - Created
 * 	    11/24/2024 - thihaaung - modified
 */


public interface CustomerDao {
    CustomerDto get(Integer id) throws DaoException;
    
    CustomerDto getRow(String field, Object value) throws DaoException;
    
    List<CustomerDto> getRows(String field, Object value) throws DaoException;
    
    List<CustomerDto> getAll() throws DaoException;
    
    void save(CustomerDto t) throws DaoException;
    
    void update(CustomerDto t, String[] params) throws DaoException;
    
    void delete(CustomerDto t) throws DaoException;

}
