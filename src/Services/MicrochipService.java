/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Dao.MicrochipDao; // Necesario para acceder al DAO
import Entities.Microchip; // Necesario para la entidad
import java.time.LocalDate;
import java.util.List;

/**
 * Implementación del servicio de negocio para la entidad Microchip.
 * Es la capa de servicio simple, enfocada en la validación de Microchip
 * y delegación al DAO, sin coordinación transaccional.
 */
public class MicrochipService implements GenericService<Microchip, Long> {
    
    // Inyección de dependencia
    private final MicrochipDao microchipDao; 

    /**
     * Constructor con inyección de dependencia del DAO.
     */
    public MicrochipService(MicrochipDao microchipDao) {
        if (microchipDao == null) {
            throw new IllegalArgumentException("MicrochipDAO no puede ser null.");
        }
        this.microchipDao = microchipDao;
    }

    // --- MÉTODOS CRUD IMPLEMENTADOS ---
    
    @Override
    public void insertar(Microchip microchip) throws Exception {
        if (microchip == null) {
            throw new IllegalArgumentException("El microchip a insertar no puede ser null.");
        }
        validateMicrochip(microchip); // RN-M02: Aplicar validaciones
        
        try {
            microchipDao.crear(microchip); // Llama al DAO simple
        } catch (Exception e) {
            throw new Exception("Error al insertar Microchip: " + e.getMessage(), e);
        }
    }

    @Override
public void actualizar(Microchip microchip) throws Exception {
    
    // 1. Verificación de nulidad del objeto
    if (microchip == null) {
        throw new IllegalArgumentException("El microchip a actualizar no puede ser null.");
    }
    
    validateMicrochip(microchip); 

    // 2. Verificación de ID:
    if (microchip.getId() == null || (microchip.getId().longValue() <= 0)) {
        throw new IllegalArgumentException("El ID del microchip debe ser > 0 para actualizar");
    }
    
    try {
        microchipDao.actualizar(microchip);
    } catch (Exception e) {
        throw new Exception("Error al actualizar Microchip: " + e.getMessage(), e);
    }
}

    @Override
public void eliminar(Long id) throws Exception {
    if (id == null || id <= 0) {
        throw new IllegalArgumentException("El ID debe ser mayor a 0");
    }
    try {
        // Usa el 'id' recibido y conviértelo a long
        microchipDao.eliminar(id.longValue()); 
    } catch (Exception e) {
        throw new Exception("Error al eliminar Microchip con ID " + id + ": " + e.getMessage(), e);
    }
}

    @Override
    public Microchip getById(Long id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor a 0");
        }
        try {
            return microchipDao.leer(id.longValue()); 
        } catch (Exception e) {
            throw new Exception("Error al obtener Microchip por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Microchip> getAll() throws Exception {
        try {
            return microchipDao.leerTodos(); 
        } catch (Exception e) {
            throw new Exception("Error al obtener la lista de Microchips: " + e.getMessage(), e);
        }
    }
    
    // --- LÓGICA DE NEGOCIO Y VALIDACIÓN ---
    
    private void validateMicrochip(Microchip microchip) {
        // Nota: La validación de nulidad del objeto 'microchip' se hace ahora en los métodos CRUD.
        if (microchip.getCodigo() == null || microchip.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío ");
        }
        if (microchip.getFechaImplantacion() == null) {
            throw new IllegalArgumentException("La fecha de implantación no puede ser nula ");
        }
        if (microchip.getVeterinaria() == null || microchip.getVeterinaria().trim().isEmpty()) {
            throw new IllegalArgumentException("La veterinaria no puede estar vacía ");
        }
        if (microchip.getFechaImplantacion().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de implantación no puede ser futura");
        }
    }
}
