package es.velsoft.mvn2.beans;

import es.velsoft.mvn2.dao.ListaTADAO;
import es.velsoft.mvn2.dao.TablasAuxiliaresDAO;
import es.velsoft.mvn2.modelo.IdNombreObj;
import es.velsoft.mvn2.modelo.ListaTA;
import es.velsoft.mvn2.modelo.TablaAux;
import java.io.Serializable;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

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
    private List<IdNombreObj> modificados;
    private boolean mostrarBloqueo, btnGrabarHabilitado;
    private boolean btnAniadirHabilitado;

    /**
     * Creates a new instance of TabAuxBean
     */
    public TabAuxBean() {
        this.tablas = new ArrayList<>();
        this.datos = new ArrayList<>();
        this.modificados = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        mostrarBloqueo = false;
        btnGrabarHabilitado = false;
        btnAniadirHabilitado = false;
        tabla=null;
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

    public boolean isMostrarBloqueo() {
        return mostrarBloqueo;
    }

    public void setMostrarBloqueo(boolean mostrarBloqueo) {
        this.mostrarBloqueo = mostrarBloqueo;
    }

    public boolean isBtnGrabarHabilitado() {
        return btnGrabarHabilitado;
    }

    public void setBtnGrabarHabilitado(boolean btnGrabarHabilitado) {
        this.btnGrabarHabilitado = btnGrabarHabilitado;
    }

    public boolean isBtnAniadirHabilitado() {
        return btnAniadirHabilitado;
    }

    public void setBtnAniadirHabilitado(boolean btnAniadirHabilitado) {
        this.btnAniadirHabilitado = btnAniadirHabilitado;
    }

    ///////////////// MANIPULADORES DE EVENTOS 
    public void handleSelect(AjaxBehaviorEvent e) {
        System.out.println("AjaxBehavior Listener : " + e.getBehavior() + " .. " + e.getSource());
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", "Póngase en contacto con el administrador."));

        // pido los datos al dao
        if (tabla != null && tabla != "") {
            TablasAuxiliaresDAO dao = new TablasAuxiliaresDAO();
            datos = dao.getAllDatos(tabla);
            System.out.println("handleSelect. datos tiene: " + datos.size());
            btnAniadirHabilitado = true;
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

        btnGrabarHabilitado = true;

        // guardar en la tabla de actualizacion
        modificados.add(new IdNombreObj(ultimo, "CREATE"));

        FacesMessage msg = new FacesMessage("Fila nueva", fila.getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    // PONE UNA FILA EN MODO EDICION
    public void onRowEdit(RowEditEvent event) {
        System.out.println("En onRowEdit. Voy a editar el registro: " + ((TablaAux) event.getObject()).getId().toString());
        FacesMessage msg = new FacesMessage("Editado registro", ((TablaAux) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        btnGrabarHabilitado = true;

        // guardar en la tabla de actualizacion
        IdNombreObj ino = new IdNombreObj(
                ((TablaAux) event.getObject()).getId(),
                "UPDATE"
        );
        modificados.add(ino);
    }

    // ELIMINA UNA FILA
    public void onRowDelete(TablaAux aux) {
        System.out.println("En onRowDelete. Voy a borrar el registro: " + aux.toString());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado registro", aux.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        datos.remove(aux);
        
        btnGrabarHabilitado = true;

        // guardar en la tabla de actualizacion
        modificados.add(new IdNombreObj(aux.getId(), "DELETE"));
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        btnGrabarHabilitado = true;

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onSave() {
        mostrarBloqueo = true;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TabAuxBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnGrabarHabilitado = false;

    }

    private int getUltimoValor(List<TablaAux> elArray) {
        int max = 0;
        for (TablaAux t : elArray) {
            if (abs(t.getId()) > abs(max)) {
                max = abs(t.getId());
            }
        }
        return max;
    }

}
