
package es.velsoft.mvn2.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase genérica de utilidad para todos los objetos
 * que tienen un identificador entero y un nombre en formato
 * cadena
 *
 * @author Alejandro
 */
public class IdNombreObj implements Serializable {
    
    Integer id;
    String nombre;

    public IdNombreObj() {
    }

    public IdNombreObj(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IdNombreObj other = (IdNombreObj) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IdNombreObj{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
    
}
