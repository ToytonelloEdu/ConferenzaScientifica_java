package GUI_classes;

import Business_Logic.AddInstance_controller;
import Business_Logic.Controller;
import Model_classes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CF_NewSessioneFrame extends JFrame {
    private Controller business_logic;
    private AddInstance_controller AddInst_bl;
    private JPanel MainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox<Locazione> comboBox3;
    private JComboBox<Utente> comboBox4;
    private JComboBox<Partecipante> comboBox5;
    private JButton interventoButton;
    private JButton eventoSocialeButton;
    private JButton pausaButton;
    private JLabel label1;
    private JLabel nuovoLabel;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField textField3;
    private JTextField textField4;
    private JList<Evento> list1;
    private JTextField textField5;
    private JComboBox<Partecipante> Interv_comboBox;
    private JComboBox<String> tipoP_comboBox;
    private JComboBox<String> tipoES_comboBox;
    private JTextField textField6;
    private JPanel eventoJPanel;
    private JPanel DataInsertJPanel;
    private JButton annullaButton;
    private JButton confermaButton;
    private JLabel intervLabel;

    public CF_NewSessioneFrame(Controller c, AddInstance_controller aic){
        business_logic = c;
        AddInst_bl = aic;
        AddInst_bl.setNewSessioneFrame(this);
        setContentPane(MainPanel);
        setTitle("Nuova Sessione");
        setBounds(750, 75, 500, 550);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        eventoJPanel.setVisible(false);
        setVisible(false);

        interventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInst_bl.interventoButton_clicked();
            }
        });
        eventoSocialeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInst_bl.eventoSocialeButton_clicked();
            }
        });
        pausaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInst_bl.pausaButton_clicked();
            }
        });
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInst_bl.NewSess_AnnullaButton_clicked();
            }
        });
    }
    public JComboBox<Locazione> getComboBox3() {
        return comboBox3;
    }

    public JPanel getEventoJPanel() {
        return eventoJPanel;
    }

    public JButton getInterventoButton() {
        return interventoButton;
    }

    public JButton getEventoSocialeButton() {
        return eventoSocialeButton;
    }

    public JButton getPausaButton() {
        return pausaButton;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public JComboBox<Utente> getComboBox4() {
        return comboBox4;
    }

    public JComboBox<Partecipante> getComboBox5() {
        return comboBox5;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public JTextField getTextField4() {
        return textField4;
    }

    public JList<Evento> getList1() {
        return list1;
    }

    public JTextField getTextField5() {
        return textField5;
    }

    public JComboBox<Partecipante> getInterv_comboBox() {
        return Interv_comboBox;
    }

    public JComboBox<String> getTipoP_comboBox() {
        return tipoP_comboBox;
    }

    public JComboBox<String> getTipoES_comboBox() {
        return tipoES_comboBox;
    }

    public JTextField getTextField6() {
        return textField6;
    }
}
