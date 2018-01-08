/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vista.mostrar;
import modelo.consola;

/**
 *
 * @author degana
 */
public class controlador implements ActionListener, MouseListener {

    private mostrar vistaMostrar;
    consola modelo = new consola();

    public controlador(mostrar vistaMostrar) {
        this.vistaMostrar = vistaMostrar;
    }

    public enum AccionMVC {
        _menuMostrar
    }

    public void iniciar() {
        try {
            vistaMostrar.setVisible(true);
        } catch (Exception ex) {
        }
        //
        this.vistaMostrar.botonBuscar.setActionCommand("_menuMostrar");
        this.vistaMostrar.botonBuscar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (AccionMVC.valueOf(e.getActionCommand())) {
            case _menuMostrar: {
                try {
                    this.vistaMostrar.jTable1.setModel(this.modelo.MostrarEmp(this.vistaMostrar.boxIndicador.getSelectedItem().toString()));
                    System.out.print("Opcion: " + this.vistaMostrar.boxIndicador.getSelectedItem());
                } catch (IOException ex) {
                    Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            try {
                this.vistaMostrar.mayorVariacion.setText(this.modelo.encontrarMayor(this.vistaMostrar.boxIndicador.getSelectedItem().toString()));
            } catch (IOException ex) {
                Logger.getLogger(controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            break;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e
    ) {
    }

    @Override
    public void mousePressed(MouseEvent e
    ) {
    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {
    }

    @Override
    public void mouseExited(MouseEvent e
    ) {
    }
}
