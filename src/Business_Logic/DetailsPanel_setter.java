package Business_Logic;

import GUI_classes.*;
import Model_classes.*;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
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
    }

    private void setFields_inDetPanel_forConferenza(CF_MainFrame MainFrame, List Current_Main_outputList, int CurrentSpinnerValue) {
        Conferenza SelectedConferenza = (Conferenza) Current_Main_outputList.get(CurrentSpinnerValue);
        SetConf_DetailTitle(MainFrame.getDetail_ObjectName_label(), (SelectedConferenza.getNome()));
        SetConf_FirstField(MainFrame, SelectedConferenza);
        SetConf_SecondField(MainFrame, SelectedConferenza);
        SetConf_ThirdField(MainFrame, SelectedConferenza);
        Hide_Conf_UnusedComp(MainFrame);
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

    private void Hide_Conf_UnusedComp(CF_MainFrame MainFrame) {
        MainFrame.getFourthField_label().setVisible(false);
        MainFrame.getFourthField_outputArea().setVisible(false);
    }

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
