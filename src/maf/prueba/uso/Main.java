/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.prueba.uso;

import maf.bdmem.BDEnMemoria;

/**
 *
 * @author Martin Alejandro
 */
public class Main {
    
    public static void main(String[] args) {

        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        
        c1.setNombre("Don Pepito");
        c2.setNombre("Don José");
        
        BDEnMemoria.conectar().insertar(c1);
        BDEnMemoria.conectar().insertar(c2);

        System.out.println(BDEnMemoria.conectar().seleccionar("Cliente", 0));
        System.out.println(BDEnMemoria.conectar().seleccionar("Cliente"));
    }
}
