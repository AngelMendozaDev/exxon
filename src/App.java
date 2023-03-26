import java.util.ArrayList;
import java.util.EventListener;

import java.awt.Color;

import javax.sound.midi.SysexMessage;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System.Logger;

import javax.swing.border.Border;

import com.itextpdf.text.DocumentException;

public class App extends JFrame implements EventListener {
    String productos[] = { "Aceite sintetico", "Diesel", "Gasolina 87 Octanos", "Gas Licuado",
            "Aceite extendedor Arafinico" };
    int cantidades[] = { 2500, 2820, 3500, 4000, 5000 };
    int precios[] = { 25, 9, 6, 10, 13 };
    String compania[] = { "CHEVROLET(EU)", "DINA (MX)", "CONSTRUCCIÓN COLOSING (CAN)", "HOGE MOTOR COMPANY (EN)",
            "SONY (JP)", "SAMSUNG (KO)", "TRANSPORTES BOATY´S (EU)" };

    String nameCom = "";

    JPanel negro, espace2;
    JLabel etiquetax;
    JButton pedido, stock;

    JRadioButton btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    JTextField cantidad;
    JComboBox catalogo;
    JTextField total;
    JButton agregar, factura;

    JButton abastecer1, abastecer2, abastecer3, abastecer4, abastecer5;

    ArrayList pedidos = new ArrayList();

    int i = 0;
    int tempTotal = 0;

    public App() {
        int x = 0;

        setLayout(null);
        setBounds(0, 0, 750, 300);
        setTitle(("Equipo4 EXXON"));
        setLocationRelativeTo(null);

        x = this.getWidth();
        negro = new JPanel();
        negro.setBackground(Color.BLACK);
        negro.setLayout(null);
        negro.setForeground(Color.WHITE);
        negro.setBounds(0, 0, x, 30);
        add(negro);

        etiquetax = new JLabel("Gestion");
        etiquetax.setBounds(2, 1, 100, 20);
        etiquetax.setForeground(Color.WHITE);
        negro.add(etiquetax);

        etiquetax = new JLabel("EXXON");
        x = negro.getWidth() - 100;
        etiquetax.setBounds(x, 2, 100, 20);
        etiquetax.setForeground(Color.WHITE);
        Font fuente = new Font("Arial", Font.BOLD, 15);
        etiquetax.setFont(fuente);
        negro.add(etiquetax);

        pedido = new JButton("Generar Pedido");
        pedido.setBounds(5, 35, 150, 35);
        pedido.setCursor(new Cursor(HAND_CURSOR));
        pedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarPedido();
            }
        });
        add(pedido);

        stock = new JButton("Abastecer Stock");
        stock.setBounds(5, 80, 150, 35);
        stock.setCursor(new Cursor(HAND_CURSOR));
        stock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abastecerStock();
            }
        });
        add(stock);

        espace2 = new JPanel();
        espace2.setBounds(170, 32, 560, 225);
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        espace2.setBorder(blackline);
        espace2.setLayout(null);
        add(espace2);

    }

    public static void main(String[] args) {
        App ventana = new App();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void abastecerStock() {
        espace2.removeAll();
        /*********************************************************
         * Abastecer Stock
         **********************************************************************/
        this.etiquetax = new JLabel("Cantidad");
        this.etiquetax.setBounds(180, 10, 100, 15);
        this.espace2.add(etiquetax);

        int y = 20;

        for (i = 0; i < productos.length; i++) {
            y += 18;
            etiquetax = new JLabel(productos[i]);
            etiquetax.setBounds(5, y, 200, 15);
            espace2.add(etiquetax);

            etiquetax = new JLabel("" + cantidades[i]);
            etiquetax.setBounds(208, y, 100, 15);
            espace2.add(etiquetax);
        }

        abastecer1 = new JButton("Abastecer");
        abastecer1.setBounds(308, 38, 100, 15);
        abastecer1.setCursor(new Cursor(HAND_CURSOR));
        abastecer1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pide(1);
            }
        });
        espace2.add(abastecer1);

        abastecer2 = new JButton("Abastecer");
        abastecer2.setBounds(308, 56, 100, 15);
        abastecer2.setCursor(new Cursor(HAND_CURSOR));
        abastecer2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pide(2);
            }
        });
        espace2.add(abastecer2);

        abastecer3 = new JButton("Abastecer");
        abastecer3.setBounds(308, 74, 100, 15);
        abastecer3.setCursor(new Cursor(HAND_CURSOR));
        abastecer3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pide(3);
            }
        });
        espace2.add(abastecer3);

        abastecer4 = new JButton("Abastecer");
        abastecer4.setBounds(308, 92, 100, 15);
        abastecer4.setCursor(new Cursor(HAND_CURSOR));
        abastecer4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pide(4);
            }
        });
        espace2.add(abastecer4);

        abastecer5 = new JButton("Abastecer");
        abastecer5.setBounds(308, 110, 100, 15);
        abastecer5.setCursor(new Cursor(HAND_CURSOR));
        abastecer5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pide(5);
            }
        });
        espace2.add(abastecer5);
        espace2.updateUI();
    }

    public void generarPedido() {
        espace2.removeAll();
        /*********************************************************
         * Generar pedido
         **********************************************************************/
        ButtonGroup grupo1 = new ButtonGroup();

        btn1 = new JRadioButton(compania[0]);
        btn1.setBounds(5, 10, 200, 15);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameCom = btn1.getText();
            }
        });
        grupo1.add(btn1);
        espace2.add(btn1);

        btn2 = new JRadioButton(compania[1]);
        btn2.setBounds(205, 10, 100, 15);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameCom = btn2.getText();
            }
        });
        grupo1.add(btn2);
        espace2.add(btn2);

        btn3 = new JRadioButton(compania[2]);
        btn3.setBounds(310, 10, 240, 15);
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameCom = btn3.getText();
            }
        });
        grupo1.add(btn3);
        espace2.add(btn3);

        btn4 = new JRadioButton(compania[3]);
        btn4.setBounds(5, 40, 200, 15);
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameCom = btn4.getText();
            }
        });
        grupo1.add(btn4);
        espace2.add(btn4);

        btn5 = new JRadioButton(compania[4]);
        btn5.setBounds(205, 40, 100, 15);
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameCom = btn5.getText();
            }
        });
        grupo1.add(btn5);
        espace2.add(btn5);

        btn6 = new JRadioButton(compania[5]);
        btn6.setBounds(310, 40, 200, 15);
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameCom = btn6.getText();
            }
        });
        grupo1.add(btn6);
        espace2.add(btn6);

        btn7 = new JRadioButton(compania[6]);
        btn7.setBounds(5, 70, 200, 15);
        btn7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameCom = btn7.getText();
            }
        });
        grupo1.add(btn7);
        espace2.add(btn7);

        catalogo = new JComboBox();
        catalogo.setBounds(5, 120, 180, 20);
        catalogo.addItem("Catalogo");
        for (i = 0; i < productos.length; i++) {
            catalogo.addItem(productos[i]);
        }
        catalogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (catalogo.getSelectedIndex() > 0) {
                    cantidad.setEditable(true);
                    agregar.setEnabled(true);
                } else {
                    cantidad.setEditable(false);
                    agregar.setEnabled(false);
                }
            }
        });
        espace2.add(catalogo);

        etiquetax = new JLabel("Cantidad:");
        etiquetax.setBounds(10, 150, 100, 20);
        espace2.add(etiquetax);

        cantidad = new JTextField();
        cantidad.setBounds(90, 150, 100, 20);
        cantidad.setEditable(false);
        espace2.add(cantidad);

        agregar = new JButton("Agregar");
        agregar.setBounds(250, 150, 80, 20);
        agregar.setCursor(new Cursor(HAND_CURSOR));
        agregar.setEnabled(false);
        agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code Event
                int aux = 0;
                int importe = 0;

                if (!cantidad.getText().isEmpty()) {
                    aux = Integer.parseInt(cantidad.getText());
                    if (cantidades[catalogo.getSelectedIndex() - 1] >= aux) {
                        cantidades[catalogo.getSelectedIndex() - 1] = cantidades[catalogo.getSelectedIndex() - 1] - aux;
                        importe = precios[catalogo.getSelectedIndex() - 1] * aux;
                        tempTotal += importe;

                        pedidos.add(
                                catalogo.getSelectedItem().toString() + "\t\t" + cantidad.getText() + "\t\t" + importe);

                        if (cantidades[catalogo.getSelectedIndex() - 1] <= 1000) {
                            JOptionPane.showMessageDialog(null, "Producto casi agotado, \n Adquiera productos");
                        }
                        cantidad.setText("");
                        catalogo.setSelectedIndex(0);
                        agregar.setEnabled(false);
                        total.setText("" + tempTotal);
                    }
                }
            }
        });
        espace2.add(agregar);

        factura = new JButton("Factura");
        factura.setBounds(355, 150, 80, 20);
        factura.setCursor(new Cursor(HAND_CURSOR));
        factura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code Event
                Pdf pdf = new Pdf();
                try {
                    pdf.createFactura(nameCom, pedidos, nameCom);
                } catch (FileNotFoundException ex) {
                    System.out.println("No se encuentra we");
                } catch (DocumentException ex) {
                    System.out.println("Hay un problema con el PDF");
                }
                factura.setEnabled(false);
                total.setText("");
                grupo1.clearSelection();
            }
        });
        espace2.add(factura);

        etiquetax = new JLabel("Total:");
        etiquetax.setBounds(10, 180, 900, 20);
        espace2.add(etiquetax);

        total = new JTextField();
        total.setBounds(90, 180, 100, 20);
        total.setEditable(false);
        espace2.add(total);

        espace2.updateUI();
    }

    public void pide(int producto) {
        int aux = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad:"));
        cantidades[producto - 1] = cantidades[producto - 1] + aux;
        abastecerStock();
    }
}
