package es.velsoft.mvn2.beans;

import es.velsoft.mvn2.dao.ListaTADAO;
import es.velsoft.mvn2.dao.TablasAuxiliaresDAO;
import es.velsoft.mvn2.modelo.ListaTA;
import es.velsoft.mvn2.modelo.TablaAux;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;

import javax.persistence.EntityManager;

/**
 *
 * @author Alejandro
 */
@ManagedBean(name="tabAuxBean")
@RequestScoped
public class TabAuxBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(TabAuxBean.class.getName());
    private static final long serialVersionUID = -1485678366573348398L;

    // la sesion de EntityManager
    EntityManager sesion;

    // la lista de tablas auxiliares
    List<ListaTA> tablas;
    // el nombre de la tabla seleccionada
    String tabla;
    private List<TablaAux> datos;

    /**
     * Creates a new instance of TabAuxBean
     */
    public TabAuxBean() {
        this.tablas = new ArrayList<>();
                this.datos = new ArrayList<>();

    }

    @PostConstruct
    public void init() {
        // pido los datos al dao
        ListaTADAO dao = new ListaTADAO();
        tablas = dao.getListaTA();
        System.out.println("Estoy en init. tablas tiene: " + tablas.size());

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

    public List<TablaAux> getDatos() {
        return datos;
    }

    public void setDatos(List<TablaAux> datos) {
        this.datos = datos;
    }
    
    
    
    public void handleSelect(AjaxBehaviorEvent e) {
        System.out.println("AjaxBehavior Listener : " + e.getBehavior()+ " .. " +  e.getSource());
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", "Póngase en contacto con el administrador."));
        
        // pido los datos al dao
        TablasAuxiliaresDAO dao = new TablasAuxiliaresDAO();
        datos = dao.getAllDatos(tabla);
        System.out.println("handleSelect. datos tiene: " + datos.size());        
    }

}
