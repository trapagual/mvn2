package es.velsoft.mvn2.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "agentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agentes.findAll", query = "SELECT a FROM Agentes a")})
public class Agentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAgente")
    private Integer idAgente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CodAgente")
    private int codAgente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Nick")
    private String nick;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CodConductor")
    private int codConductor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CodGrupoAgente")
    private int codGrupoAgente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CodDepartamento")
    private int codDepartamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Autor")
    private String autor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FechaInsercion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsercion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Maquina")
    private String maquina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Activo")
    private Character activo;
    @Column(name = "Trafico")
    private Character trafico;
    @Size(max = 200)
    @Column(name = "email_riesgos")
    private String emailRiesgos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PorcentajeEnganche")
    private BigDecimal porcentajeEnganche;
    @Column(name = "CodResponsable")
    private Integer codResponsable;
    @Size(max = 20)
    @Column(name = "Telefono")
    private String telefono;
    @Size(max = 20)
    @Column(name = "TelefonoDpto")
    private String telefonoDpto;
    @Size(max = 20)
    @Column(name = "FaxDpto")
    private String faxDpto;
    @Column(name = "ConfirmacionExpte")
    private Boolean confirmacionExpte;
    @Size(max = 10)
    @Column(name = "codgrupo")
    private String codgrupo;
    @Column(name = "DesbloqueoMatricula")
    private Boolean desbloqueoMatricula;
    @Size(max = 500)
    @Column(name = "ObservacionesPlanning")
    private String observacionesPlanning;
    @Column(name = "codresponsableZona")
    private Integer codresponsableZona;
    @Column(name = "AltaVehiculosPropios")
    private Boolean altaVehiculosPropios;
    @Column(name = "AltaVehiculosExternos")
    private Boolean altaVehiculosExternos;
    @Column(name = "ExcluirMargen")
    private Boolean excluirMargen;
    @Column(name = "GestionarVehiculosPlanning")
    private Boolean gestionarVehiculosPlanning;
    @Column(name = "ModificarExptesFacturados")
    private Boolean modificarExptesFacturados;
    @Column(name = "AsociarExptesFraValidada")
    private Boolean asociarExptesFraValidada;
    @Size(max = 200)
    @Column(name = "email_cargas")
    private String emailCargas;
    @Column(name = "CancelarAnticipos")
    private Boolean cancelarAnticipos;
    @Column(name = "MinPrecioKm")
    private BigDecimal minPrecioKm;
    @Column(name = "AgentePlanning")
    private Boolean agentePlanning;
    @Size(max = 30)
    @Column(name = "GestorCobros")
    private String gestorCobros;
    @Column(name = "Grupajes")
    private Boolean grupajes;
    @Size(max = 100)
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy="agente")
    private List<Pedidos> pedidos;
    
    public Agentes() {
        super();
        this.pedidos = new ArrayList<>();
    }

    public Agentes(Integer idAgente) {
        this.idAgente = idAgente;
        this.pedidos = new ArrayList<>();
    }

    public Agentes(Integer idAgente, int codAgente, String nombre, String apellidos, String nick, int codConductor, int codGrupoAgente, int codDepartamento, String autor, Date fechaInsercion, String maquina, Character activo) {
        this.idAgente = idAgente;
        this.codAgente = codAgente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nick = nick;
        this.codConductor = codConductor;
        this.codGrupoAgente = codGrupoAgente;
        this.codDepartamento = codDepartamento;
        this.autor = autor;
        this.fechaInsercion = fechaInsercion;
        this.maquina = maquina;
        this.activo = activo;
        this.pedidos = new ArrayList<>();
    }

    public Integer getIdAgente() {
        return idAgente;
    }

    public void setIdAgente(Integer idAgente) {
        this.idAgente = idAgente;
    }

    public int getCodAgente() {
        return codAgente;
    }

    public void setCodAgente(int codAgente) {
        this.codAgente = codAgente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getCodConductor() {
        return codConductor;
    }

    public void setCodConductor(int codConductor) {
        this.codConductor = codConductor;
    }

    public int getCodGrupoAgente() {
        return codGrupoAgente;
    }

    public void setCodGrupoAgente(int codGrupoAgente) {
        this.codGrupoAgente = codGrupoAgente;
    }

    public int getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(int codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(Date fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public Character getActivo() {
        return activo;
    }

    public void setActivo(Character activo) {
        this.activo = activo;
    }

    public Character getTrafico() {
        return trafico;
    }

    public void setTrafico(Character trafico) {
        this.trafico = trafico;
    }

    public String getEmailRiesgos() {
        return emailRiesgos;
    }

    public void setEmailRiesgos(String emailRiesgos) {
        this.emailRiesgos = emailRiesgos;
    }

    public BigDecimal getPorcentajeEnganche() {
        return porcentajeEnganche;
    }

    public void setPorcentajeEnganche(BigDecimal porcentajeEnganche) {
        this.porcentajeEnganche = porcentajeEnganche;
    }

    public Integer getCodResponsable() {
        return codResponsable;
    }

    public void setCodResponsable(Integer codResponsable) {
        this.codResponsable = codResponsable;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefonoDpto() {
        return telefonoDpto;
    }

    public void setTelefonoDpto(String telefonoDpto) {
        this.telefonoDpto = telefonoDpto;
    }

    public String getFaxDpto() {
        return faxDpto;
    }

    public void setFaxDpto(String faxDpto) {
        this.faxDpto = faxDpto;
    }

    public Boolean getConfirmacionExpte() {
        return confirmacionExpte;
    }

    public void setConfirmacionExpte(Boolean confirmacionExpte) {
        this.confirmacionExpte = confirmacionExpte;
    }

    public String getCodgrupo() {
        return codgrupo;
    }

    public void setCodgrupo(String codgrupo) {
        this.codgrupo = codgrupo;
    }

    public Boolean getDesbloqueoMatricula() {
        return desbloqueoMatricula;
    }

    public void setDesbloqueoMatricula(Boolean desbloqueoMatricula) {
        this.desbloqueoMatricula = desbloqueoMatricula;
    }

    public String getObservacionesPlanning() {
        return observacionesPlanning;
    }

    public void setObservacionesPlanning(String observacionesPlanning) {
        this.observacionesPlanning = observacionesPlanning;
    }

    public Integer getCodresponsableZona() {
        return codresponsableZona;
    }

    public void setCodresponsableZona(Integer codresponsableZona) {
        this.codresponsableZona = codresponsableZona;
    }

    public Boolean getAltaVehiculosPropios() {
        return altaVehiculosPropios;
    }

    public void setAltaVehiculosPropios(Boolean altaVehiculosPropios) {
        this.altaVehiculosPropios = altaVehiculosPropios;
    }

    public Boolean getAltaVehiculosExternos() {
        return altaVehiculosExternos;
    }

    public void setAltaVehiculosExternos(Boolean altaVehiculosExternos) {
        this.altaVehiculosExternos = altaVehiculosExternos;
    }

    public Boolean getExcluirMargen() {
        return excluirMargen;
    }

    public void setExcluirMargen(Boolean excluirMargen) {
        this.excluirMargen = excluirMargen;
    }

    public Boolean getGestionarVehiculosPlanning() {
        return gestionarVehiculosPlanning;
    }

    public void setGestionarVehiculosPlanning(Boolean gestionarVehiculosPlanning) {
        this.gestionarVehiculosPlanning = gestionarVehiculosPlanning;
    }

    public Boolean getModificarExptesFacturados() {
        return modificarExptesFacturados;
    }

    public void setModificarExptesFacturados(Boolean modificarExptesFacturados) {
        this.modificarExptesFacturados = modificarExptesFacturados;
    }

    public Boolean getAsociarExptesFraValidada() {
        return asociarExptesFraValidada;
    }

    public void setAsociarExptesFraValidada(Boolean asociarExptesFraValidada) {
        this.asociarExptesFraValidada = asociarExptesFraValidada;
    }

    public String getEmailCargas() {
        return emailCargas;
    }

    public void setEmailCargas(String emailCargas) {
        this.emailCargas = emailCargas;
    }

    public Boolean getCancelarAnticipos() {
        return cancelarAnticipos;
    }

    public void setCancelarAnticipos(Boolean cancelarAnticipos) {
        this.cancelarAnticipos = cancelarAnticipos;
    }

    public BigDecimal getMinPrecioKm() {
        return minPrecioKm;
    }

    public void setMinPrecioKm(BigDecimal minPrecioKm) {
        this.minPrecioKm = minPrecioKm;
    }

    public Boolean getAgentePlanning() {
        return agentePlanning;
    }

    public void setAgentePlanning(Boolean agentePlanning) {
        this.agentePlanning = agentePlanning;
    }

    public String getGestorCobros() {
        return gestorCobros;
    }

    public void setGestorCobros(String gestorCobros) {
        this.gestorCobros = gestorCobros;
    }

    public Boolean getGrupajes() {
        return grupajes;
    }

    public void setGrupajes(Boolean grupajes) {
        this.grupajes = grupajes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCadenaAgente() {
        return "(" +this.nick + ") " + this.nombre + " " + this.apellidos;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgente != null ? idAgente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agentes)) {
            return false;
        }
        Agentes other = (Agentes) object;
        if ((this.idAgente == null && other.idAgente != null) || (this.idAgente != null && !this.idAgente.equals(other.idAgente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.velsoft.eg.modelo.Agentes[ idAgente=" + idAgente + " ]";
    }
    
}
