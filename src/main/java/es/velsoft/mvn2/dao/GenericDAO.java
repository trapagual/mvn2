
package es.velsoft.mvn2.dao;

import java.util.Map;
import javax.persistence.EntityManager;

/**
 *  DAO QUE CONTIENE LOS METODOS GENERICOS NECESARIOS PARA TODOS LOS DAO
 * QUE TIENEN QUE HEREDAR DE ESTA
 * @author Alejandro
 */
public class GenericDAO {


    EntityManager obtenerSessionHibernate() throws Exception {
        EntityManager session = EntityManagerHelper.getEMF().createEntityManager();

        return session;
    }

    boolean isSesionSQLServer(EntityManager s) throws Exception {
        boolean respuesta = false;

        Map<String, Object> mapa = s.getEntityManagerFactory().getProperties();
        String valor = (String) mapa.get("hibernate.ejb.persistenceUnitName");
        if (null == valor) {
            throw new Exception("No se puede definir la base de datos");
        } else switch (valor) {
            case "eg_JPA_MYSQL":
                System.out.println("Estamos con MySQL");
                respuesta = false;
                break;
            case "eg_JPA_SQLSERVER":
                System.out.println("Estamos con SQLServer");
                respuesta = true;
                break;
            default:
                throw new Exception("No se puede definir la base de datos");
        }
        return respuesta;
    }
    
}
