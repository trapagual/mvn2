package es.velsoft.mvn2.beans;

import es.velsoft.mvn2.dao.ListaTADAO;
import es.velsoft.mvn2.dao.TablasAuxiliaresDAO;
import es.velsoft.mvn2.modelo.ListaTA;
import es.velsoft.mvn2.modelo.TablaAux;
import java.io.Serializable;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import javax.persistence.EntityManager;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Alejandro
 */
@ManagedBean(name = "tabAuxBean")
@ViewScoped
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
        this.tabla = null;

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
        System.out.println("AjaxBehavior Listener : " + e.getBehavior() + " .. " + e.getSource());
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", "Póngase en contacto con el administrador."));

        // pido los datos al dao
        if (tabla != null && tabla != "") {
            TablasAuxiliaresDAO dao = new TablasAuxiliaresDAO();
            datos = dao.getAllDatos(tabla);
            System.out.println("handleSelect. datos tiene: " + datos.size());
        } else {
            System.err.println("Tabla Seleccionada es null");
        }
    }
    
    // para añadir una fila
    public void onAddNew() {
        TablaAux fila = new TablaAux();
        int ultimo = (-1) * (abs(getUltimoValor(datos) + 1));
        fila.setId(ultimo);
        fila.setDescripcion("INTRODUZCA DESCRIPCION");
        datos.add(fila);

        FacesMessage msg = new FacesMessage("Fila nueva", fila.getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }    
    public void onRowEdit(RowEditEvent event) {
        System.out.println("En onRowEdit. Voy a editar el registro: " + ((TablaAux) event.getObject()).getId().toString());
        FacesMessage msg = new FacesMessage("Editado registro", ((TablaAux) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void onRowDelete(Integer id) {
        System.out.println("En onRowDelete. Voy a borrar el registro: " + id);
        FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_WARN, "Eliminado registro", id.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }    
    private int getUltimoValor(List<TablaAux> elArray) {
        int max = 0;
        for (TablaAux t : elArray) {
            if (abs(t.getId()) > abs(max)) max = abs(t.getId());
        }
        return max;
    }

}
