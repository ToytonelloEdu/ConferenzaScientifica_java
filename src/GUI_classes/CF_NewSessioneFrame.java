package GUI_classes;

import Business_Logic.AddEditFrameAppearanceController;
import Business_Logic.Controller;
import Model_classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CF_NewSessioneFrame extends JFrame {
    private final Controller business_logic;
    private final AddEditFrameAppearanceController AddEdit_bl;
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
    private JPanel EventoData_JPanel;
    private JPanel BottomPanel;
    private JLabel tipologiaLabel;
    private JLabel eventoLabel;
    private JLabel inizioLabel;
    private JLabel fineLabel;
    private JLabel descrizioneLabel;
    private JLabel abstractLabel;
    private JTextField TextField1_1;
    private JTextField TextField2_1;
    private JButton aggiungiButton;
    private JButton nessunoButton;
    private JButton rimuoviButton;
    private JTextField textField0;
    private JLabel addButtonLabel;
    private final List<JComponent> EventoDataComponents = new ArrayList<>(EventoData_JPanel.getComponentCount());

    private final DefaultListModel<Evento> EvDLModel = new DefaultListModel<>();
    private String EventoSelected;

    public CF_NewSessioneFrame(Controller c, AddEditFrameAppearanceController aic){
        business_logic = c;
        AddEdit_bl = aic;
        AddEdit_bl.setNewSessioneFrame(this);
        list1.setModel(EvDLModel);
        for(Component comp : EventoData_JPanel.getComponents()){
            EventoDataComponents.add((JComponent) comp);
        }
        setContentPane(MainPanel);
        setTitle("Nuova Sessione");
        setBounds(750, 75, 550, 550);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        aggiungiButton.setEnabled(false);
        EventoData_JPanel.setVisible(false);
        setVisible(false);

        interventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.interventoButton_clicked();
            }
        });
        eventoSocialeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.eventoSocialeButton_clicked();
            }
        });
        pausaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.pausaButton_clicked();
            }
        });
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.NewSess_AnnullaButton_clicked();
            }
        });
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.NewSess_ConfermaButtonClicked();
            }
        });
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.NewSess_AggiungiButtonClicked();
            }
        });
        nessunoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.NewSess_NessunoButtonClicked();
            }
        });
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                business_logic.NewSess_RimuoviButtonClicked();
            }
        });
        textField1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                business_logic.NewSess_DateTextFieldsExited();
            }
        });
        textField2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                business_logic.NewSess_DateTextFieldsExited();
            }
        });
        TextField1_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                business_logic.NewSess_DateTextFieldsExited();
            }
        });
        TextField2_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                business_logic.NewSess_DateTextFieldsExited();
            }
        });
    }

    public DefaultListModel<Evento> getEvDLModel() {
        return EvDLModel;
    }

    public List<JComponent> getEventoDataComponents() {
        return EventoDataComponents;
    }

    public String getEventoSelected() {
        return EventoSelected;
    }

    public void setEventoSelected(String eventoSelected) {
        EventoSelected = eventoSelected;
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

    public JPanel getEventoData_JPanel() {
        return EventoData_JPanel;
    }

    public Controller getBusiness_logic() {
        return business_logic;
    }

    public JLabel getIntervLabel() {
        return intervLabel;
    }

    public JLabel getTipologiaLabel() {
        return tipologiaLabel;
    }

    public JLabel getEventoLabel() {
        return eventoLabel;
    }

    public JLabel getDescrizioneLabel() {
        return descrizioneLabel;
    }

    public JLabel getAbstractLabel() {
        return abstractLabel;
    }

    public JTextField getTextField1_1() {
        return TextField1_1;
    }

    public JTextField getTextField2_1() {
        return TextField2_1;
    }

    public JButton getNessunoButton() {
        return nessunoButton;
    }

    public JTextField getTextField0() {
        return textField0;
    }

    public JButton getAggiungiButton() {
        return aggiungiButton;
    }

    public JLabel getAddButtonLabel() {
        return addButtonLabel;
    }
}
