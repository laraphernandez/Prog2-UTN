package Dao;

import Models.Microchip;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interfaz específica para operaciones de Microchip.
 * Extiende GenericDAO y agrega métodos propios.
 */
public interface IMicrochipDAO extends GenericDAO<Microchip> {
    
    // Busca un microchip por su código único.
    Microchip buscarPorCodigo(String code, Connection conn) throws SQLException;
}