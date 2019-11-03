package es.velsoft.mvn2.beans;

import es.velsoft.mvn2.dao.ListaTADAO;
import es.velsoft.mvn2.modelo.ListaTA;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author Alejandro
 */
@Named(value = "tabAuxBean")
@ViewScoped
public class TabAuxBean {

    // la sesion de EntityManager
    EntityManager sesion;

    // la lista de tablas auxiliares
    List<ListaTA> tablas;
    // el nombre de la tabla seleccionada
    String tabla;

    /**
     * Creates a new instance of TabAuxBean
     */
    public TabAuxBean() {
        this.tablas = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        // pido los datos al dao
        ListaTADAO dao = new ListaTADAO();
        tablas = dao.getListaTA();

    }

    public List<ListaTA> getTablas() {
        return tablas;
    }


    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

}
