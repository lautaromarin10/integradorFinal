package Services;
import Config.DatabaseConnection;
import Dao.MascotaDao;
import Dao.MascotaDaoJdbc;
import Dao.MicrochipDao;
import Dao.MicrochipDaoJdbc;
import Entities.Mascota;
import Entities.Microchip;
import java.sql.Connection;
import java.util.List;


public class MascotaService implements GenericService<Mascota, Long>{
    
    private final MascotaDao mascotaDao = new MascotaDaoJdbc();
    
    @Override
    public void insertar(Mascota mascota) throws Exception {
        
        if(mascota == null){
            return;
        }
        
        try (Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false);
            try {

                Microchip microchip = mascota.getMicrochip();

                if(microchip == null){
                    throw new Exception("La mascota debe tener un Microchip");
                }

                MicrochipDao microchipDao = new MicrochipDaoJdbc();

                if(microchip.getId() <= 0){
                    microchipDao.crear(microchip, conn);
                }

                microchipDao.crear(mascota.getMicrochip(), conn);
                mascotaDao.crear(mascota, conn);
                conn.commit();

            } catch (Exception e) {
                conn.rollback();
                throw new Exception(e);
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
        
    }
    
    @Override
    public void actualizar(Mascota mascota) throws Exception{
       try (Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false);
            try {
                mascotaDao.actualizar(mascota, conn);
                conn.commit();


            } catch (Exception e) {
                conn.rollback();
                throw new Exception(e);
            }
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage(), e);
        }
    }
    
    public void eliminar(long id) throws Exception{
        
        try(Connection conn = DatabaseConnection.getConnection()){
            conn.setAutoCommit(false);

            try {
                Mascota mascota = mascotaDao.leer(id);

                if(mascota == null){
                    throw new Exception("Mascota no encontrada");
                }
                
                mascotaDao.eliminar(id, conn);
                
                conn.commit();

            } catch (Exception e) {
                conn.rollback();
                throw new Exception(e);
            }

        }
        
    }
    
    @Override
    public Mascota getById(long id) throws Exception {
        
        try{
            return mascotaDao.leer(id);
        } catch (Exception e) {
            throw new Exception("Error al obtener mascota por ID: " + id, e);
        }
        
    }

    public List<Mascota> getAll() throws Exception {
        
            
        try{
            return mascotaDao.leerTodos();
        } catch (Exception e) {
            throw new Exception("Error al obtener las listas de mascotas", e );
        }
        
        }
    
    }
