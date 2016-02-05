/*
 * Esta clase fué desarrollada por Martín Alejandro Fernández.
 * La edición de la presente clase, sin expresa autorización
 * no esta permitida.
 */
package maf.bdmem;

import java.util.ArrayList;

/**
 * <b>Base de Datos en Memoria:</b><br>
 * Simula una base de datos en memoria usando un {@link ArrayList} para 
 * almacenar las tuplas que son del tipo {@link maf.bdmem.Registro}<br>
 * <p>
 * <b>Modo de uso:</b><br>
 * Para poder usar la base de datos primero se deberá establecer una "conección"
 * usando el método {@link maf.bdmem.BDEnMemoria#conectar()
 * conectar} y así obtener una instancia de la base de datos para poder
 * utilizarla.<br>
 * <b>Ejemplo:</b><br>
 * <pre> 
 * {@code BDEnMemoria miBD = BDEnMemoria.conectar();
 *  int n = miBD.getNumeroDeRegistros();
 *  System.out.println(String.valueOf(n) + " registros ingresados en la Base de Datos");}
 * </pre>
 * <p>
 * <b>Comandos DDL (Data Definition Lenguaje) incluidos:</b></p>
 * <ul>
 * <li>
 * Borrar Todos los Registros (Drop Data
 * Base):{@link maf.bdmem.BDEnMemoria#borrarTodo() borrarTodo()}
 * </li>
 * </ul>
 *
 * <p>
 * <b>Comandos DML (Data Manipulation Lenguaje) incluidos:</b></p>
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
 * <li>{@link maf.bdmem.BDEnMemoria#seleccionarTodosLosRegistros(String) seleccionarTodosLosRegistros(String)}</li>
 * </ul>
 * </li>
 * </ul>
 * <p>
 * <b>Comandos para estadísticas incluidos:</b></p>
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
 *
 * @author Martín Alejandro Fernández
 * @version 1.0
 * @see <a href="mailto://mafernandez21@hotmail.com">Contacto</a>
 */
public class BDEnMemoria {

    public static final String OPERACION_OK = "La operación se completó "
            + "con exito";
    public static final String OPERACION_ERROR = "La operación no se "
            + "completó con exito";
    public static final String OPERACION_INVALIDA = "La operación es inválida";

    //<editor-fold defaultstate="collapsed" desc="Miembros">
    public static BDEnMemoria instancia = null;
    ArrayList<Registro> tuplasRegistros;
    ArrayList<Registro> resultados;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**
     * Crea una instancia unica de la Base de Datos en Memoria
     *
     * @see maf.bdmem.BDEnMemoria
     * @return Una nueva instancia de la Base de Datos en Memoria
     */
    public static final BDEnMemoria conectar() {
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
        this.tuplasRegistros = new ArrayList();
        this.resultados = new ArrayList();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Elimina todos los registros de la Base de Datos en Memoria, destruyendo
     * la referencia a las tuplas
     *
     * @see maf.bdmem.BDEnMemoria
     */
    public void borrarTodo() {
        this.tuplasRegistros = null;
    }

    /**
     * Inserta un Objeto a la Base de Datos en Memoria
     *
     * @param o Es el objeto a insertar en la Base de Datos en Memoria
     * @return Retorna una cadena indicando el resultado de la operación
     */
    public String insertar(Object o) {
        if (tuplasRegistros == null || tuplasRegistros.isEmpty()) {
            tuplasRegistros = new ArrayList<>();
        }
        Registro r = new Registro();
        r.setRegistro(o);
        r.setId(tuplasRegistros.size());
        r.setTabla(String.valueOf(o.getClass().getSimpleName().toLowerCase()));
        tuplasRegistros.add(r);
        return BDEnMemoria.OPERACION_OK + " (" + "Insert de " + o.getClass().getSimpleName() + ")";
    }

    /**
     * Borra un Objeto de la Base de Datos en Memoria
     *
     * @param o Es el objeto a borrar de la Base de Datos en Memoria
     * @param id Es el identificador del objeto a borrar de la Base de Datos en
     * Memoria
     * @return Retorna una cadena indicando el resultado de la operación
     */
    public String borrar(Object o, int id) {
        Registro r = this.buscar(o, id);
        if (r != null) {
            this.tuplasRegistros.remove(r.getId());
            return BDEnMemoria.OPERACION_OK + " (" + "DELETE de " + o.getClass().getSimpleName() + ")";
        }
        return BDEnMemoria.OPERACION_ERROR + " (" + "DELETE de " + o.getClass().getSimpleName() + ")";
    }

    /**
     * Modifica un Objeto de la Base de Datos en Memoria
     *
     * @param o Es el objeto a modificar de la Base de Datos en Memoria
     * @param id Es el identificador del objeto a modificar de la Base de Datos
     * en Memoria
     * @return Retorna una cadena indicando el resultado de la operación
     */
    public String modificar(Object o, int id) {
        Registro r = this.buscar(o, id);
        if (r != null) {
            this.tuplasRegistros.set(r.getId(), r);
            return BDEnMemoria.OPERACION_OK + " (" + "UPDATE de " + o.getClass().getSimpleName() + ")";
        }
        return BDEnMemoria.OPERACION_ERROR + " (" + "UPDATE de " + o.getClass().getSimpleName() + ")";
    }

    /**
     * Busca un Objeto de la Base de Datos en Memoria
     *
     * @param o Es el objeto a buscar de la Base de Datos en Memoria
     * @param id Es el identificador del objeto a buscar de la Base de Datos en
     * Memoria
     * @return Retorna un objeto de tipo {@link maf.bdmem.Registro}
     * @see maf.bdmem.BDEnMemoria
     * @see maf.bdmem.Registro
     */
    private Registro buscar(Object o, int id) {
        Registro r = new Registro();
        r.setRegistro(o);
        r.setId(id);
        r.setTabla(String.valueOf(o.getClass().getSimpleName().toLowerCase()));
        if (tuplasRegistros != null && !tuplasRegistros.isEmpty()) {
            for (int i = 0; i < tuplasRegistros.size(); i++) {
                if (tuplasRegistros.get(i).equals(r)) {
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
     * @param tabla Es una cadena que representa el nombre de la tabla (o grupo)
     * de tuplas
     * @param id Es el identificador del objeto a buscar de la Base de Datos en
     * Memoria
     * @return Retorna un objeto de tipo {@link Object}
     */
    public Object seleccionar(String tabla, int id) {
        Registro r = new Registro();
        r.setRegistro(null);
        r.setTabla(tabla.toLowerCase());
        r.setId(id);
        if (tuplasRegistros != null && !tuplasRegistros.isEmpty()) {
            for (int i = 0; i < tuplasRegistros.size(); i++) {
                if (tuplasRegistros.get(i).equals(r)) {
                    return tuplasRegistros.get(i).getRegistro();
                }
            }
        }
        return null;
    }

    /**
     * Busca todos los Objetos de la Base de Datos en Memoria, usando el nombre
     * de una tabla
     *
     * @param tabla Es una cadena que representa el nombre de la tabla (o grupo)
     * de tuplas
     * @return Retorna un ArrayList con objetos de tipo
     * {@link maf.bdmem.Registro}
     */
    public ArrayList<Registro> seleccionarTodos(String tabla) {
        this.resultados = null;
        this.resultados = new ArrayList();
        if (tuplasRegistros != null && !tuplasRegistros.isEmpty()) {
            for (int i = 0; i < tuplasRegistros.size(); i++) {
                Registro r = new Registro();
                r.setTabla(tabla.toLowerCase());
                if (tuplasRegistros.get(i).getTabla().equals(r.getTabla())) {
                    r.setRegistro(tuplasRegistros.get(i).getRegistro());
                    r.setId(tuplasRegistros.get(i).getId());
                    this.resultados.add(r);
                }
            }
            return this.resultados;
        }
        return null;
    }

    /**
     * Busca todos los Objetos de la Base de Datos en Memoria, usando el nombre
     * de una tabla
     *
     * @param tabla Es una cadena que representa el nombre de la tabla (o grupo)
     * de tuplas
     * @return Retorna un ArrayList con objetos de tipo {@link Object}
     */
    public ArrayList<Object> seleccionarTodosLosRegistros(String tabla) {
        ArrayList ret = new ArrayList();
        if (tuplasRegistros != null && !tuplasRegistros.isEmpty()) {
            for (int i = 0; i < tuplasRegistros.size(); i++) {
                Registro r = new Registro();
                r.setTabla(tabla.toLowerCase());
                if (tuplasRegistros.get(i).getTabla().equals(r.getTabla())) {
                    ret.add(tuplasRegistros.get(i).getRegistro());
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
        return this.tuplasRegistros.size();
    }

    /**
     * Indica la cantidad de tablas en la que se organizan los objetos de tipo
     * {@link maf.bdmem.Registro} insertados en la Base de Datos en Memoria.
     *
     * @return Retorna un entero con el número total de tablas diferentes en la
     * Base de Datos.
     */
    public int getNumeroDeTablas() {
        ArrayList<String> sTablas = new ArrayList();

        if (tuplasRegistros != null && !tuplasRegistros.isEmpty()) {
            for (int i = 0; i < tuplasRegistros.size(); i++) {
                if (sTablas.isEmpty() || !sTablas.contains(tuplasRegistros.get(i).getTabla())) {
                    sTablas.add(tuplasRegistros.get(i).getTabla());
                }
            }
        }
        return sTablas.size();
    }

    //</editor-fold>
}
