
package es.velsoft.mvn2.modelo;

import java.io.Serializable;

/**
 *  Esto NO es una entity
 * Es un POJO generico porque el objeto se rellenara con los datos de la tabla
 * que se seleccione.
 * @author Alejandro
 */
public class TablaAux implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String descripcion;

    public TablaAux() {
    }

    public TablaAux(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof TablaAux)) {
            return false;
        }
        TablaAux other = (TablaAux) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TablaAux{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }


    
}
