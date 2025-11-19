package Dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD en la base de datos.
 * @param <T> tipo de entidad que maneja el DAO
 */
public interface GenericDAO<T> {
    
    // Inserta una nueva entidad en la base de datos
    void crear(T entidad, Connection conn) throws SQLException;
    
    // Obtiene una entidad por su ID
    T leer(Long id, Connection conn) throws SQLException;
    
    // Obtiene todas las entidades de la tabla
    List<T> leerTodos(Connection conn) throws SQLException;
    
    // Actualiza una entidad existente
    void actualizar(T entidad, Connection conn) throws SQLException;
    
    // Elimina una entidad por su ID
    void eliminar(Long id, Connection conn) throws SQLException;
}