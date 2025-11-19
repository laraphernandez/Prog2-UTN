package Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica para la capa de servicios.
 * La capa Service contiene la lógica de negocio
 * Encargado de las validaciones y orquesta las transacciones
 */
public interface GenericService<T> {
    
    // Inserta una nueva entidad con validaciones y transacción.
  
    void insertar(T entity) throws SQLException;
    
    // Obtiene una entidad por ID.
    T getById(Long id) throws SQLException;
    
    // Obtiene todas las entidades no eliminadas.
    List<T> getAll() throws SQLException;
    
    // Actualiza una entidad con validaciones.
    void actualizar(T entity) throws SQLException;
    
    // Elimina lógicamente una entidad.
    void eliminar(Long id) throws SQLException;
}