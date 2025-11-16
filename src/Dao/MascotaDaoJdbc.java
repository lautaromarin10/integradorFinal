/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// dao/MascotaDaoJdbc.java

package Dao;

import Config.DatabaseConnection;
import Entities.Mascota;
import Entities.Microchip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Implementa la interfaz MascotaDao
public class MascotaDaoJdbc implements MascotaDao {

    // Necesitamos el DAO de Microchip para resolver la relación 1-1.
    // Usamos la implementación concreta para instanciarlo.
    private final MicrochipDaoJdbc microchipDao = new MicrochipDaoJdbc();
    
    // -------------------------------------------------------------
    // --- CREATE (Métodos que llaman a la sobrecarga transaccional)
    // -------------------------------------------------------------

    @Override
    public Mascota crear(Mascota mascota) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // El DAO solo usa la conexión, la gestión transaccional la hace el Service.
            return crear(mascota, conn); 
        } catch (SQLException e) {
            throw new Exception("Error al crear Mascota (sin transacción): " + e.getMessage(), e);
        }
    }
    
    @Override
    public Mascota crear(Mascota mascota, Connection conn) throws Exception {
       
        Microchip microchip = mascota.getMicrochip();

        if(microchip == null){
            throw new IllegalArgumentException("La mascota debe. tener un Microchip");
        }

        // Si el microchip no tiene id, lo creamos en la misma transacción
        if (microchip.getId() <= 0) {
            microchipDao.crear(microchip, conn); // microchipDao.crear(Microchip, Connection) debe asignar el id en el objeto
        }

        if (microchip.getId() <= 0) {
            throw new SQLException("No se pudo obtener el ID del Microchip para la Mascota.");
        }

        String sql = "INSERT INTO mascota (nombre, especie, raza, fechaNacimiento, duenio, telefonoDuenio, eliminado, microchip) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setString(3, mascota.getRaza());
            ps.setDate(4, Date.valueOf(mascota.getFechaNacimiento())); // Convertir LocalDate a java.sql.Date
            ps.setString(5, mascota.getDuenio());
            ps.setString(6, mascota.getTelefonoDuenio());
            ps.setBoolean(7, mascota.estaEliminado());
            ps.setLong(8, mascota.getMicrochip().getId());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("La creación de la Mascota falló.");
            }
            
            // Obtener el ID generado y asignarlo a la entidad
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    mascota.setId(rs.getLong(1));
                }
            }
            return mascota;
        } 
        // Las excepciones se propagan para que el Service maneje el rollback.
    }

    // -------------------------------------------------------------
    // --- READ
    // -------------------------------------------------------------

    @Override
    public Mascota leer(long id) throws Exception {
        String sql = "SELECT * FROM mascota WHERE id = ? AND eliminado = FALSE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return buildMascotaFromResultSet(rs);
                }
                return null;
            }
        }
    }

    @Override
    public List<Mascota> leerTodos() throws Exception {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascota WHERE eliminado = FALSE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(buildMascotaFromResultSet(rs));
            }
            return lista;
        }
    }
    
    // Implementación de la búsqueda específica
    @Override
    public List<Mascota> buscarPorDuenio(String duenio) throws Exception {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascota WHERE duenio LIKE ? AND eliminado = FALSE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + duenio + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(buildMascotaFromResultSet(rs));
                }
                return lista;
            }
        }
    }

    // -------------------------------------------------------------
    // --- UPDATE y DELETE (Usan la sobrecarga transaccional)
    // -------------------------------------------------------------
    
    // Implementar los cuerpos para actualizar(Mascota) y eliminar(long id) 
    // que llaman a sus versiones sobrecargadas con una nueva conexión.
    
    // Ejemplo de actualización transaccional:
    @Override
    public void actualizar(Mascota mascota, Connection conn) throws Exception {
        String sql = "UPDATE mascota SET nombre = ?, especie = ?, raza = ?, fechaNacimiento = ?, duenio = ?, telefonoDuenio = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getEspecie());
            ps.setString(3, mascota.getRaza());
            ps.setDate(4, Date.valueOf(mascota.getFechaNacimiento()));
            ps.setString(5, mascota.getDuenio());
            ps.setString(6, mascota.getTelefonoDuenio());
            ps.setLong(7, mascota.getId());
            ps.executeUpdate();
        }
    }
    
    // Ejemplo de eliminación lógica transaccional:
    @Override
    public void eliminar(long id, Connection conn) throws Exception {
        String sql = "UPDATE mascota SET eliminado = 1 WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }
    
    // -------------------------------------------------------------
    // --- Helper (Mapeo de ResultSet a Objeto)
    // -------------------------------------------------------------
    
    private Mascota buildMascotaFromResultSet(ResultSet rs) throws Exception {
        Mascota m = new Mascota();
        m.setId(rs.getLong("id"));
        m.setEliminado(rs.getBoolean("eliminado")); // Usa el setter de Base.java
        m.setNombre(rs.getString("nombre"));
        m.setEspecie(rs.getString("especie"));
        m.setRaza(rs.getString("raza"));
        
        Date sqlDate = rs.getDate("fechaNacimiento");
        if(sqlDate != null){
            m.setFechaNacimiento(sqlDate.toLocalDate());
        }
        m.setDuenio(rs.getString("duenio"));
        m.setTelefonoDuenio(rs.getString("telefonoDuenio"));
        
        // RECUPERAR EL MICROCHIP ASOCIADO (LA REFERENCIA 1->1)
        Microchip microchip = microchipDao.leerPorIdMascota(m.getId()); 
        m.setMicrochip(microchip); // Establecer la referencia 1-1
        
        return m;
    }

    @Override
    public void actualizar(Mascota mascota) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            actualizar(mascota, conn); // delega en la versión transaccional
        }
    }

    @Override
    public void eliminar(long id) throws Exception {
        try (Connection conn = DatabaseConnection.getConnection()) {
            eliminar(id, conn); // delega en la versión transaccional
        }
}
}