package Vista;

import Controladora.ControladoraMain;
import Dao.DaoBatalla;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class FormVista extends JFrame {
    private JComboBox comboBoxAtaqueRu;
    private JComboBox comboBoxAtaqueAl;
    private JButton agregarAlEjercitoButton;
    private JButton agregarAlEjercitoButton1;
    private JButton batallarButton;
    private JComboBox comboBoxDefensaAl;
    private JComboBox comboBoxDefensaRu;
    private JTextPane textEjerAl;
    private JTextPane textEjerRu;
    private JTextPane textBatalla;
    private JTextPane textHistorial;
    private ControladoraMain control=new ControladoraMain();
    private DaoBatalla daoBatalla;

    public static void main(String[] args) {

        FormVista form = new FormVista();
        form.setVisible(true);

    }

    public FormVista() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        comboBoxAtaqueRu.addItem("Avion");
        comboBoxAtaqueRu.addItem("Cañon");
        comboBoxAtaqueRu.addItem("Fusil");
        comboBoxAtaqueRu.addItem("Tanque");
        comboBoxAtaqueAl.addItem("Avion");
        comboBoxAtaqueAl.addItem("Cañon");
        comboBoxAtaqueAl.addItem("Fusil");
        comboBoxAtaqueAl.addItem("Tanque");
        comboBoxDefensaAl.addItem("Blindaje Tanque");
        comboBoxDefensaAl.addItem("Casco");
        comboBoxDefensaAl.addItem("Chaleco");
        comboBoxDefensaRu.addItem("Blindaje Tanque");
        comboBoxDefensaRu.addItem("Casco");
        comboBoxDefensaRu.addItem("Chaleco");

        agregarAlEjercitoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                control.agregarSoldado("Ruso",comboBoxAtaqueRu.getSelectedItem().toString(),comboBoxDefensaRu.getSelectedItem().toString());

            }
        });
        agregarAlEjercitoButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                control.agregarSoldado("Aleman",comboBoxAtaqueAl.getSelectedItem().toString(),comboBoxDefensaAl.getSelectedItem().toString());

            }
        });
        batallarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                control.play();
            }
        });
    }

    public void actualizarPaneles(){

    }

    public void actualizarHistorial(ArrayList<String> lista){

    }

    public void actualizarBatalla(String s){

    }

}
