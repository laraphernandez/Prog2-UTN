package Service;

import Config.DatabaseConnection;
import Config.TransactionManager;
import Dao.MicrochipDAO;
import Models.Microchip;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para lógica de negocio de Microchip:
 * Valida que el código del microchip sea único
 * Maneja transacciones con commit/rollback
 * Encapsula la lógica de negocio (los DAOs solo hacen SQL)
 */
public class MicrochipServiceImpl implements GenericService<Microchip> {
    
    private final MicrochipDAO dao = new MicrochipDAO();
    
    @Override
    public void insertar(Microchip micro) throws SQLException {
        // Validación: el código es obligatorio
        if (micro.getCode() == null || micro.getCode().trim().isEmpty()) {
            throw new SQLException("El código del microchip es obligatorio");
        }
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            TransactionManager.beginTransaction(conn);
            
            // Validar que el código sea único
            Microchip existe = dao.buscarPorCodigo(micro.getCode(), conn);
            if (existe != null) {
                throw new SQLException("Ya existe un microchip con código: " + micro.getCode());
            }
            
            dao.crear(micro, conn);
            TransactionManager.commit(conn);
            
        } catch (SQLException e) {
            TransactionManager.rollback(conn);
            throw e;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    @Override
    public Microchip getById(Long id) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            return dao.leer(id, conn);
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    @Override
    public List<Microchip> getAll() throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            return dao.leerTodos(conn);
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    @Override
    public void actualizar(Microchip micro) throws SQLException {
        if (micro.getId() == null) {
            throw new SQLException("ID del microchip no puede ser nulo");
        }
        
        if (micro.getCode() == null || micro.getCode().trim().isEmpty()) {
            throw new SQLException("El código del microchip es obligatorio");
        }
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            TransactionManager.beginTransaction(conn);
            
            dao.actualizar(micro, conn);
            TransactionManager.commit(conn);
            
        } catch (SQLException e) {
            TransactionManager.rollback(conn);
            throw e;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    @Override
    public void eliminar(Long id) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            TransactionManager.beginTransaction(conn);
            
            dao.eliminar(id, conn);
            TransactionManager.commit(conn);
            
        } catch (SQLException e) {
            TransactionManager.rollback(conn);
            throw e;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    // Busca un microchip por su código.
    public Microchip buscarPorCodigo(String code) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            return dao.buscarPorCodigo(code, conn);
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
}