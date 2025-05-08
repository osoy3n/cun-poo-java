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
public class ModeloCarculadora {
    private double numero1;
    private double numero2;
    private Operacion actualOperacion;
    private boolean nuevaOperacion;
    
    public ModeloCarculadora() {
        limpiar();
    }
    
    public void limpiar() {
        numero1 = 0;
        numero2 = 0;
        actualOperacion = null;
        nuevaOperacion = true;
    }
    
    public boolean esNuevaOperacion() {
        return nuevaOperacion;
    }
    
    public void setNuevaOperacion(boolean bandera) {
        nuevaOperacion = bandera;
    }
    
    public String digitoEntrada(String mostrarTexto, String digito) {
        if (nuevaOperacion) {
            nuevaOperacion = false;
            return digito;
        }
        return mostrarTexto.equals("0") ? digito : mostrarTexto + digito;
    }
    
    public String entradaDecimal(String mostrarTexto) {
        if (!mostrarTexto.contains(".")) {
            nuevaOperacion = false;
            return mostrarTexto + ".";
        }
        return mostrarTexto;
    }
    
    public String cambiarSigno(String mostrarTexto) {
        double valor = Double.parseDouble(mostrarTexto);
        return String.valueOf(-valor);
    }
    
    public String porcentaje(String mostrarTexto) {
        double valor = Double.parseDouble(mostrarTexto) / 100.0;
        return String.valueOf(valor);
    }
    
    public void setOperacion(Operacion op, String mostrarTexto) {
        numero1 = Double.parseDouble(mostrarTexto);
        actualOperacion = op;
        nuevaOperacion = true;
    }

    public String calcular(String mostrarTexto) {
        if (actualOperacion != null) {
            numero2 = Double.parseDouble(mostrarTexto);
            double resultado = actualOperacion.calcular(numero1, numero2);
            nuevaOperacion = true;
            actualOperacion = null;
            return String.valueOf(resultado);
        }
        return mostrarTexto;
    }
}
