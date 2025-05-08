/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.calculadora.operaciones;

/**
 *
 * @author yoos
 */

/**
 * Abstracción para una operación de calculadora.
 */
public abstract class Operacion {
    /**
     * Símbolo que aparece en el botón.
     */
    public abstract String getSymbol();

    /**
     * Realiza la operación sobre dos operandos.
     * @param a primer operando
     * @param b segundo operando
     * @return resultado de la operación
     * @throws ArithmeticException para operaciones no válidas (por ejemplo, dividir por cero)
     */
    public abstract double calcular(double a, double b) throws ArithmeticException;
}
