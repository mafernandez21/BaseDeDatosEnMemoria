/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maf.prueba.uso;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
        
        JFrame f=new JFrame("Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p=new JPanel();
        p.setBackground(Color.red);
        JTable t=new JTable();
        JScrollPane s=new JScrollPane();
        
        BDEnMemoria.registrosATabla(t, BDEnMemoria.conectar().seleccionarTodos("Cliente"));
        
        s.setViewportView(t);
        p.add(s);
        f.add(p);
        f.setVisible(true);
        //f.pack();
    }
}
