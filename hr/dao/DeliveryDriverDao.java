package hr.dao;

import java.util.List;

import hr.dao.exception.DaoException;

/**
 * DeliveryDriverDao
 *
 * Interface for Data Access Object, DeliveryDriverDao 
 *
 * Modifications:
 *
 * 		04/20/2024 - jhui - Created
 * 	    11/24/2024 - thihaaung - modified
 */


public interface DeliveryDriverDao {
    DeliveryDriverDto get(Integer id) throws DaoException;

    DeliveryDriverDto getRow(String field, Object value) throws DaoException;

    List<DeliveryDriverDto> getRows(String field, Object value) throws DaoException;

    List<DeliveryDriverDto> getAll() throws DaoException;

    void save(DeliveryDriverDto t) throws DaoException;

    void update(DeliveryDriverDto t, String[] params) throws DaoException;

    void delete(DeliveryDriverDto t) throws DaoException;

}
