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
    private boolean btnGrabarHabilitado;
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
        btnGrabarHabilitado = false;
        btnAniadirHabilitado = false;
        tabla = null;
        // pido los datos al dao
        ListaTADAO dao = new ListaTADAO();
        tablas = dao.getListaTA();
        LOG.log(Level.INFO, "Estoy en init. Tablas tiene: {0} registros", tablas.size());

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

        // pido los datos al dao
        if (tabla != null && !"".equals(tabla)) {
            TablasAuxiliaresDAO dao = new TablasAuxiliaresDAO();
            datos = dao.getAllDatos(tabla);
            LOG.log(Level.INFO, "Estoy en handleSelect. Datos tiene: {0} registros", datos.size());
            btnAniadirHabilitado = true;
        } else {
            LOG.log(Level.SEVERE, "Estoy en handleSelect. Tabla Seleccionada es NULL");

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
        //modificados.add(new IdNombreObj(ultimo, "CREATE"));
        // NO HACE FALTA METER EL CREATE PORQUE DESPUES DE UNA INSERCION SIEMPRE VIENE UN EDIT, O SEA
        // QUE EL MISMO REGISTRO APARECERIA DOS VECES, UNA PARA INSERTARLO Y OTRA PARA EDITARLO
        // SI INSERTA UNA FILA, PERO NO LA EDITA PARA PONERLE UN VALOR CORRECTO, SIMPLEMENTE
        // SE DESPRECIA LA INSERCION. ES EN EL UPDATE DONDE HAY QUE DECIDIR SI ES UNA INSERCION O UNA ACTUALIZACION

        FacesMessage msg = new FacesMessage("Fila nueva", fila.getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    // ELIMINA UNA FILA
    public void onRowDelete(TablaAux aux) {
        LOG.log(Level.INFO, "En onRowDelete. Voy a editar el registro: {0}", aux.toString());

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado registro", aux.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        datos.remove(aux);

        btnGrabarHabilitado = true;

        // guardar en la tabla de actualizacion
        modificados.add(new IdNombreObj(aux.getId(), "DELETE"));
    }

    // editar una celda
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        int numFila = event.getRowIndex();
 


        // obtengo el id del objeto a actualizar, dato el rowIndex de la fila del array
        int id = datos.get(numFila).getId();
        LOG.log(Level.INFO, "En onCellEdit. Voy a editar el registro: {0} con el nuevo valor: {1}", new Object[]{ id, newValue} );
        
        // guardar en la tabla de actualizacion
        modificados.add(new IdNombreObj(id, "UPDATE"));    
        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }        
        btnGrabarHabilitado = true;
        
    }

    public void onSave() {
        /*
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TabAuxBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        TablasAuxiliaresDAO tadao = new TablasAuxiliaresDAO();
        boolean que=tadao.save(modificados, tabla, datos);
        // recargo la tabla de datos para eliminar los negativos
        datos = tadao.getAllDatos(tabla);
        // poner a cero los modificados para la proxima vuelta
        modificados = new ArrayList<>();        
        LOG.log(Level.INFO, "Después de llamar al DAO");
        
                   
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
