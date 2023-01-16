package Business_Logic;

import GUI_classes.CF_AddIstanceClassFrame;

import javax.swing.*;

public class AddInstanceFrame_controller {
    Controller business_logic;
    CF_AddIstanceClassFrame AddIstanceClassFrame;
    public AddInstanceFrame_controller(Controller c, CF_AddIstanceClassFrame aicf) {
        business_logic = c;
        AddIstanceClassFrame = aicf;
    }

    public void ChoiseClassAdd(String Class_Selected) {
        SetAllToVisible();
        switch (Class_Selected) {
            case "Conferenza" ->
                    setFieldsAdd_forConferenza();
            case "Sede" ->
                    setFieldsAdd_forSede();
            case "Utente", "Organizzatore", "Partecipante" ->
                    setFieldsAdd_forUtente();
        }
    }

    private void SetAllToVisible() {
        for(JComponent jc : AddIstanceClassFrame.getDataInsertComponentList())
            jc.setVisible(true);
    }

    public void setFieldsAdd_forConferenza(){
        setConf_firstField();
        setConf_secondField();
        setConf_thirdField();
        setConf_fourthField();
        setConf_FK();
        Hide_Conferenza_UnusedComponents();
    }
    public void setConf_firstField(){
        AddIstanceClassFrame.getLabel1().setText("Nome");
        AddIstanceClassFrame.getTextField1().setText("");
    }
    public void setConf_secondField(){
        AddIstanceClassFrame.getLabel2().setText("Data Inizio");
        AddIstanceClassFrame.getTextField2().setText("");
    }
    public void setConf_thirdField(){
        AddIstanceClassFrame.getLabel3().setText("Data Fine");
        AddIstanceClassFrame.getTextField3().setText("");
    }
    public void setConf_fourthField(){
        AddIstanceClassFrame.getLabel4().setText("Descrizione");
        AddIstanceClassFrame.getTextField4().setText("");
    }
    public void setConf_FK(){
        AddIstanceClassFrame.getLabel10().setText("Collocazione");
        business_logic.setValues_in_Select_comboBox(AddIstanceClassFrame);
    }
    public void Hide_Conferenza_UnusedComponents(){
        Hide_Conferenza_Unusedlabel();
        Hide_Conferenza_UnusedTextField_Button();
    }
    public void Hide_Conferenza_Unusedlabel(){
        AddIstanceClassFrame.getLabel5().setVisible(false);
        AddIstanceClassFrame.getLabel6().setVisible(false);
        AddIstanceClassFrame.getLabel7().setVisible(false);
        AddIstanceClassFrame.getLabel8().setVisible(false);
        AddIstanceClassFrame.getLabel9().setVisible(false);
    }
    public void Hide_Conferenza_UnusedTextField_Button(){
        AddIstanceClassFrame.getTextField5().setVisible(false);
        AddIstanceClassFrame.getTextField6().setVisible(false);
        AddIstanceClassFrame.getTextField7().setVisible(false);
        AddIstanceClassFrame.getTextField8().setVisible(false);
        AddIstanceClassFrame.getLeftButton9Button().setVisible(false);
        AddIstanceClassFrame.getRightButton9Button().setVisible(false);
    }

    public void setFieldsAdd_forSede(){
        setSede_firstField();
        setSede_secondField();
        setSede_thirdField();
        Hide_Sede_UnusedComponents();
    }
    public void setSede_firstField(){
        AddIstanceClassFrame.getLabel1().setText("Nome");
        AddIstanceClassFrame.getTextField1().setText("");
    }
    public void setSede_secondField(){
        AddIstanceClassFrame.getLabel2().setText("Indirizzo");
        AddIstanceClassFrame.getTextField2().setText("");
    }
    public void setSede_thirdField(){
        AddIstanceClassFrame.getLabel3().setText("Città");
        AddIstanceClassFrame.getTextField3().setText("");
    }
    public void Hide_Sede_UnusedComponents(){
        Hide_Sede_Unusedlabel();
        Hide_Sede_UnusedTextField_Button();
    }

    public void Hide_Sede_Unusedlabel(){
        AddIstanceClassFrame.getLabel4().setVisible(false);
        AddIstanceClassFrame.getLabel5().setVisible(false);
        AddIstanceClassFrame.getLabel6().setVisible(false);
        AddIstanceClassFrame.getLabel7().setVisible(false);
        AddIstanceClassFrame.getLabel8().setVisible(false);
        AddIstanceClassFrame.getLabel9().setVisible(false);
    }

    public void Hide_Sede_UnusedTextField_Button(){
        AddIstanceClassFrame.getTextField4().setVisible(false);
        AddIstanceClassFrame.getTextField5().setVisible(false);
        AddIstanceClassFrame.getTextField6().setVisible(false);
        AddIstanceClassFrame.getTextField7().setVisible(false);
        AddIstanceClassFrame.getTextField8().setVisible(false);
        AddIstanceClassFrame.getLeftButton9Button().setVisible(false);
        AddIstanceClassFrame.getRightButton9Button().setVisible(false);
    }

    public void setFieldsAdd_forUtente(){
        setUtente_firstField();
        setUtente_secondField();
        setUtente_thirdField();
        setUtente_fourthField();
        setUtente_fifthField();
        setUtente_tipo();
        Hide_Utente_UnusedComponents();
    }
    public void setUtente_firstField(){
        AddIstanceClassFrame.getLabel1().setText("Titolo");
        AddIstanceClassFrame.getTextField1().setText("");
    }
    public void setUtente_secondField(){
        AddIstanceClassFrame.getLabel2().setText("Nome");
        AddIstanceClassFrame.getTextField2().setText("");
    }
    public void setUtente_thirdField(){
        AddIstanceClassFrame.getLabel3().setText("Cognome");
        AddIstanceClassFrame.getTextField3().setText("");
    }
    public void setUtente_fourthField(){
        AddIstanceClassFrame.getLabel4().setText("Email");
        AddIstanceClassFrame.getTextField4().setText("");
    }
    public void setUtente_fifthField(){
        AddIstanceClassFrame.getLabel5().setText("Istituzione di afferenza");
        AddIstanceClassFrame.getTextField5().setText("");
    }

    private void setUtente_tipo(){
        AddIstanceClassFrame.getLabel9().setText("Ruolo");
        AddIstanceClassFrame.getLeftButton9Button().setText("Partecipante");
        AddIstanceClassFrame.getRightButton9Button().setText("Organizzatore");
    }
    public void Hide_Utente_UnusedComponents(){
        AddIstanceClassFrame.getLabel6().setVisible(false);
        AddIstanceClassFrame.getTextField6().setVisible(false);
        AddIstanceClassFrame.getLabel7().setVisible(false);
        AddIstanceClassFrame.getTextField7().setVisible(false);
        AddIstanceClassFrame.getLabel8().setVisible(false);
        AddIstanceClassFrame.getTextField8().setVisible(false);
    }

}
