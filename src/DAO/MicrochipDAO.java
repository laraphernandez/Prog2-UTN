package Dao;

import Models.Microchip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO para operaciones CRUD de Microchip.
public class MicrochipDAO implements IMicrochipDAO {
    
    @Override
    public void crear(Microchip micro, Connection conn) throws SQLException {
        String sql = "INSERT INTO microchips (deleted, code, implantation_date, " +
                     "veterinary, observations) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBoolean(1, micro.getDeleted());
            stmt.setString(2, micro.getCode());
            stmt.setDate(3, micro.getImplantationDate() != null ? 
                         Date.valueOf(micro.getImplantationDate()) : null);
            stmt.setString(4, micro.getVeterinary());
            stmt.setString(5, micro.getObservations());
            
            stmt.executeUpdate();
            
            // Obtener el ID generado automáticamente
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    micro.setId(rs.getLong(1));
                }
            }
        }
    }
    
    @Override
    public Microchip leer(Long id, Connection conn) throws SQLException {
        String sql = "SELECT * FROM microchips WHERE id = ? AND deleted = FALSE";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapMicrochip(rs);
                }
            }
        }
        return null;
    }
    
    @Override
    public List<Microchip> leerTodos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM microchips WHERE deleted = FALSE";
        List<Microchip> list = new ArrayList<>();
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                list.add(mapMicrochip(rs));
            }
        }
        return list;
    }
    
    @Override
    public void actualizar(Microchip micro, Connection conn) throws SQLException {
        String sql = "UPDATE microchips SET code = ?, implantation_date = ?, " +
                     "veterinary = ?, observations = ? WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, micro.getCode());
            stmt.setDate(2, micro.getImplantationDate() != null ? 
                         Date.valueOf(micro.getImplantationDate()) : null);
            stmt.setString(3, micro.getVeterinary());
            stmt.setString(4, micro.getObservations());
            stmt.setLong(5, micro.getId());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void eliminar(Long id, Connection conn) throws SQLException {
        // Baja lógica: solo marcamos deleted = TRUE
        String sql = "UPDATE microchips SET deleted = TRUE WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
    
    @Override
    public Microchip buscarPorCodigo(String code, Connection conn) throws SQLException {
        String sql = "SELECT * FROM microchips WHERE code = ? AND deleted = FALSE";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapMicrochip(rs);
                }
            }
        }
        return null;
    }
    
    // Convierte un ResultSet en un objeto Microchip.

    private Microchip mapMicrochip(ResultSet rs) throws SQLException {
        Microchip micro = new Microchip();
        micro.setId(rs.getLong("id"));
        micro.setDeleted(rs.getBoolean("deleted"));
        micro.setCode(rs.getString("code"));
        
        Date date = rs.getDate("implantation_date");
        if (date != null) {
            micro.setImplantationDate(date.toLocalDate());
        }
        
        micro.setVeterinary(rs.getString("veterinary"));
        micro.setObservations(rs.getString("observations"));
        
        return micro;
    }
}