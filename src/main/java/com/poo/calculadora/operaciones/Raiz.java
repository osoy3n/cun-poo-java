/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.calculadora.operaciones;

/**
 *
 * @author yoos
 */
public class Raiz extends Operacion {
    @Override
    public String getSymbol() {
        return "√";
    }
    
    @Override
    public double calcular(double a, double b) {
        if (b == 0) throw new ArithmeticException("El grado de la raíz no puede ser 0");
        return Math.pow(a, 1.0 / b);
    }
}
