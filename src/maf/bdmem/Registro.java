/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.bdmem;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Representa un registro para almacenar datos en una Base de Datos en Memoria
 *
 * @author Martín Alejandro Fernández
 * <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 * @version 1.1
 * @see maf.bdmem.BDEnMemoria
 */
public class Registro {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int id;
    private String sNombreTabla;
    private Object oDatos;
    //MetaDatos
    private int numeroColumnas=0;
    private String[] nombreColumnas;
    private Type[] tipoColumnas;
    private Object[] valorColumnas;
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

    public String getSNombreTabla() {
        return sNombreTabla;
    }

    public void setSNombreTabla(String sNombreTabla) {
        this.sNombreTabla = sNombreTabla;
    }

    public Object getODatos() {
        if(this.oDatos!=null){
            this.setMetaDatos(this.oDatos.getClass());
        }
        return this.oDatos;
    }

    public void setODatos(Object oDatos) {
        this.oDatos = oDatos;
        if(this.oDatos!=null){
            this.setMetaDatos(this.oDatos.getClass());
        }
 
    }

    public int getNumeroColumnas() {
        setMetaDatos(this.oDatos.getClass());
        return this.numeroColumnas;
    }

    public String getNombreColumna(int idx) {
        setMetaDatos(this.oDatos.getClass());
        return this.nombreColumnas[idx];
    }

    public Object getValorObjeto(int idx) {
        this.setMetaDatos(this.oDatos.getClass());
        return this.valorColumnas[idx];
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funciones">
    /**
     * Método que extrae los metadatos del objeto que forma parte del registro
     * @param clase Clase de la que el objetos es una instancia.
     */
    private void setMetaDatos(Class clase) {
        Field atributos[] = clase.getDeclaredFields();

        this.numeroColumnas = atributos.length;
        this.nombreColumnas = new String[this.numeroColumnas];
        this.tipoColumnas = new Type[this.numeroColumnas];
        this.valorColumnas=new Object[this.numeroColumnas];
        
        for (int i = 0; i < atributos.length; i++) {
            try {
            atributos[i].setAccessible(true);
            this.nombreColumnas[i] = atributos[i].getName();
            this.tipoColumnas[i]=atributos[i].getGenericType();
            this.valorColumnas[i]=atributos[i].get(this.oDatos);
            } catch (IllegalArgumentException | IllegalAccessException | NullPointerException ex) {
            }
        }
    }

    /**
     * Método sobreescrito para poder determinar la igualdad de dos objetos de
     * tipo {@link maf.bdmem.Registro}. Se compara en contenido del valor del 
     * registro. El valor del registro se corresponde con los datos del objeto
     * {@link maf.bdmem.Registro#getODatos()}.
     *
     * @param obj Es el Objeto a ser comparado
     * @return VERDADERO - Si el objeto comparado es igual al almacenado en un 
     * registro dentro de la BD. Caso contrario devuelve FALSO
     * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Registro) {
            Registro r = (Registro) obj;
            this.getODatos().equals(obj);
            return this.getODatos().equals(r.getODatos());
        }
        return false;
    }

    /**
     * Método sobreescrito para generar un código hash a partir de los valores
     * de los miembros {@link maf.bdmem.Registro#id}, y
     * {@link maf.bdmem.Registro#sNombreTabla}. Se puede usar para determinar si
     * dos registros son iguales, independientemente del objeto contenido en el 
     * registro
     *
     * @return Entero que representa un código hash
     * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.sNombreTabla);
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }
    //</editor-fold>
    
}
