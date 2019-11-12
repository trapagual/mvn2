package es.velsoft.mvn2.beans;


import es.velsoft.mvn2.dao.EntityManagerHelper;
import es.velsoft.mvn2.modelo.Pedidos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
@ManagedBean(name="pedidosBean")
@ViewScoped
public class PedidosBean implements Serializable {

    private static final long serialVersionUID = 7143779796955040248L;
    
    private List<Pedidos> pedidos;
    private List<Pedidos> filtrados;
    

    private Pedidos selectedPedido;
    private static final Logger LOG = Logger.getLogger(PedidosBean.class.getName());
    
    public PedidosBean() {
        this.filtrados = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }
    EntityManager sesion;
    
    @PostConstruct
    public void init() {
        sesion = EntityManagerHelper.getEMF().createEntityManager();
        Query q = sesion.createNamedQuery("Pedidos.findAll");

        this.pedidos = q.getResultList();
        LOG.log(Level.INFO, "El array de pedidos tiene {0} registros", pedidos.size());
    }

    public List<Pedidos> getPedidos() {
        return pedidos;
    }

    public List<Pedidos> getFiltrados() {
        return filtrados;
    }

    public void setFiltrados(List<Pedidos> filtrados) {
        this.filtrados = filtrados;
    }


    public Pedidos getSelectedPedido() {
        return selectedPedido;
    }

    public void setSelectedPedido(Pedidos selectedPedido) {
        this.selectedPedido = selectedPedido;
    }

    
}
