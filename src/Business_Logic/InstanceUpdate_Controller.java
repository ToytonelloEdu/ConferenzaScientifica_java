package Business_Logic;

import DAO_classes.Conferenza_DAO;
import Exceptions.DataInsertedException;
import Exceptions.InsertFailedException;
import GUI_classes.CF_AddEditClassFrame;
import Model_classes.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InstanceUpdate_Controller {

    final Controller business_logic;
    AddEdit_ChecksController checksBL;
    CF_AddEditClassFrame AddEditClassFrame;
    ModelClass NewOggetto;
    ModelClass OldOggetto;

    List<ModelClass> ObjectsToDelete = new ArrayList<>();
    List<ModelClass> ObjectsToInsert = new ArrayList<>();



    private final DefaultListModel<ModelClass> dlModel10;
    private final DefaultListModel<ModelClass> dlModel11;
    private final DefaultListModel<ModelClass> dlModel12;
    private final DefaultListModel<ModelClass> dlModel14;
    private final DefaultListModel<String> dlModel12i;
    private final DefaultListModel<Integer> dlModel14i;

    public InstanceUpdate_Controller(Controller c) {
        business_logic = c;
        AddEditClassFrame = business_logic.AddEditClassFrame;
        dlModel10 = AddEditClassFrame.getDlModel10();
        dlModel11 = AddEditClassFrame.getDlModel11();
        dlModel12 = AddEditClassFrame.getDlModel12();
        dlModel14 = AddEditClassFrame.getDlModel14();
        dlModel12i = AddEditClassFrame.getDlModel12i();
        dlModel14i = AddEditClassFrame.getDlModel14i();
    }

    public void AddObjectToInsert(ModelClass InsObj) {
        ObjectsToInsert.add(InsObj);
    }

    public void AddObjectToInsert10(String classSelected, ModelClass insObj) {
        if ("Conferenza".equals(classSelected)) {
            ObjectsToInsert.add(new Ente_organizzatore((Conferenza) OldOggetto, (Istituzione) insObj));
        }
    }

    public void AddObjectToInsert12(String classSelected, ModelClass insObj, String comitato) {
        if ("Conferenza".equals(classSelected)) {
            ObjectsToInsert.add(new Conf_Organ((Conferenza) OldOggetto, (Organizzatore) insObj, comitato));
        }
    }

    public void AddObjectToInsert14(String classSelected, ModelClass insObj, Integer importo) {
        if ("Conferenza".equals(classSelected)) {
            ObjectsToInsert.add(new Conf_Sponsor((Conferenza) OldOggetto, (Sponsor) insObj, BigDecimal.valueOf(importo)));
        }
    }

    public void AddObjectToDelete(String classSelected, ModelClass delObj) {
        if("Conferenza".equals(classSelected)){
            ObjectsToDelete.add(delObj);
        }
    }

    public void AddObjectToDelete10(String classSelected, ModelClass delObj){
        if("Conferenza".equals(classSelected)){
            ObjectsToDelete.add(new Ente_organizzatore((Conferenza) OldOggetto, (Istituzione) delObj));
            }
    }

    public void AddObjectToDelete12(String classSelected, ModelClass delObj, String comitato){
        if("Conferenza".equals(classSelected)){
            ObjectsToDelete.add(new Conf_Organ((Conferenza) OldOggetto, (Organizzatore) delObj, comitato));
        }
    }

    public void AddObjectToDelete14(String classSelected, ModelClass delObj, Integer importo){
        if("Conferenza".equals(classSelected)){
            ObjectsToDelete.add(new Conf_Sponsor((Conferenza) OldOggetto, (Sponsor) delObj, BigDecimal.valueOf(importo)));
        }
    }

    public void setChecksBL(AddEdit_ChecksController checksBL) {
        this.checksBL = checksBL;
    }

    public void setOldOggetto(ModelClass oldOggetto) {
        OldOggetto = oldOggetto;
    }

    public List<ModelClass> getObjectsToDelete() {
        return ObjectsToDelete;
    }

    public List<ModelClass> getObjectsToInsert() {
        return ObjectsToInsert;
    }

    public void UpdateConferenza_Control() {
        try {
            if(checksBL.NoCampiVuoti_forConferenza()){
                UpdateConferenza();
            }
        } catch (DataInsertedException e) {
            JOptionPane.showMessageDialog(AddEditClassFrame, e.getMessage());
        }

    }

    private void UpdateConferenza() {
        DeleteObjectsRemoved();
        InsertObjectsAdded();
        NewOggetto = new Conferenza();
        SetField_forInsertedConferenza();
        Conferenza_DAO.getDAO().Update(OldOggetto, NewOggetto);
        SuccessfulUpdate();
    }

    private void SuccessfulUpdate() {
        business_logic.AddInstanceFrame_hidden();
        JOptionPane.showMessageDialog(business_logic.MainFrame, "Modifica riuscita");
        business_logic.MainFrame_searchButton_clicked();
    }

    private void InsertObjectsAdded() {
        while (!ObjectsToInsert.isEmpty()){
            try {
                ModelClass InsObj = ObjectsToInsert.remove(0);
                InsObj.getDao().Insert(InsObj);
            } catch (InsertFailedException ignored) {}
        }
    }

    private void DeleteObjectsRemoved() {
        while(!ObjectsToDelete.isEmpty()){
            ModelClass DelObj = ObjectsToDelete.remove(0);
            DelObj.getDao().Delete(DelObj);
        }
    }

    private void SetField_forInsertedConferenza() {
        (((Conferenza) NewOggetto)).setNome(AddEditClassFrame.getTextField1().getText());
        try {
            (((Conferenza) NewOggetto)).setDataInizio(getInizioConferenzaInDate());
            (((Conferenza) NewOggetto)).setDataFine(getFineConferenzaInDate());
        } catch (ParseException ignored) {
            throw new DataInsertedException("Formato data incorretto");
        }
        (((Conferenza) NewOggetto)).setDescrizione(AddEditClassFrame.getTextField4().getText());
        (((Conferenza) NewOggetto)).setCollocazione((Sede) AddEditClassFrame.getSelectOne_comboBox13().getSelectedItem());
    }

    private Date getFineConferenzaInDate() throws ParseException {
        String strData2 = AddEditClassFrame.getTextField3().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData2);
    }

    private Date getInizioConferenzaInDate() throws ParseException{
        String strData1 = AddEditClassFrame.getTextField2().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData1);
    }


    public void UpdateSede_Control() {
    }

    public void UpdateIstituzione_Control() {
    }

    public void UpdateUtente_Control() {
    }


    public void setConfDiSessione(Sessione tempSessione) {
        tempSessione.setConferenza((Conferenza) OldOggetto);
    }
}
