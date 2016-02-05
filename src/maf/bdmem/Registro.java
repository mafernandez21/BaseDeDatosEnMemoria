/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.bdmem;

import java.util.Objects;

/**
 * Representa una tupla para almacenar registros en una Base de Datos en Memoria
 *
 * @author Martín Alejandro Fernández
 * <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 * @version 1.0
 * @see maf.bdmem.BDEnMemoria
 */
public class Registro {

    //<editor-fold defaultstate="collapsed" desc="Miembros">
    private int id;
    private String tabla;
    private Object registro;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public Registro() {
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public Object getRegistro() {
        return registro;
    }

    public void setRegistro(Object registro) {
        this.registro = registro;
    }

    /**
     * Método sobreescrito para poder determinar la igualdad de dos objetos de
     * tipo {@link maf.bdmem.Registro}. Se compara un código hash generado a
     * partir de los valores de los miembros {@link maf.bdmem.Registro#id}, y
     * {@link maf.bdmem.Registro#tabla}
     *
     * @param obj Es el Objeto a ser comparado
     * @return VERDADERO - Si los códigos hash son idénticos, caso contrario
     * devuelve FALSO
     * @see maf.bdmem.Registro#hashCode()
     * @author Martín Alejandro Fernández
     * @version 1.0
     * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Registro) {
            Registro r = (Registro) obj;
            return this.hashCode() == r.hashCode();
        }
        return false;
    }

    /**
     * Método sobreescrito para generar un código hash a partir de los valores
     * de los miembros {@link maf.bdmem.Registro#id}, y
     * {@link maf.bdmem.Registro#tabla}
     *
     * @return Entero que representa un código hash
     * @author Martín Alejandro Fernández
     * @version 1.0
     * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.tabla);
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }
    //</editor-fold>

}
