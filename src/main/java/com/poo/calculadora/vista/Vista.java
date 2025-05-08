/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.calculadora.vista;

import com.poo.calculadora.modelo.ModeloCarculadora;
import com.poo.calculadora.operaciones.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yoos
 */

/**
 * UI (Vista) para la calculadora.
 */
public class Vista extends JFrame implements ActionListener {
    private final JTextField display;
    private final ModeloCarculadora calculadora;
    private final Map<String, Operacion> operacionesMap;

    public Vista() {
        setTitle("Calculadora");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        calculadora = new ModeloCarculadora();
        operacionesMap = new HashMap<>();
        initOperations();

        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6, 4, 5, 5));
        String[] buttons = {
            "AC", "+/-", "%", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", "", ",", "=",
            "^", "√", "", ""
        };
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.PLAIN, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initOperations() {
        operacionesMap.put("+", new Suma());
        operacionesMap.put("-", new Resta());
        operacionesMap.put("x", new Multiplicar());
        operacionesMap.put("/", new Dividir());
        operacionesMap.put("^", new Potencia());
        operacionesMap.put("√", new Raiz());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();
        String text = display.getText();
        switch (input) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" ->
                display.setText(calculadora.digitoEntrada(text, input));
            case "," -> display.setText(calculadora.entradaDecimal(text));
            case "AC" -> {
                calculadora.limpiar();
                display.setText("0");
            }
            case "+/-" -> display.setText(calculadora.cambiarSigno(text));
            case "%" -> display.setText(calculadora.porcentaje(text));
            case "=" -> display.setText(calculadora.calcular(text));
            default -> {
                if (operacionesMap.containsKey(input)) {
                    calculadora.setOperacion(operacionesMap.get(input), text);
                }
            }
        }
    }
}
