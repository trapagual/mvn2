
package es.velsoft.mvn2.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SGEN0290
 */
@Entity
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 1000)
    @Column(name = "cliente_final")
    private String clienteFinal;
    @Column(name = "id_contrato")
    private Integer idContrato;
    @Size(max = 50)
    @Column(name = "ref_cliente")
    private String refCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_carga")
    private int idTipoCarga;
    @Column(name = "adr")
    private Boolean adr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "fecha_ini_servicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIniServicio;
    @Column(name = "fecha_fin_servicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinServicio;
    @Column(name = "id_ruta")
    private Integer idRuta;
    @Column(name = "tot_kms_nac")
    private Long totKmsNac;
    @Column(name = "cost_kms_nac")
    private Long costKmsNac;
    @Column(name = "tot_kms_int")
    private Long totKmsInt;
    @Column(name = "cost_kms_int")
    private Long costKmsInt;
    @Column(name = "kms_autopst_nac")
    private Long kmsAutopstNac;
    @Column(name = "cost_kms_autopst_nac")
    private Long costKmsAutopstNac;
    @Column(name = "kms_autopst_int")
    private Long kmsAutopstInt;
    @Column(name = "cost_kms_autopst_int")
    private Long costKmsAutopstInt;
    @Column(name = "tot_kms_ida")
    private Long totKmsIda;
    @Column(name = "tot_kms_vuelta")
    private Long totKmsVuelta;
    @Column(name = "tot_kms")
    private Long totKms;
    @Column(name = "tot_amort_remolques")
    private Long totAmortRemolques;
    @Column(name = "tot_amort_tractoras")
    private Long totAmortTractoras;
    @Column(name = "tot_seguro_remolques")
    private Long totSeguroRemolques;
    @Column(name = "tot_seguro_tractoras")
    private Long totSeguroTractoras;
    @Column(name = "tot_seguro_mercancias")
    private Long totSeguroMercancias;
    @Column(name = "tot_mntmnto_ruedas")
    private Long totMntmntoRuedas;
    @Column(name = "tot_nomina_condctr_nac")
    private Long totNominaCondctrNac;
    @Column(name = "tot_nomina_condctr_inter")
    private Long totNominaCondctrInter;
    @Column(name = "tot_dietas_kms")
    private Long totDietasKms;
    @Column(name = "precio_litro_gasoil")
    private Long precioLitroGasoil;
    @Column(name = "gasoil_remolque")
    private Long gasoilRemolque;
    @Column(name = "gasoil_tractora")
    private Long gasoilTractora;
    @Column(name = "total_ingresos")
    private Long totalIngresos;
    @Column(name = "total_gastos")
    private Long totalGastos;
    @Column(name = "excedido")
    private Boolean excedido;
    @Column(name = "id_grupo")
    private Integer idGrupo;
    @Column(name = "id_expediente")
    private Integer idExpediente;
    @Column(name = "id_estado")
    private Integer idEstado;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "idHojaCarga")
    private Integer idHojaCarga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_vehiculo")
    private int idTipoVehiculo;
    @Column(name = "id_cliente")
    private Integer idCliente;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_agente", referencedColumnName = "idAgente")    
    private Agentes agente;

    public Pedidos() {
    }

    public Pedidos(Integer id) {
        this.id = id;
    }

    public Pedidos(Integer id, Agentes agente, int idTipoCarga, Date fechaAlta, int idTipoVehiculo) {
        this.id = id;
        this.agente = agente;
        this.idTipoCarga = idTipoCarga;
        this.fechaAlta = fechaAlta;
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClienteFinal() {
        return clienteFinal;
    }

    public void setClienteFinal(String clienteFinal) {
        this.clienteFinal = clienteFinal;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getRefCliente() {
        return refCliente;
    }

    public void setRefCliente(String refCliente) {
        this.refCliente = refCliente;
    }

    public int getIdTipoCarga() {
        return idTipoCarga;
    }

    public void setIdTipoCarga(int idTipoCarga) {
        this.idTipoCarga = idTipoCarga;
    }

    public Boolean getAdr() {
        return adr;
    }

    public void setAdr(Boolean adr) {
        this.adr = adr;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaIniServicio() {
        return fechaIniServicio;
    }

    public void setFechaIniServicio(Date fechaIniServicio) {
        this.fechaIniServicio = fechaIniServicio;
    }

    public Date getFechaFinServicio() {
        return fechaFinServicio;
    }

    public void setFechaFinServicio(Date fechaFinServicio) {
        this.fechaFinServicio = fechaFinServicio;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Long getTotKmsNac() {
        return totKmsNac;
    }

    public void setTotKmsNac(Long totKmsNac) {
        this.totKmsNac = totKmsNac;
    }

    public Long getCostKmsNac() {
        return costKmsNac;
    }

    public void setCostKmsNac(Long costKmsNac) {
        this.costKmsNac = costKmsNac;
    }

    public Long getTotKmsInt() {
        return totKmsInt;
    }

    public void setTotKmsInt(Long totKmsInt) {
        this.totKmsInt = totKmsInt;
    }

    public Long getCostKmsInt() {
        return costKmsInt;
    }

    public void setCostKmsInt(Long costKmsInt) {
        this.costKmsInt = costKmsInt;
    }

    public Long getKmsAutopstNac() {
        return kmsAutopstNac;
    }

    public void setKmsAutopstNac(Long kmsAutopstNac) {
        this.kmsAutopstNac = kmsAutopstNac;
    }

    public Long getCostKmsAutopstNac() {
        return costKmsAutopstNac;
    }

    public void setCostKmsAutopstNac(Long costKmsAutopstNac) {
        this.costKmsAutopstNac = costKmsAutopstNac;
    }

    public Long getKmsAutopstInt() {
        return kmsAutopstInt;
    }

    public void setKmsAutopstInt(Long kmsAutopstInt) {
        this.kmsAutopstInt = kmsAutopstInt;
    }

    public Long getCostKmsAutopstInt() {
        return costKmsAutopstInt;
    }

    public void setCostKmsAutopstInt(Long costKmsAutopstInt) {
        this.costKmsAutopstInt = costKmsAutopstInt;
    }

    public Long getTotKmsIda() {
        return totKmsIda;
    }

    public void setTotKmsIda(Long totKmsIda) {
        this.totKmsIda = totKmsIda;
    }

    public Long getTotKmsVuelta() {
        return totKmsVuelta;
    }

    public void setTotKmsVuelta(Long totKmsVuelta) {
        this.totKmsVuelta = totKmsVuelta;
    }

    public Long getTotKms() {
        return totKms;
    }

    public void setTotKms(Long totKms) {
        this.totKms = totKms;
    }

    public Long getTotAmortRemolques() {
        return totAmortRemolques;
    }

    public void setTotAmortRemolques(Long totAmortRemolques) {
        this.totAmortRemolques = totAmortRemolques;
    }

    public Long getTotAmortTractoras() {
        return totAmortTractoras;
    }

    public void setTotAmortTractoras(Long totAmortTractoras) {
        this.totAmortTractoras = totAmortTractoras;
    }

    public Long getTotSeguroRemolques() {
        return totSeguroRemolques;
    }

    public void setTotSeguroRemolques(Long totSeguroRemolques) {
        this.totSeguroRemolques = totSeguroRemolques;
    }

    public Long getTotSeguroTractoras() {
        return totSeguroTractoras;
    }

    public void setTotSeguroTractoras(Long totSeguroTractoras) {
        this.totSeguroTractoras = totSeguroTractoras;
    }

    public Long getTotSeguroMercancias() {
        return totSeguroMercancias;
    }

    public void setTotSeguroMercancias(Long totSeguroMercancias) {
        this.totSeguroMercancias = totSeguroMercancias;
    }

    public Long getTotMntmntoRuedas() {
        return totMntmntoRuedas;
    }

    public void setTotMntmntoRuedas(Long totMntmntoRuedas) {
        this.totMntmntoRuedas = totMntmntoRuedas;
    }

    public Long getTotNominaCondctrNac() {
        return totNominaCondctrNac;
    }

    public void setTotNominaCondctrNac(Long totNominaCondctrNac) {
        this.totNominaCondctrNac = totNominaCondctrNac;
    }

    public Long getTotNominaCondctrInter() {
        return totNominaCondctrInter;
    }

    public void setTotNominaCondctrInter(Long totNominaCondctrInter) {
        this.totNominaCondctrInter = totNominaCondctrInter;
    }

    public Long getTotDietasKms() {
        return totDietasKms;
    }

    public void setTotDietasKms(Long totDietasKms) {
        this.totDietasKms = totDietasKms;
    }

    public Long getPrecioLitroGasoil() {
        return precioLitroGasoil;
    }

    public void setPrecioLitroGasoil(Long precioLitroGasoil) {
        this.precioLitroGasoil = precioLitroGasoil;
    }

    public Long getGasoilRemolque() {
        return gasoilRemolque;
    }

    public void setGasoilRemolque(Long gasoilRemolque) {
        this.gasoilRemolque = gasoilRemolque;
    }

    public Long getGasoilTractora() {
        return gasoilTractora;
    }

    public void setGasoilTractora(Long gasoilTractora) {
        this.gasoilTractora = gasoilTractora;
    }

    public Long getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(Long totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public Long getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(Long totalGastos) {
        this.totalGastos = totalGastos;
    }

    public Boolean getExcedido() {
        return excedido;
    }

    public void setExcedido(Boolean excedido) {
        this.excedido = excedido;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdHojaCarga() {
        return idHojaCarga;
    }

    public void setIdHojaCarga(Integer idHojaCarga) {
        this.idHojaCarga = idHojaCarga;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Agentes getAgente() {
        return this.agente;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pedidos[ id=" + id + " ]";
    }

}
