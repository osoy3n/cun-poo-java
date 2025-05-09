/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.calculadora.operaciones;

/**
 *
 * @author yoos
 */
public class Porcentaje extends Operacion {
    @Override
    public String getSymbol() {
        return "%";
    }

    @Override
    public double calcular(double numero1, double numero2) {
        return (numero1 * numero2) / 100;
    }

}
