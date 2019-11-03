package es.velsoft.mvn2.dao;

import es.velsoft.mvn2.modelo.ListaTA;
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
public class ListaTADAO extends GenericDAO {

    private EntityManager sesion;
    private static final Logger LOG = Logger.getLogger(ListaTADAO.class.getName());

    private static final String SQL_GET_LISTA_TA_SQLSERVER = "SELECT id, Nombre, Columnas FROM vTablasAuxiliares ORDER BY Nombre";
    private static final String SQL_GET_LISTA_TA_MYSQL = "SELECT" +
        " (@rownum:=@rownum + 1) AS id," +
        " t.TABLE_NAME AS Nombre, count(c.COLUMN_NAME) AS Columnas" +
        " FROM information_schema.`TABLES` t" +
        " JOIN information_schema.`COLUMNS` c ON t.table_catalog = c.TABLE_CATALOG AND t.table_schema = c.TABLE_SCHEMA AND t.table_name = c.TABLE_NAME" +
        ", (SELECT @rownum:=0) r" +
        " WHERE t.TABLE_NAME LIKE 'aux_%'" +
        " GROUP BY t.table_name" +
        " ORDER BY t.table_name";
    
    public List<ListaTA> getListaTA() {
        List<ListaTA> lista = new ArrayList<>();

        Query q;
        try {
            sesion = obtenerSessionHibernate();
            if (isSesionSQLServer(sesion)) {
                q = sesion.createNativeQuery(SQL_GET_LISTA_TA_SQLSERVER, ListaTA.class);
            } else {
                q = sesion.createNativeQuery(SQL_GET_LISTA_TA_MYSQL, ListaTA.class);

            }
            lista = q.getResultList();
        } catch (Exception e)  {
            LOG.log(Level.SEVERE,"getAllFromTA: error %s", e.getLocalizedMessage());

        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }

        return lista;

    }

    private EntityManager obtenerSessionHibernate() throws Exception {
        EntityManager session = EntityManagerHelper.getEMF().createEntityManager();

        return session;
    }

}