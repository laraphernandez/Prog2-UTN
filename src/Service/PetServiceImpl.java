package Service;

import Config.DatabaseConnection;
import Config.TransactionManager;
import Dao.PetDAO;
import Dao.MicrochipDAO;
import Models.Pet;
import Models.Microchip;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para lógica de negocio de Pet:
 * Valida que los campos obligatorios estén completos
 * Maneja la asignación de microchips a mascotas
 * Asegura que un microchip solo se asigne a una mascota
 */
public class PetServiceImpl implements GenericService<Pet> {
    
    private final PetDAO dao = new PetDAO();
    private final MicrochipDAO microDAO = new MicrochipDAO();
    
    @Override
    public void insertar(Pet pet) throws SQLException {
        // Validaciones de campos obligatorios
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new SQLException("El nombre de la mascota es obligatorio");
        }
        if (pet.getSpecies() == null || pet.getSpecies().trim().isEmpty()) {
            throw new SQLException("La especie es obligatoria");
        }
        if (pet.getOwner() == null || pet.getOwner().trim().isEmpty()) {
            throw new SQLException("El dueño es obligatorio");
        }
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            TransactionManager.beginTransaction(conn);
            
            dao.crear(pet, conn);
            TransactionManager.commit(conn);
            
        } catch (SQLException e) {
            TransactionManager.rollback(conn);
            throw e;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    @Override
    public Pet getById(Long id) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            return dao.leer(id, conn);
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    @Override
    public List<Pet> getAll() throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            return dao.leerTodos(conn);
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    @Override
    public void actualizar(Pet pet) throws SQLException {
        if (pet.getId() == null) {
            throw new SQLException("ID de la mascota no puede ser nulo");
        }
        
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new SQLException("El nombre de la mascota es obligatorio");
        }
        if (pet.getSpecies() == null || pet.getSpecies().trim().isEmpty()) {
            throw new SQLException("La especie es obligatoria");
        }
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            TransactionManager.beginTransaction(conn);
            
            dao.actualizar(pet, conn);
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
    
    //Busca mascotas por nombre del dueño.
    public List<Pet> buscarPorDuenio(String owner) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            return dao.buscarPorDuenio(owner, conn);
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    /**
     * Asigna un microchip existente a una mascota:
     * Esta operación usa transacción porque modifica dos registros
     * Valida que el microchip no esté ya asignado a otra mascota
     * Si algo falla, hace rollback para mantener consistencia
     */
    public void asignarMicrochip(Long petId, Long microchipId) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            TransactionManager.beginTransaction(conn);
            
            // Verificar que la mascota existe
            Pet pet = dao.leer(petId, conn);
            if (pet == null) {
                throw new SQLException("Mascota no encontrada con ID: " + petId);
            }
            
            // Verificar que el microchip existe
            Microchip micro = microDAO.leer(microchipId, conn);
            if (micro == null) {
                throw new SQLException("Microchip no encontrado con ID: " + microchipId);
            }
            
            // Verificar que la mascota no tenga ya un microchip
            if (pet.getMicrochip() != null) {
                throw new SQLException("La mascota ya tiene un microchip asignado");
            }
            
            // Asignar el microchip a la mascota
            pet.setMicrochip(micro);
            dao.actualizar(pet, conn);
            
            TransactionManager.commit(conn);
            
        } catch (SQLException e) {
            TransactionManager.rollback(conn);
            throw e;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }
}