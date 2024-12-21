package hr.dao;

import java.util.List;

import hr.dao.exception.DaoException;

public interface RestaurantDao {

	/**
	 * RestaurantDao
	 *
	 * Interface for Data Access Object, RestaurantDao
	 *
	 * Modifications:
	 *
	 * 		04/20/2024 - jhui - Created
	 * 	    11/24/2024 - thihaaung - modified
	 */

    RestaurantDto get(Integer id) throws DaoException;

    RestaurantDto getRow(String field, Object value) throws DaoException;
    
    List<RestaurantDto> getRows(String field, Object value) throws DaoException;
    
    List<RestaurantDto> getAll() throws DaoException;
    
    void save(RestaurantDto t) throws DaoException;
    
    void update(RestaurantDto t, String[] params) throws DaoException;
    
    void delete(RestaurantDto t) throws DaoException;

}
