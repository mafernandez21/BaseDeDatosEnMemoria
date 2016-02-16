/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.bdmem;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * <b>Base de Datos en Memoria:</b><br>
 * Simula una base de datos en memoria usando un {@link ArrayList} para
 * almacenar los datos del tipo {@link maf.bdmem.Registro}<br>
 * <p>
 * <b>Modo de uso:</b><br>
 * Para poder usar la base de datos primero se deberá establecer una "conexión"
 * usando el método {@link maf.bdmem.BDEnMemoria#conectar()
 * conectar} y así obtener una instancia de la base de datos para poder
 * utilizarla. Como se usa el patrón de diseño "Singlenton", siempre se accede a
 * una única instancia de la Base de Datos<br><br>
 * <b>Ejemplo:</b>
 * <pre>
 * {@code
 *  //Conectamos con la BD
 * BDEnMemoria miBD = BDEnMemoria.conectar();
 * //Le pedimos a la BD la cantidad de registros almacenados
 * int n = miBD.getNumeroDeRegistros();
 * //Mostramos por pantalla
 * System.out.println(String.valueOf(n) + " registros ingresados en la Base de Datos");
 * }
 * </pre>
 * <b>Comandos incluidos:</b>
 * <ul>
 * <li><b>Comandos DDL (Data Definition Lenguaje):</b>
 * <ul>
 * <li>
 * Borrar Todos los Registros (Drop Data
 * Base):{@link maf.bdmem.BDEnMemoria#borrarTodo() borrarTodo()}
 * </li>
 * </ul>
 * </li>
 * <li><b>Comandos DML (Data Manipulation Lenguaje):</b>
 * <ul>
 * <li>
 * Alta (insert):
 * {@link maf.bdmem.BDEnMemoria#insertar(Object) insertar(Object)}
 * </li>
 *
 * <li>
 * Baja (delete):
 * {@link maf.bdmem.BDEnMemoria#borrar(Object, int) borrar(Object,int)}
 * </li>
 *
 * <li>
 * Modificación (update):
 * {@link maf.bdmem.BDEnMemoria#modificar(Object, int) modificar(Object,int)}
 * </li>
 * <li>
 * Buscar (search):
 * {@link maf.bdmem.BDEnMemoria#buscar(Object, int) buscar(Object,int)}
 * </li>
 * <li>
 * Comandos de Selección (select):
 * <ul>
 * <li>{@link maf.bdmem.BDEnMemoria#seleccionar(String, int) seleccionar(String,int)}</li>
 * <li>{@link maf.bdmem.BDEnMemoria#seleccionarTodos(String) seleccionarTodos(String)}</li>
 * <li>{@link maf.bdmem.BDEnMemoria#seleccionar(String) seleccionar(String)}</li>
 * </ul>
 * </li>
 * </ul>
 * </li>
 * <li><b>Comandos para estadísticas:</b>
 * <ul>
 * <li>
 * N° de Registros
 * ingresados:{@link maf.bdmem.BDEnMemoria#getNumeroDeRegistros() getNumeroDeRegistros()}
 * </li>
 * <li>
 * N° de Tablas
 * registradas:{@link maf.bdmem.BDEnMemoria#getNumeroDeTablas() getNumeroDeTablas()}
 * </li>
 * </ul>
 * </li>
 * </ul>
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class BDEnMemoria {

    //<editor-fold defaultstate="collapsed" desc="Variables y Constantes de tipo "static">
    public static final String OPERACION_OK = "La operación se completó "
            + "con exito";
    public static final String OPERACION_ERROR = "La operación no se "
            + "completó con exito";
    public static final String OPERACION_INVALIDA = "La operación es inválida";
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * Instancia única de la Base de Datos
     *
     * @see maf.bdmem.BDEnMemoria
     */
    public static BDEnMemoria instancia = null;
    /**
     * ArrayList que contiene todos los datos de la Base de Datos
     *
     * @see maf.bdmem.BDEnMemoria
     */
    ArrayList<Registro> metaBaseDeDatos;
    /**
     * ArrayList que simula, pero sin MetaDatos un ResultSet
     *
     * @see java.sql.ResultSet
     */
    ArrayList<Registro> metaResultSet;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**
     * Crea una instancia única de la Base de Datos en Memoria
     *
     * @see maf.bdmem.BDEnMemoria
     * @return Una nueva instancia de la Base de Datos en Memoria
     */
    public static BDEnMemoria conectar() {
        if (BDEnMemoria.instancia == null) {
            BDEnMemoria.instancia = new BDEnMemoria();
        }
        return BDEnMemoria.instancia;
    }

    /**
     * Crea e inicializa una instancia de la Base de Datos en Memoria
     *
     * @see maf.bdmem.BDEnMemoria
     */
    private BDEnMemoria() {
        this.metaBaseDeDatos = new ArrayList<Registro>();
        this.metaResultSet = new ArrayList<Registro>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Elimina todos los registros de la Base de Datos en Memoria, destruyendo
     * la referencia a los datos
     *
     * @see maf.bdmem.BDEnMemoria
     */
    public void borrarTodo() {
        this.metaBaseDeDatos = null;
    }

    /**
     * Inserta un Objeto a la Base de Datos en Memoria
     *
     * @see maf.bdmem.BDEnMemoria
     * @param obj Es el objeto a insertar en la Base de Datos en Memoria
     * @return Retorna una cadena indicando el resultado de la operación
     */
    public String insertar(Object obj) {
        if (metaBaseDeDatos == null || metaBaseDeDatos.isEmpty()) {
            metaBaseDeDatos = new ArrayList<>();
        }
        Registro r = new Registro();
        r.setODatos(obj);
        r.setId(metaBaseDeDatos.size());
        r.setSNombreTabla(String.valueOf(obj.getClass().getSimpleName().toLowerCase()));
        metaBaseDeDatos.add(r);
        return BDEnMemoria.OPERACION_OK + " (" + "Insert de " + obj.getClass().getSimpleName() + ")";
    }

    /**
     * Borra un Objeto de la Base de Datos en Memoria
     *
     * @see maf.bdmem.BDEnMemoria
     * @param obj Es el objeto a borrar de la Base de Datos en Memoria
     * @param id Es el identificador del objeto a borrar de la Base de Datos en
     * Memoria
     * @return Retorna una cadena indicando el resultado de la operación
     */
    public String borrar(Object obj, int id) {
        Registro r = this.buscar(obj, id);
        if (r != null) {
            this.metaBaseDeDatos.remove(r.getId());
            return BDEnMemoria.OPERACION_OK + " (" + "DELETE de " + obj.getClass().getSimpleName() + ")";
        }
        return BDEnMemoria.OPERACION_ERROR + " (" + "DELETE de " + obj.getClass().getSimpleName() + ")";
    }

    /**
     * Modifica un Objeto de la Base de Datos en Memoria
     *
     * @see maf.bdmem.BDEnMemoria
     * @param obj Es el objeto a modificar de la Base de Datos en Memoria
     * @param id Es el identificador del objeto a modificar de la Base de Datos
     * en Memoria
     * @return Retorna una cadena indicando el resultado de la operación
     */
    public String modificar(Object obj, int id) {
        Registro r = this.buscar(obj, id);
        if (r != null) {
            this.metaBaseDeDatos.set(r.getId(), r);
            return BDEnMemoria.OPERACION_OK + " (" + "UPDATE de " + obj.getClass().getSimpleName() + ")";
        }
        return BDEnMemoria.OPERACION_ERROR + " (" + "UPDATE de " + obj.getClass().getSimpleName() + ")";
    }

    /**
     * Busca un Objeto de la Base de Datos en Memoria
     *
     * @param obj Es el objeto a buscar de la Base de Datos en Memoria
     * @param id Es el identificador del objeto a buscar de la Base de Datos en
     * Memoria
     * @return Retorna un objeto de tipo {@link maf.bdmem.Registro}
     * @see maf.bdmem.BDEnMemoria
     * @see maf.bdmem.Registro
     */
    private Registro buscar(Object obj, int id) {
        Registro r = new Registro();
        r.setODatos(obj);
        r.setId(id);
        r.setSNombreTabla(String.valueOf(obj.getClass().getSimpleName().toLowerCase()));
        if (metaBaseDeDatos != null && !metaBaseDeDatos.isEmpty()) {
            for (int i = 0; i < metaBaseDeDatos.size(); i++) {
                if (metaBaseDeDatos.get(i).equals(r)) {
                    return r;
                }
            }
        }
        return null;
    }

    /**
     * Busca un Objeto de la Base de Datos en Memoria, usando el nombre de una
     * tabla
     *
     * @see maf.bdmem.BDEnMemoria
     * @param sNombreTabla Es una cadena que representa el nombre de la tabla (o
     * grupo) de tuplas
     * @param id Es el identificador del objeto a buscar de la Base de Datos en
     * Memoria
     * @return Retorna un objeto de tipo {@link Object}
     */
    public Object seleccionar(String sNombreTabla, int id) {
        Registro r = new Registro();
        r.setODatos(null);
        r.setSNombreTabla(sNombreTabla.toLowerCase());
        r.setId(id);
        if (metaBaseDeDatos != null && !metaBaseDeDatos.isEmpty()) {
            for (int i = 0; i < metaBaseDeDatos.size(); i++) {
                if (metaBaseDeDatos.get(i).equals(r)) {
                    return metaBaseDeDatos.get(i).getODatos();
                }
            }
        }
        return null;
    }

    /**
     * Busca todos los Objetos de la Base de Datos en Memoria, usando el nombre
     * de una tabla
     *
     * @see maf.bdmem.BDEnMemoria
     * @see maf.bdmem.Registro
     * @param sNombreTabla Es una cadena que representa el nombre de la tabla (o
     * grupo) de tuplas
     * @return Retorna un ArrayList con objetos de tipo
     * {@link maf.bdmem.Registro}
     */
    public ArrayList<Registro> seleccionarTodos(String sNombreTabla) {
        this.metaResultSet = null;
        this.metaResultSet = new ArrayList<Registro>();
        if (metaBaseDeDatos != null && !metaBaseDeDatos.isEmpty()) {
            for (int i = 0; i < metaBaseDeDatos.size(); i++) {
                Registro r = new Registro();
                r.setSNombreTabla(sNombreTabla.toLowerCase());
                if (metaBaseDeDatos.get(i).getSNombreTabla().equals(r.getSNombreTabla())) {
                    r.setODatos(metaBaseDeDatos.get(i).getODatos());
                    r.setId(metaBaseDeDatos.get(i).getId());
                    this.metaResultSet.add(r);
                }
            }
            return this.metaResultSet;
        }
        return null;
    }

    /**
     * Busca todos los Objetos de la Base de Datos en Memoria, usando el nombre
     * de una tabla
     *
     * @see maf.bdmem.BDEnMemoria
     * @param sNombreTabla Es una cadena que representa el nombre de la tabla (o
     * grupo) de registros.
     * @return Retorna un ArrayList con objetos de tipo {@link Object}
     */
    public ArrayList<Object> seleccionar(String sNombreTabla) {
        ArrayList<Object> ret = new ArrayList<Object>();
        if (metaBaseDeDatos != null && !metaBaseDeDatos.isEmpty()) {
            for (int i = 0; i < metaBaseDeDatos.size(); i++) {
                Registro r = new Registro();
                r.setSNombreTabla(sNombreTabla.toLowerCase());
                if (metaBaseDeDatos.get(i).getSNombreTabla().equals(r.getSNombreTabla())) {
                    ret.add(metaBaseDeDatos.get(i).getODatos());
                }
            }
            return ret;
        }
        return null;
    }

    /**
     * Indica la cantidad de objetos de tipo {@link maf.bdmem.Registro}
     * insertados en la Base de Datos en Memoria.
     *
     * @return Retorna un entero con el número total de objetos en la Base de
     * Datos.
     */
    public int getNumeroDeRegistros() {
        return this.metaBaseDeDatos.size();
    }

    /**
     * Indica la cantidad de tablas en la que se organizan los objetos de tipo
     * {@link maf.bdmem.Registro} insertados en la Base de Datos en Memoria.
     *
     * @return Retorna un entero con el número total de tablas diferentes en la
     * Base de Datos.
     */
    public int getNumeroDeTablas() {
        ArrayList<String> sNombreTablas = new ArrayList<String>();

        if (metaBaseDeDatos != null && !metaBaseDeDatos.isEmpty()) {
            for (int i = 0; i < metaBaseDeDatos.size(); i++) {
                if (sNombreTablas.isEmpty() || !sNombreTablas.contains(metaBaseDeDatos.get(i).getSNombreTabla())) {
                    sNombreTablas.add(metaBaseDeDatos.get(i).getSNombreTabla());
                }
            }
        }
        return sNombreTablas.size();
    }
    //</editor-fold>

    /**
     * Método que convierte un grupo de Registros en una tabla
     * @param tabla Tabla que mostrará los resultados
     * @param registros Registros de tipo {@link maf.bdmem.Registro}, con los 
     * datos a mostrar.
     */
    public static void registrosATabla(JTable tabla, ArrayList<Registro> registros) {
        //Para establecer el modelo al JTable
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        int cantidadColumnas = registros.get(0).getNumeroColumnas();

        //Establecer como cabezeras el nombre de las colimnas
        for (int i = 0; i < cantidadColumnas; i++) {
            modelo.addColumn(registros.get(0).getNombreColumna(i));
        }

        for (Registro r : registros) {
            Object[] fila = new Object[cantidadColumnas];
            for (int i = 0; i < cantidadColumnas; i++) {
                fila[i] = r.getValorObjeto(i);

            }
            modelo.addRow(fila);
        }
    }
}
