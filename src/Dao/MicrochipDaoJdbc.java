/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// dao/MicrochipDaoJdbc.java

package Dao;

import Config.DatabaseConnection;
import Entities.Microchip;
import java.sql.*;
import java.util.List;
import java.time.LocalDate;

// Implementa la interfaz MicrochipDao
public class MicrochipDaoJdbc implements MicrochipDao {
    
    // -------------------------------------------------------------
    // --- CREATE (Métodos que llaman a la sobrecarga transaccional)
    // -------------------------------------------------------------
    
    @Override
    public Microchip crear(Microchip microchip) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            return crear(microchip, conn);
        } catch (SQLException e) {
            throw new Exception("Error al crear Microchip (sin transacción): " + e.getMessage(), e);
        }
    }
    
    @Override
    public Microchip crear(Microchip microchip, Connection conn) throws Exception {
        // o se incluye aquí si el Service ya tiene el ID de Mascota.
        // Para que la inserción simple funcione, la dejamos fuera por ahora.
        String sql = "INSERT INTO microchip (codigo, fechaImplantacion, veterinaria, observaciones, eliminado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, microchip.getCodigo());
            ps.setDate(2, Date.valueOf(microchip.getFechaImplantacion()));
            ps.setString(3, microchip.getVeterinaria());
            ps.setString(4, microchip.getObservaciones());
            ps.setBoolean(5, microchip.estaEliminado());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La creación del Microchip falló.");
            }
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    microchip.setId(rs.getLong(1));
                }
            }
            return microchip;
        }
    }
    
    // -------------------------------------------------------------
    // --- READ Específico (Necesario para MascotaDao)
    // -------------------------------------------------------------
    
    // Implementación de la búsqueda específica por FK a Mascota
    @Override
    public Microchip leerPorIdMascota(long idMascota) throws Exception {
        // ¡Crucial para la relación 1-1!
        String sql = "SELECT * FROM microchip JOIN mascota WHERE mascota.id = ? AND microchip.eliminado = FALSE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, idMascota);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return buildMicrochipFromResultSet(rs);
                }
                return null;
            }
        }
    }
    
    // Implementación de la búsqueda específica por código UNIQUE
    @Override
    public Microchip leerPorCodigo(String codigo) throws Exception {
        String sql = "SELECT * FROM microchip WHERE codigo = ? AND eliminado = FALSE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return buildMicrochipFromResultSet(rs);
                }
                return null;
            }
        }
    }
    
    // -------------------------------------------------------------
    // --- Helper (Mapeo de ResultSet a Objeto)
    // -------------------------------------------------------------

    private Microchip buildMicrochipFromResultSet(ResultSet rs) throws SQLException {
        Microchip m = new Microchip();
        m.setId(rs.getLong("id"));
        m.setEliminado(rs.getBoolean("eliminado"));
        m.setCodigo(rs.getString("codigo"));
        
        Date sqlDate = rs.getDate("fechaImplantacion");
        if (sqlDate != null) {
            m.setFechaImplantacion(sqlDate.toLocalDate());
        }
        m.setVeterinaria(rs.getString("veterinaria"));
        m.setObservaciones(rs.getString("observaciones"));
        return m;
    }
    
    // [ ... Implementar leer(id), leerTodos, actualizar y eliminar de forma similar ... ]
    
    // Ejemplo de eliminación lógica transaccional:
    @Override
    public void eliminar(long id, Connection conn) throws Exception {
        String sql = "UPDATE microchip SET eliminado = TRUE WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
    
    // Implementar los cuerpos restantes de GenericDao<T>...
    // Nota: El método actualizar(Microchip, Connection) requerirá 
    // la cláusula WHERE id = ? en el UPDATE.

    @Override
    public Microchip leer(long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Microchip> leerTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Microchip entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(Microchip entity, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}