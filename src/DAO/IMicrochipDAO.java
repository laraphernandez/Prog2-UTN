package Dao;

import Models.Microchip;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interfaz DAO específica para operaciones con Microchips.
 * Extiende las operaciones CRUD genéricas y agrega búsqueda por código.
 */
public interface IMicrochipDAO extends GenericDAO<Microchip> {
    
    // Busca un microchip por su código único
    Microchip buscarPorCodigo(String codigo, Connection conn) throws SQLException;
}