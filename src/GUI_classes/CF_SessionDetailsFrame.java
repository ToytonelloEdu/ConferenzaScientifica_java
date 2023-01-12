package GUI_classes;

import Business_Logic.Controller;
import Model_classes.Evento;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CF_SessionDetailsFrame extends JFrame {
    Controller business_logic;
    DefaultListModel<String> dlModel = new DefaultListModel<>();
    List<? extends Evento> currentEventoList;
    private JPanel MainPanel;
    private JPanel TitlePanel;
    private JLabel SessTitle_Label;
    private JButton closeButton;
    private JList<String> Eventi_JList;
    private JLabel InizioSessione_label;
    private JTextArea InizioSessione_textArea;
    private JTextArea FineSessione_textArea;
    private JLabel FineSessione_label;
    private JTextArea Locazione_textArea;
    private JLabel Chair_label;
    private JTextArea Chair_textArea;
    private JLabel KeynoteSpeaker_label;
    private JTextArea KeynoteSpeaker_textArea;
    private JLabel Locazione_label;
    private JPanel Descrizione_JPanel;
    private JLabel Descrizione_Label;
    private JTextArea Descrizione_textArea;


    public CF_SessionDetailsFrame(Controller c){
        business_logic = c;
        CF_SessionDetailsFrame FrameHolder = sessionDetailFrame_setup();
        setVisible(false);


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameHolder.setVisible(false);
            }
        });
        Eventi_JList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                business_logic.EventoList_SelectedItem_changed();
            }
        });
    }

    private CF_SessionDetailsFrame sessionDetailFrame_setup() {
        setTitle("Session Details");
        CF_SessionDetailsFrame FrameHolder = this;
        setContentPane(MainPanel);
        setBounds(1400, 50, 500, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Eventi_JList.setModel(dlModel);
        Descrizione_textArea.setLineWrap(true);
        Descrizione_textArea.setWrapStyleWord(true);

        return FrameHolder;
    }


    public JLabel getSessTitle_Label() {
        return SessTitle_Label;
    }

    public JTextArea getInizioSessione_textArea() {
        return InizioSessione_textArea;
    }

    public JTextArea getFineSessione_textArea() {
        return FineSessione_textArea;
    }

    public JTextArea getLocazione_textArea() {
        return Locazione_textArea;
    }

    public JTextArea getChair_textArea() {
        return Chair_textArea;
    }

    public JTextArea getKeynoteSpeaker_textArea() {
        return KeynoteSpeaker_textArea;
    }

    public DefaultListModel<String> getListModel() {
        return dlModel;
    }

    public List<? extends Evento> getCurrentEventoList() {
        return currentEventoList;
    }

    public void setCurrentEventoList(List<? extends Evento> currentEventoList) {
        this.currentEventoList = currentEventoList;
    }

    public JLabel getDescrizione_Label() {
        return Descrizione_Label;
    }

    public JTextArea getDescrizione_textArea() {
        return Descrizione_textArea;
    }

    public JPanel getDescrizione_JPanel() {
        return Descrizione_JPanel;
    }

    public JList<String> getEventi_JList() {
        return Eventi_JList;
    }
}
