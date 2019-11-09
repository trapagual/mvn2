package es.velsoft.mvn2.dao;

import es.velsoft.mvn2.modelo.IdNombreObj;
import es.velsoft.mvn2.modelo.TablaAux;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * DAO para la lista de tablas auxiliares
 *
 * @author Alejandro
 */
public class TablasAuxiliaresDAO extends GenericDAO {

    private EntityManager sesion;
    private Session miSesion;
    private Transaction tx;
    private static final Logger LOG = Logger.getLogger(TablasAuxiliaresDAO.class.getName());

    private static final String SQL_GET_ALL = "SELECT Id, descripcion FROM ";
    private static final String SQL_INSERT1 = "INSERT INTO ";
    private static final String SQL_INSERT2 = " (DESCRIPCION) VALUES (?)";
    private static final String SQL_UPDATE1 = "UPDATE ";
    private static final String SQL_UPDATE2 = " SET DESCRIPCION=? WHERE ID=?";

    public List<TablaAux> getAllDatos(String tabla) {
        List<TablaAux> datos = new ArrayList<>();
        Query q;
        try {

            sesion = obtenerSessionHibernate();
            q = sesion.createNativeQuery(SQL_GET_ALL + tabla + " ORDER BY descripcion");
            List<Object[]> resultados = q.getResultList();
            for (Object[] filas : resultados) {
                TablaAux t = new TablaAux();
                t.setId(Integer.valueOf(filas[0].toString()));
                t.setDescripcion(filas[1].toString());
                datos.add(t);
            }
        } catch (Exception ex) {
            Logger.getLogger(TablasAuxiliaresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return datos;
    }

    public boolean save(List<IdNombreObj> modificados, String tabla, List<TablaAux> actuales) {
        LOG.info("Estoy en el DAO, en save()");
        boolean resultado = false;

        try {
            // esto es en realidad un EntityManager
            sesion = obtenerSessionHibernate();
            // tengo que extraer una sesion para poder iniciar una transaccion
            miSesion = sesion.unwrap(Session.class);
            // y ahora la transaccion
            tx = miSesion.beginTransaction();
            // aquí empieza la funcion
            for (IdNombreObj o : modificados) {
                switch (o.getNombre()) {
                    case "UPDATE":
                        actualiza(o.getId(), tabla, actuales);
                        break;
                    case "DELETE":
                        elimina(o.getId(), tabla, actuales);
                        break;
                    default:
                        LOG.log(Level.SEVERE, "No se encuentra {0}", o.getNombre());
                }

            }
            
            // poner a cero los modificados para la proxima vuelta
            modificados = new ArrayList<>();
            
            // finalizar transaccion
            tx.commit();
            resultado = true;
        } catch (RuntimeException rex) {
            try {
                if (tx != null) {
                    tx.rollback();
                LOG.log(Level.WARNING, "Se hace rollback de la transaccion, debido a: {0}", rex.getLocalizedMessage());
                }
            } catch (HibernateException he) {
                LOG.log(Level.SEVERE, "Error al hacer rollback: {0}", he.getLocalizedMessage());
            }

        } catch (Exception ex) {
            Logger.getLogger(TablasAuxiliaresDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            miSesion.close();
            sesion.close();
        }

        return resultado;
    }



    /**
     * Hace un UPDATE si el id es mayor que cero (registro que ya existia, actualizacion)
     * Y un INSERT si el id es menor que cero (registro nuevo, insertar)
     * Los INSERT entran por este metodo porque siempre que se inserta un registro se levanta
     * también el evento UPDATE, cuando se cambia algún valor de los campos.
     * @param id    el identificador del registro de actuales que vamos a modificar
     * @param tabla el nombre de la tabla auxiliar sobre la que actuamos
     * @param actuales la lista de objetos modificados durante la sesion
     */
    private void actualiza(Integer id, String tabla, List<TablaAux> actuales) {
       TablaAux ta = null;
        for (TablaAux t : actuales) {
            if (Objects.equals(t.getId(), id)) {
                ta = t;
                break;
            }
        }
        if (ta == null) {
            LOG.log(Level.SEVERE, "No se encuentra {0}", id);
            throw new RuntimeException("No se encuentra objeto en actuales con el id adecuado");
        } else {
            LOG.log(Level.INFO, "Voy a insertar {0}", ta.toString());
        }
        // aqui ya tengo en ta el objeto a insertar o actualizar.
        // tendré que insertarlo si el id es negativo y actualizarlo si no.
        if (id < 0) { // es una inserción
            miSesion.createNativeQuery(SQL_INSERT1 + tabla + SQL_INSERT2)
                    .setParameter(1, ta.getDescripcion())
                    .executeUpdate();
            // recuperar el id insertado.
            
                    
            LOG.log(Level.INFO, "Insertado {0}", ta.toString());
        } else { // es un update
            miSesion.createNativeQuery(SQL_UPDATE1 + tabla + SQL_UPDATE2)
                    .setParameter(1, ta.getDescripcion())
                    .setParameter(2, ta.getId())
                    .executeUpdate();
            LOG.log(Level.INFO, "Actualizado {0}", ta.toString());           
        }
    }

    private void elimina(Integer id, String tabla, List<TablaAux> actuales) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
