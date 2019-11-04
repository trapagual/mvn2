package es.velsoft.mvn2.dao;

import es.velsoft.mvn2.modelo.TablaAux;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * DAO para la lista de tablas auxiliares
 *
 * @author Alejandro
 */
public class TablasAuxiliaresDAO extends GenericDAO {

    private EntityManager sesion;
    private static final Logger LOG = Logger.getLogger(TablasAuxiliaresDAO.class.getName());

    private static final String SQL_GET_ALL = "SELECT Id, descripcion FROM ";

    public List<TablaAux> getAllDatos(String tabla) {
        List<TablaAux> datos = new ArrayList<>();
        Query q;
        try {

            sesion = obtenerSessionHibernate();
            //q = sesion.createQuery(SQL_GET_ALL, TablaAux.class);
            q = sesion.createNativeQuery(SQL_GET_ALL + tabla + " ORDER BY id");
            List<Object[]> resultados = q.getResultList();
            for (Object[] filas : resultados) {
                TablaAux t = new TablaAux();
                t.setId(Integer.valueOf(filas[0].toString()));
                t.setDescripcion(filas[1].toString());
                System.out.println(t.getId() + " " + t.getDescripcion());
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

}
