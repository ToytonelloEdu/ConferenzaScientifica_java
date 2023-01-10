package Business_Logic;

import GUI_classes.*;
import Model_classes.*;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class DetailsPanel_setter {
    Controller business_logic;
    public DetailsPanel_setter(Controller controller) {
        business_logic = controller;

    }

    public void setAllDetailsComp_visible(List<JComponent> detailsComp_list) {
        for(JComponent comp : detailsComp_list){
            comp.setVisible(true);
        }
    }

    public void setDetailPanel_onSearch(CF_MainFrame MainFrame, List Current_Main_outputList) {
        JSpinner Spinner = MainFrame.getSelection_spinner();

        Spinner.setModel(new SpinnerNumberModel(1, 1, Current_Main_outputList.size(), 1));

        int CurrentSpinnerValue = (Integer) Spinner.getValue() - 1;

        setData_onDPanel_byClass(MainFrame, Current_Main_outputList, CurrentSpinnerValue);
        MainFrame.getDetails_panel().setVisible(true);
    }

    public void setData_onDPanel_byClass(CF_MainFrame MainFrame, List Current_Main_outputList, int CurrentSpinnerValue) {
        JComboBox<String> Class_Cbox = MainFrame.getClass_comboBox();
        String Class_selected = Objects.requireNonNull((String) Class_Cbox.getSelectedItem());
        if(Class_selected.equals("Conferenza")) {
            setFields_inDetPanel_forConferenza(MainFrame, Current_Main_outputList, CurrentSpinnerValue);
        }
        else if(Class_selected.equals("Sede")){
            setFields_inDetPanel_forSede(MainFrame, Current_Main_outputList, CurrentSpinnerValue);
        }
        else if(Class_selected.equals("Utente") || Class_selected.equals("Partecipante") || Class_selected.equals("Organizzatore")){
            setFields_inDetPanel_forUtenti(MainFrame, Current_Main_outputList, CurrentSpinnerValue);
        }
        else
            MainFrame.getDetails_panel().setVisible(false);
    }

    private void setFields_inDetPanel_forUtenti(CF_MainFrame MainFrame, List Current_Main_outputList, int CurrentSpinnerValue) {
        Utente SelectedUtente = (Utente) Current_Main_outputList.get(CurrentSpinnerValue);
        SetUtenti_DetailTitle(MainFrame.getDetail_ObjectName_label(), SelectedUtente);
        SetUtenti_FirstField(MainFrame, SelectedUtente);
        SetUtenti_SecondField(MainFrame, SelectedUtente);
        SetUtenti_ThirdField(MainFrame, SelectedUtente);
        SetUtenti_FourthField(MainFrame, SelectedUtente);
        Hide_Utenti_UnusedComp(MainFrame);
    }

    private void SetUtenti_DetailTitle(JLabel detail_objectName_label, Utente selectedUtente) {
        detail_objectName_label.setText(selectedUtente.getNome() + " "+ selectedUtente.getCognome());
    }

    private void SetUtenti_FirstField(CF_MainFrame mainFrame, Utente selectedUtente) {
        mainFrame.getFirstField_label().setText("Titolo");
        mainFrame.getFirstField_outputArea().setText(selectedUtente.getTitolo());
    }

    private void SetUtenti_SecondField(CF_MainFrame mainFrame, Utente selectedUtente) {
        mainFrame.getSecondField_label().setText("Istituzione");
        mainFrame.getSecondField_outputArea().setText(selectedUtente.getIstit_afferenza().toDetailString());
    }

    private void SetUtenti_ThirdField(CF_MainFrame mainFrame, Utente selectedUtente) {
        mainFrame.getThirdField_label().setText("Ruolo");
        mainFrame.getThirdField_outputArea().setText(selectedUtente.getClass().getSimpleName());
    }

    private void SetUtenti_FourthField(CF_MainFrame mainFrame, Utente selectedUtente) {
        mainFrame.getFourthField_label().setText("email");
        mainFrame.getFourthField_outputArea().setText(selectedUtente.getEmail());
    }

    private void Hide_Utenti_UnusedComp(CF_MainFrame mainFrame) {
        mainFrame.getDetailP_sessionList().setVisible(false);
    }

    //Conferenza's details setting methods
    private void setFields_inDetPanel_forConferenza(CF_MainFrame MainFrame, List Current_Main_outputList, int CurrentSpinnerValue) {
        Conferenza SelectedConferenza = (Conferenza) Current_Main_outputList.get(CurrentSpinnerValue);
        SetConf_DetailTitle(MainFrame.getDetail_ObjectName_label(), (SelectedConferenza.getNome()));
        SetConf_FirstField(MainFrame, SelectedConferenza);
        SetConf_SecondField(MainFrame, SelectedConferenza);
        SetConf_ThirdField(MainFrame, SelectedConferenza);
        Hide_Conferenza_UnusedComp(MainFrame);
    }

    private void SetConf_DetailTitle(JLabel MainFrame, String SelectedConferenza) {
        MainFrame.setText(SelectedConferenza);
    }

    private void SetConf_FirstField(CF_MainFrame MainFrame, Conferenza SelectedConferenza) {
        MainFrame.getFirstField_label().setText("Date");
        MainFrame.getFirstField_outputArea().setText("da " + SelectedConferenza.getDataFine() + " a " + SelectedConferenza.getDataFine());
    }

    private void SetConf_SecondField(CF_MainFrame MainFrame, Conferenza SelectedConferenza) {
        MainFrame.getSecondField_label().setText("Collocazione");
        MainFrame.getSecondField_outputArea().setText(SelectedConferenza.getCollocazione().toDetailString());
    }

    private static void SetConf_ThirdField(CF_MainFrame MainFrame, Conferenza SelectedConferenza) {
        MainFrame.getThirdField_label().setText("Descrizione");
        MainFrame.getThirdField_outputArea().setText(SelectedConferenza.getDescrizione());
    }

    private void Hide_Conferenza_UnusedComp(CF_MainFrame MainFrame) {
        MainFrame.getFourthField_label().setVisible(false);
        MainFrame.getFourthField_outputArea().setVisible(false);
        MainFrame.getDetailP_sessionList().setVisible(false);
    }


    //Sede's details setting methods
    private void setFields_inDetPanel_forSede(CF_MainFrame MainFrame, List Current_Main_outputList, int CurrentSpinnerValue) {
        Sede SelectedSede = (Sede) Current_Main_outputList.get(CurrentSpinnerValue);
        MainFrame.getDetail_ObjectName_label().setText((SelectedSede.getNome()));
        SetSede_FirstField(MainFrame, SelectedSede);
        SetSede_SecondField(MainFrame, SelectedSede);
        Hide_Sede_UnusedComp(MainFrame);
    }

    private void Hide_Sede_UnusedComp(CF_MainFrame MainFrame) {
        MainFrame.getThirdField_label().setVisible(false);
        MainFrame.getThirdField_outputArea().setVisible(false);
        MainFrame.getFourthField_label().setVisible(false);
        MainFrame.getFourthField_outputArea().setVisible(false);
        MainFrame.getDetailP_sessionList().setVisible(false);
    }

    private void SetSede_FirstField(CF_MainFrame MainFrame, Sede SelectedSede) {
        MainFrame.getFirstField_label().setText("Indirizzo");
        MainFrame.getFirstField_outputArea().setText(SelectedSede.getIndirizzo());
    }

    private void SetSede_SecondField(CF_MainFrame MainFrame, Sede SelectedSede) {
        MainFrame.getSecondField_label().setText("Città");
        MainFrame.getSecondField_outputArea().setText(SelectedSede.getCitta());
    }
}
