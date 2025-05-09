/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.calculadora.modelo;

import com.poo.calculadora.operaciones.Operacion;

/**
 *
 * @author yoos
 */

/**
* Encapsular el estado y la lógica de la calculadora (Controlador/Modelo en MVC)
*/
public class ModeloCalculadora {
    private double numero1;
    private double numero2;
    private Operacion actualOperacion;
    private boolean nuevaOperacion;

    public ModeloCalculadora() {
        limpiar();
    }

    public void limpiar() {
        numero1 = 0;
        numero2 = 0;
        actualOperacion = null;
        nuevaOperacion = true;
    }

    public String digitoEntrada(String texto, String entrada) {
        if (nuevaOperacion) {
            nuevaOperacion = false;
            return entrada;
        }
        return texto.equals("0") ? entrada : texto + entrada;
    }

    public String entradaDecimal(String texto) {
        if (!texto.contains(".")) {
            nuevaOperacion = false;
            return texto + ".";
        }
        return texto;
    }

    public void setOperacion(Operacion op, String texto) {
        numero1 = Double.parseDouble(texto);
        actualOperacion = op;
        nuevaOperacion = true;
    }

    public String calcular(String texto) {
        if (actualOperacion != null) {
            numero2 = Double.parseDouble(texto);
            double resultado = actualOperacion.calcular(numero1, numero2);
            nuevaOperacion = true;
            actualOperacion = null;
            return String.valueOf(resultado);
        }
        return texto;
    }
}
