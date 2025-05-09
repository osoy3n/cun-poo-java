/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.calculadora.vista;

import com.poo.calculadora.modelo.ModeloCalculadora;
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
    private final JTextField displayPrincipal;
    private final JTextField displayOperacion;
    private final ModeloCalculadora calculadora;
    private final Map<String, Operacion> operacionesMap;

    private String numero1 = "";
    private String numero2 = "";
    private String operador = "";
    private boolean porcentajeUsado = false;

    public Vista() {
        setTitle("Calculadora");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        calculadora = new ModeloCalculadora();
        operacionesMap = new HashMap<>();
        inicializarOps();

        JPanel panelDisplay = new JPanel(new GridLayout(2, 1));
        displayOperacion = new JTextField();
        displayOperacion.setEditable(false);
        displayOperacion.setFont(new Font("Arial", Font.PLAIN, 18));
        displayOperacion.setHorizontalAlignment(JTextField.RIGHT);
        displayOperacion.setForeground(Color.GRAY);
        displayOperacion.setBorder(null);

        displayPrincipal = new JTextField("0");
        displayPrincipal.setEditable(false);
        displayPrincipal.setFont(new Font("Arial", Font.BOLD, 28));
        displayPrincipal.setHorizontalAlignment(JTextField.RIGHT);
        displayPrincipal.setBorder(null);

        panelDisplay.add(displayOperacion);
        panelDisplay.add(displayPrincipal);
        add(panelDisplay, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] botones = {
            "AC", "^", "√", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", "%", ",", "="
        };
        for (String texto : botones) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.PLAIN, 20));
            btn.addActionListener(this);
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    private void inicializarOps() {
        operacionesMap.put("/", new Dividir());
        operacionesMap.put("x", new Multiplicar());
        operacionesMap.put("%", new Porcentaje());
        operacionesMap.put("^", new Potencia());
        operacionesMap.put("√", new Raiz());
        operacionesMap.put("-", new Resta());
        operacionesMap.put("+", new Suma());
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String entrada = evento.getActionCommand();
        String texto = displayPrincipal.getText();

        switch (entrada) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                displayPrincipal.setText(calculadora.digitoEntrada(texto, entrada));
            }
            case "," -> displayPrincipal.setText(calculadora.entradaDecimal(texto));
            case "AC" -> {
                calculadora.limpiar();
                displayPrincipal.setText("0");
                displayOperacion.setText("");
                numero1 = "";
                numero2 = "";
                operador = "";
                porcentajeUsado = false;
            }
            case "=" -> {
                if (!numero1.isEmpty() && !operador.isEmpty()) {
                    numero2 = displayPrincipal.getText();
                    double n1 = Double.parseDouble(numero1.replace(",", "."));
                    double n2 = Double.parseDouble(numero2.replace(",", "."));

                    Operacion op = operacionesMap.get(operador);
                    double resultado = op.calcular(n1, n2);
                    displayOperacion.setText(numero1 + " " + operador + " " + numero2 + (porcentajeUsado ? " %" : "") + " =");
                    displayPrincipal.setText(String.valueOf(resultado));
                }
                numero1 = "";
                numero2 = "";
                operador = "";
                porcentajeUsado = false;
            }
            case "%" -> {
                porcentajeUsado = true;
                operador = "%";
            }
            default -> {
                if (operacionesMap.containsKey(entrada)) {
                    numero1 = displayPrincipal.getText();
                    operador = entrada;
                    displayOperacion.setText(numero1 + " " + operador);
                    displayPrincipal.setText("0");
                    porcentajeUsado = false;
                }
            }
        }
    }
}
