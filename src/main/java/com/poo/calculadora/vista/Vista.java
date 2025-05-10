/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.calculadora.vista;

import com.poo.calculadora.modelo.ModeloCalculadora;
import com.poo.calculadora.operaciones.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    private final Map<String, Operacion> operaciones;

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
        operaciones = new HashMap<>();
        inicializarOps();

        JPanel panelDisplay = new JPanel(new GridLayout(2, 1));
        panelDisplay.setBackground(Color.DARK_GRAY);
        panelDisplay.setBorder(new EmptyBorder(20, 20, 10, 20));

        displayOperacion = new JTextField();
        displayOperacion.setEditable(false);
        displayOperacion.setFont(new Font("Arial", Font.PLAIN, 18));
        displayOperacion.setHorizontalAlignment(JTextField.RIGHT);
        displayOperacion.setForeground(Color.LIGHT_GRAY);
        displayOperacion.setBackground(Color.DARK_GRAY);
        displayOperacion.setBorder(null);

        displayPrincipal = new JTextField("0");
        displayPrincipal.setEditable(false);
        displayPrincipal.setFont(new Font("Arial", Font.BOLD, 36));
        displayPrincipal.setHorizontalAlignment(JTextField.RIGHT);
        displayPrincipal.setForeground(Color.WHITE);
        displayPrincipal.setBackground(Color.DARK_GRAY);
        displayPrincipal.setBorder(null);

        panelDisplay.add(displayOperacion);
        panelDisplay.add(displayPrincipal);
        add(panelDisplay, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(5, 4, 10, 10));
        panelBotones.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelBotones.setBackground(Color.DARK_GRAY);

        String[] botones = {
            "AC", "^", "√", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", "%", ",", "="
        };
        
        for (String texto : botones) {
            JButton btn = crearBoton(texto);
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(180, 180, 180));
                } else {
                    g.setColor(getBackground());
                }
                g.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getBackground().darker());
                g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            }
        };
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(30, 33, 41));
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(60, 60));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setVerticalAlignment(SwingConstants.CENTER);
        btn.addActionListener(this);
        return btn;
    }

    private void inicializarOps() {
        operaciones.put("/", new Dividir());
        operaciones.put("x", new Multiplicar());
        operaciones.put("%", new Porcentaje());
        operaciones.put("^", new Potencia());
        operaciones.put("√", new Raiz());
        operaciones.put("-", new Resta());
        operaciones.put("+", new Suma());
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

                    Operacion op = operaciones.get(operador);
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
                if (operaciones.containsKey(entrada)) {
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
