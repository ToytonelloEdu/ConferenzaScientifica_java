package Business_Logic;

import DAO_classes.Istituzione_DAO;
import GUI_classes.*;
import Model_classes.*;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class DetailsPanel_setter {
    Controller business_logic;
    String Class_selected;
    DefaultListModel<String> dListModel = new DefaultListModel<>();
    public DetailsPanel_setter(Controller controller) {
        business_logic = controller;
    }

    public DefaultListModel<String> getdListModel() {
        return dListModel;
    }

    public void setAllDetailsComp_visible(List<JComponent> detailsComp_list) {
        for(JComponent comp : detailsComp_list){
            comp.setVisible(true);
        }
        business_logic.MainFrame.getDetailsButton().setVisible(true);
        dListModel.clear();
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
        Class_selected = Objects.requireNonNull((String) Class_Cbox.getSelectedItem());
        switch (Class_selected) {
            case "Conferenza" ->
                    setFields_inDetPanel_forConferenza(MainFrame, Current_Main_outputList, CurrentSpinnerValue);
            case "Sede" -> setFields_inDetPanel_forSede(MainFrame, Current_Main_outputList, CurrentSpinnerValue);
            case "Utente", "Partecipante", "Organizzatore" ->
                    setFields_inDetPanel_forUtenti(MainFrame, Current_Main_outputList, CurrentSpinnerValue);
            case "Istituzione" -> setFields_inDetPanel_forIstituzione(MainFrame, Current_Main_outputList, CurrentSpinnerValue);
            default -> MainFrame.getDetails_panel().setVisible(false);
        }
    }

    private void setFields_inDetPanel_forIstituzione(CF_MainFrame mainFrame, List current_main_outputList, int currentSpinnerValue) {
        Istituzione SelectedIstituzione = (Istituzione) current_main_outputList.get(currentSpinnerValue);
        mainFrame.getDetail_ObjectName_label().setText(SelectedIstituzione.getNome());
        mainFrame.getFirstField_label().setText("Nazione");
        mainFrame.getFirstField_outputArea().setText(SelectedIstituzione.getNazione());
        setPercentualeIstituzione(mainFrame, SelectedIstituzione);
        dListModel.clear();
        Hide_Istituzione_UnusedFields(mainFrame);
    }

    public void setPercentualeIstituzione(CF_MainFrame mainFrame, Istituzione SelectedIstituzione){
        JSpinner Spinner_mese = mainFrame.getMeseSpinner();

        int CurrentSpinnerValue = (int) Spinner_mese.getValue();

        mainFrame.getSecondField_label().setText("Percetuale mensile");
        int totale_speaker = Istituzione_DAO.getDAO().allKeynoteSpeaker();
        mainFrame.getSecondField_outputArea().setText(String.valueOf(totale_speaker));
        int totale_speaker_forIstituzione = Istituzione_DAO.getDAO().countKeynoteSpeaker_byIstituzione(CurrentSpinnerValue, SelectedIstituzione);
        mainFrame.getThirdField_outputArea().setText(String.valueOf(totale_speaker_forIstituzione));
        mainFrame.getThirdField_label().setText("Percentuale annuale");
    }



    private void Hide_Istituzione_UnusedFields(CF_MainFrame mainFrame) {
        mainFrame.getFourthField_label().setVisible(false);
        mainFrame.getFourthField_outputArea().setVisible(false);
        mainFrame.getFirstList_Panel().setVisible(false);
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
        mainFrame.getFirstList_Panel().setVisible(false);
    }
    //Conferenza's details setting methods
    private void setFields_inDetPanel_forConferenza(CF_MainFrame MainFrame, List Current_Main_outputList, int CurrentSpinnerValue) {
        Conferenza SelectedConferenza = (Conferenza) Current_Main_outputList.get(CurrentSpinnerValue);
        SetConf_DetailTitle(MainFrame.getDetail_ObjectName_label(), (SelectedConferenza.getNome()));
        SetConf_FirstField(MainFrame, SelectedConferenza);
        SetConf_SecondField(MainFrame, SelectedConferenza);
        SetConf_ThirdField(MainFrame, SelectedConferenza);
        for(Sessione s : SelectedConferenza.getSessioneList())
            dListModel.addElement(s.toDetailString());
        Hide_Conferenza_UnusedComp(MainFrame);
    }

    private void SetConf_DetailTitle(JLabel MainFrame, String SelectedConferenza) {
        MainFrame.setText(SelectedConferenza);
    }

    private void SetConf_FirstField(CF_MainFrame MainFrame, Conferenza SelectedConferenza) {
        MainFrame.getFirstField_label().setText("Date");
        MainFrame.getFirstField_outputArea().setText("da " + SelectedConferenza.getDataInizio() + " a " + SelectedConferenza.getDataFine());
    }

    private void SetConf_SecondField(CF_MainFrame MainFrame, Conferenza SelectedConferenza) {
        MainFrame.getSecondField_label().setText("Collocazione");
        MainFrame.getSecondField_outputArea().setText(SelectedConferenza.getCollocazione().toDetailString());
    }

    private void SetConf_ThirdField(CF_MainFrame MainFrame, Conferenza SelectedConferenza) {
        MainFrame.getThirdField_label().setText("Descrizione");
        MainFrame.getThirdField_outputArea().setText(SelectedConferenza.getDescrizione());
    }

    private void Hide_Conferenza_UnusedComp(CF_MainFrame MainFrame) {
        MainFrame.getFourthField_label().setVisible(false);
        MainFrame.getFourthField_outputArea().setVisible(false);
    }


    //Sede's details setting methods
    private void setFields_inDetPanel_forSede(CF_MainFrame MainFrame, List Current_Main_outputList, int CurrentSpinnerValue) {
        Sede SelectedSede = (Sede) Current_Main_outputList.get(CurrentSpinnerValue);
        MainFrame.getDetail_ObjectName_label().setText((SelectedSede.getNome()));
        SetSede_FirstField(MainFrame, SelectedSede);
        SetSede_SecondField(MainFrame, SelectedSede);
        for(Locazione l : SelectedSede.getLocazioneList())
            dListModel.addElement(l.toString());
        Hide_Sede_UnusedComp(MainFrame);
    }

    private void Hide_Sede_UnusedComp(CF_MainFrame MainFrame) {
        MainFrame.getThirdField_label().setVisible(false);
        MainFrame.getThirdField_outputArea().setVisible(false);
        MainFrame.getFourthField_label().setVisible(false);
        MainFrame.getFourthField_outputArea().setVisible(false);
        MainFrame.getDetailsButton().setVisible(false);
    }

    private void SetSede_FirstField(CF_MainFrame MainFrame, Sede SelectedSede) {
        MainFrame.getFirstField_label().setText("Indirizzo");
        MainFrame.getFirstField_outputArea().setText(SelectedSede.getIndirizzo());
    }

    private void SetSede_SecondField(CF_MainFrame MainFrame, Sede SelectedSede) {
        MainFrame.getSecondField_label().setText("Città");
        MainFrame.getSecondField_outputArea().setText(SelectedSede.getCitta());
    }

    public void setSessioneDetails(CF_SessionDetailsFrame SessionDetailsFrame) {
        SessionDetailsFrame.getListModel().clear();
        int currentIndex = (int) business_logic.MainFrame.getSelection_spinner().getValue() -1;
        int currentListIndex = business_logic.MainFrame.getDetPanel_FirstList().getSelectedIndex();
        try {
            Conferenza selectedConferenza = (Conferenza) business_logic.Current_Main_outputList.get(currentIndex);
            Sessione selectedSessione = selectedConferenza.getSessioneList().get(currentListIndex);
            setSessioneDetInFields(SessionDetailsFrame, selectedSessione);
        }catch (IndexOutOfBoundsException | ClassCastException ignored){}
    }

    private void setSessioneDetInFields(CF_SessionDetailsFrame SessionDetailsFrame, Sessione selectedSessione) {
        SessionDetailsFrame.setCurrentEventoList(selectedSessione.getEventoList());
        SessionDetailsFrame.getSessTitle_Label().setText(selectedSessione.getNome());
        SessionDetailsFrame.getInizioSessione_textArea().setText(Timestamp.valueOf(selectedSessione.getInizio()).toString());
        SessionDetailsFrame.getFineSessione_textArea().setText(Timestamp.valueOf(selectedSessione.getFine()).toString());
        SessionDetailsFrame.getChair_textArea().setText(selectedSessione.getChair().toDetailString());
        setKeyNoteSpeakerInfo(SessionDetailsFrame, selectedSessione);
        SessionDetailsFrame.getLocazione_textArea().setText(selectedSessione.getLocazione().getNome());

        for (Evento e : selectedSessione.getEventoList())
            SessionDetailsFrame.getListModel().addElement(e.toDetailsString());
    }

    private void setKeyNoteSpeakerInfo(CF_SessionDetailsFrame SessionDetailsFrame, Sessione selectedSessione) {
        try {
            SessionDetailsFrame.getKeynoteSpeaker_textArea().setText(selectedSessione.getKeynote_speaker().toDetailString());
        } catch (NullPointerException e) {
            SessionDetailsFrame.getKeynoteSpeaker_textArea().setText("Non presente");
        }
    }

    public void SetDescrizioneForEventoSelected(CF_SessionDetailsFrame SessionDetailsFrame) {

        int currentIndex = SessionDetailsFrame.getEventi_JList().getSelectedIndex();
        try {
            Evento selectedEvento = SessionDetailsFrame.getCurrentEventoList().get(currentIndex);
            try {
                setDetailsForInterverto(SessionDetailsFrame, (Intervento) selectedEvento);
                return;
            } catch (ClassCastException e) {
                try {
                    //noinspection ConstantConditions
                    setDetailsForEvSociale(SessionDetailsFrame, (Evento_Sociale) selectedEvento);
                    return;
                } catch (ClassCastException ignored) {
                }
            }
        }catch (IndexOutOfBoundsException ignored){}
        //caso Pausa (non ha descrizione):
        SessionDetailsFrame.getDescrizione_JPanel().setVisible(false);
    }

    private void setDetailsForEvSociale(CF_SessionDetailsFrame SessionDetailsFrame, Evento_Sociale selectedEvento) {
        SessionDetailsFrame.getDescrizione_Label().setText("Descrizione");
        SessionDetailsFrame.getDescrizione_textArea().setText(selectedEvento.getDescrizione());
        SessionDetailsFrame.getDescrizione_JPanel().setVisible(true);
    }

    private void setDetailsForInterverto(CF_SessionDetailsFrame SessionDetailsFrame, Intervento selectedEvento) {
        SessionDetailsFrame.getDescrizione_Label().setText("Abstract");
        SessionDetailsFrame.getDescrizione_textArea().setText(selectedEvento.getAbstract());
        SessionDetailsFrame.getDescrizione_JPanel().setVisible(true);
    }

    public void aggiornaPercentualeIstituzione(CF_MainFrame mainFrame, List<ModelClass> current_main_outputList) {
        Istituzione currentIstituzione = (Istituzione) current_main_outputList.get((int) mainFrame.getSelection_spinner().getValue() - 1);
        setPercentualeIstituzione(mainFrame, currentIstituzione);
    }
}
