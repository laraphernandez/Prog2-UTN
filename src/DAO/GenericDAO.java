package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD.
 * Cada método recibe una Connection externa para participar en transacciones
 */
public interface GenericDAO<T> {
    
    // Crea una nueva entidad en la BD.

    void crear(T entity, Connection conn) throws SQLException;
    
    // Lee una entidad por su ID.
    T leer(Long id, Connection conn) throws SQLException;
    
    // Lee todas las entidades no eliminadas.
    List<T> leerTodos(Connection conn) throws SQLException;
    
    // Actualiza una entidad existente
    void actualizar(T entity, Connection conn) throws SQLException;
    
    // Elimina lógicamente una entidad (marca deleted = true).
    void eliminar(Long id, Connection conn) throws SQLException;
}