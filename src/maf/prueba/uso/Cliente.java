/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.prueba.uso;

/**
 * Abstraccion de un "Cliente", para poder hacer pruebas con la Base de Datos
 *
 * @author Martín Alejandro Fernández
 * <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 * @version 1.0
 * @see maf.bdmem.BDEnMemoria
 */
public class Cliente {

    private int id;
    private String nombre;
    private String apellido;
    private long dni;
    private String domicilio;
    private String localidad;
    private String cuit;

    public Cliente() {
    }

    public Cliente(String cuit) {
        this.cuit = cuit;
    }

    public Cliente(int id, String nombre, String apellido, long dni, String domicilio, String localidad, String cuit) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.cuit = cuit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", domicilio=" + domicilio + ", localidad=" + localidad + ", cuit=" + cuit + '}';
    }
}
