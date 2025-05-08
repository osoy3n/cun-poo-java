/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.poo.calculadora;

import com.poo.calculadora.vista.Vista;
import javax.swing.SwingUtilities;

/**
 *
 * @author yoos
 */
public class Calculadora {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Vista::new);
    }
}
