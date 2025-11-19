package Dao;

import Models.Pet;
import Models.Microchip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para operaciones CRUD de Pet.
 *El método mapPet() carga automáticamente el microchip asociado
 */
public class PetDAO implements GenericDAO<Pet> {
    
    private final MicrochipDAO microchipDAO = new MicrochipDAO();
    
    @Override
    public void crear(Pet pet, Connection conn) throws SQLException {
        String sql = "INSERT INTO pets (deleted, name, species, breed, " +
                     "birth_date, owner, microchip_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBoolean(1, pet.getDeleted());
            stmt.setString(2, pet.getName());
            stmt.setString(3, pet.getSpecies());
            stmt.setString(4, pet.getBreed());
            stmt.setDate(5, pet.getBirthDate() != null ? 
                         Date.valueOf(pet.getBirthDate()) : null);
            stmt.setString(6, pet.getOwner());
            
            // microchip_id puede ser null
            if (pet.getMicrochip() != null && pet.getMicrochip().getId() != null) {
                stmt.setLong(7, pet.getMicrochip().getId());
            } else {
                stmt.setNull(7, Types.BIGINT);
            }
            
            stmt.executeUpdate();
            
            // Obtener el ID generado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pet.setId(rs.getLong(1));
                }
            }
        }
    }
    
    @Override
    public Pet leer(Long id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM pets WHERE id = ? AND deleted = FALSE";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapPet(rs, conn);
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Pet> leerTodos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM pets WHERE deleted = FALSE";
        List<Pet> list = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapPet(rs, conn));
            }
        }
        return list;
    }
    
    @Override
    public void actualizar(Pet pet, Connection conn) throws SQLException {
        String sql = "UPDATE pets SET name = ?, species = ?, breed = ?, " +
                     "birth_date = ?, owner = ?, microchip_id = ? WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getSpecies());
            stmt.setString(3, pet.getBreed());
            stmt.setDate(4, pet.getBirthDate() != null ? 
                         Date.valueOf(pet.getBirthDate()) : null);
            stmt.setString(5, pet.getOwner());
            
            // microchip_id puede ser null
            if (pet.getMicrochip() != null && pet.getMicrochip().getId() != null) {
                stmt.setLong(6, pet.getMicrochip().getId());
            } else {
                stmt.setNull(6, Types.BIGINT);
            }
            
            stmt.setLong(7, pet.getId());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void eliminar(Long id, Connection conn) throws SQLException {
        // Baja lógica
        String sql = "UPDATE pets SET deleted = TRUE WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
    
    /**
     * Busca mascotas por nombre del dueño (búsqueda parcial).
     */
    public List<Pet> buscarPorDuenio(String owner, Connection conn) throws SQLException {
        String sql = "SELECT * FROM pets WHERE owner LIKE ? AND deleted = FALSE";
        List<Pet> list = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + owner + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapPet(rs, conn));
                }
            }
        }
        return list;
    }
    
    /**
     * Convierte un ResultSet en un objeto Pet.
     * Carga automáticamente el microchip asociado si existe.
     */
    private Pet mapPet(ResultSet rs, Connection conn) throws SQLException {
        Pet pet = new Pet();
        pet.setId(rs.getLong("id"));
        pet.setDeleted(rs.getBoolean("deleted"));
        pet.setName(rs.getString("name"));
        pet.setSpecies(rs.getString("species"));
        pet.setBreed(rs.getString("breed"));
        
        Date date = rs.getDate("birth_date");
        if (date != null) {
            pet.setBirthDate(date.toLocalDate());
        }
        
        pet.setOwner(rs.getString("owner"));
        
        // Cargar el microchip asociado si existe
        Long microchipId = rs.getObject("microchip_id", Long.class);
        if (microchipId != null) {
            Microchip micro = microchipDAO.leer(microchipId, conn);
            pet.setMicrochip(micro);
        }
        
        return pet;
    }
}