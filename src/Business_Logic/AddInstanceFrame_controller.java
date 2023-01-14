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
                    setFieldsAdd_forUtente(Class_Selected);
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
        setConf_fifthField();
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
    public void setConf_fifthField(){
        AddIstanceClassFrame.getLabel5().setText("Collocazione");
        AddIstanceClassFrame.getTextField5().setText("");
    }
    public void Hide_Conferenza_UnusedComponents(){
        AddIstanceClassFrame.getLabel6().setVisible(false);
        AddIstanceClassFrame.getTextField6().setVisible(false);
        AddIstanceClassFrame.getLabel7().setVisible(false);
        AddIstanceClassFrame.getTextField7().setVisible(false);
        AddIstanceClassFrame.getLabel8().setVisible(false);
        AddIstanceClassFrame.getTextField8().setVisible(false);
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
        AddIstanceClassFrame.getLabel4().setVisible(false);
        AddIstanceClassFrame.getTextField4().setVisible(false);
        AddIstanceClassFrame.getLabel5().setVisible(false);
        AddIstanceClassFrame.getTextField5().setVisible(false);
        AddIstanceClassFrame.getLabel6().setVisible(false);
        AddIstanceClassFrame.getTextField6().setVisible(false);
        AddIstanceClassFrame.getLabel7().setVisible(false);
        AddIstanceClassFrame.getTextField7().setVisible(false);
        AddIstanceClassFrame.getLabel8().setVisible(false);
        AddIstanceClassFrame.getTextField8().setVisible(false);
    }

    public void setFieldsAdd_forUtente(String Class_Selected){
        setUtente_firstField();
        setUtente_secondField();
        setUtente_thirdField();
        setUtente_fourthField();
        setUtente_fifthField(Class_Selected);
        setUtente_sixthField();
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
    public void setUtente_fifthField(String Class_Selected){
        AddIstanceClassFrame.getLabel5().setText("Tipo utente");
        AddIstanceClassFrame.getTextField5().setText("");
    }
    public void setUtente_sixthField(){
        AddIstanceClassFrame.getLabel6().setText("Istituzione di afferenza");
        AddIstanceClassFrame.getTextField6().setText("");
    }
    public void Hide_Utente_UnusedComponents(){
        AddIstanceClassFrame.getLabel7().setVisible(false);
        AddIstanceClassFrame.getTextField7().setVisible(false);
        AddIstanceClassFrame.getLabel8().setVisible(false);
        AddIstanceClassFrame.getTextField8().setVisible(false);
    }

}
