package Business_Logic;

import DAO_classes.Conferenza_DAO;
import Exceptions.DataInsertedException;
import GUI_classes.CF_AddEditClassFrame;
import GUI_classes.CF_NewSessioneFrame;
import Model_classes.Conferenza;
import Model_classes.ModelClass;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddEdit_ChecksController {
    Controller business_logic;
    CF_AddEditClassFrame AddEditClassFrame;
    CF_NewSessioneFrame NewSessioneFrame;

    public AddEdit_ChecksController(Controller c){
        business_logic = c;
        AddEditClassFrame = business_logic.AddEditClassFrame;
        NewSessioneFrame = business_logic.NewSessioneFrame;
    }


    private boolean isEmpty(JTextComponent text_Comp) {
        return text_Comp.getText().equals("");
    }

    private boolean NoCampiVuotiInJPanel(JPanel mainPanel) {
        for(Component comp: mainPanel.getComponents()){
            try{
                if(isEmpty((JTextField) comp))
                    return false;
            }catch (ClassCastException ignored){}
        }
        return true;
    }

    boolean NoCampiVuoti_forConferenza() {
        return  !isEmpty(AddEditClassFrame.getTextField1()) &&
                !isEmpty(AddEditClassFrame.getTextField2()) &&
                !isEmpty(AddEditClassFrame.getTextField3()) &&
                !isEmpty(AddEditClassFrame.getTextField4());
    }

    public void CheckCorrectSessioneDates() {
        try{
            tryLDT_Conversion();
            business_logic.CorrectSessioneDates();
        }
        catch (IllegalArgumentException iae){
            business_logic.WrongSessioneDates();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void tryLDT_Conversion(){
        getInizioSessioneInLDT();
        getFineSessioneInLDT();
    }

    private LocalDateTime getInizioSessioneInLDT() {
        String strData = NewSessioneFrame.getTextField1().getText()
                +" "+NewSessioneFrame.getTextField1_1().getText();
        Timestamp data = Timestamp.valueOf(strData);
        return convertToLocalDateTime(data);
    }

    private LocalDateTime getFineSessioneInLDT() {
        String strData = NewSessioneFrame.getTextField2().getText()
                +" "+NewSessioneFrame.getTextField2_1().getText();
        Timestamp data = Timestamp.valueOf(strData);
        return convertToLocalDateTime(data);
    }

    private LocalDateTime convertToLocalDateTime(java.util.Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toLocalDateTime();
    }

    boolean CheckDateConferenza() throws ParseException {
        return CheckNoOverlapConferenze() && CheckDatesOrdered();
    }

    private boolean CheckDatesOrdered() throws ParseException {
        java.util.List<Date> DateConferenza = GetDateFromInsertedConferenza();
        if(DateConferenza.get(0).before(DateConferenza.get(1)))
            return true;
        else
            throw new DataInsertedException("Date di Inizio e Fine invertite");
    }

    private boolean CheckNoOverlapConferenze() throws ParseException {
        List<Date> DateConferenza = GetDateFromInsertedConferenza();
        for(ModelClass c : Conferenza_DAO.getDAO().getAll())
            if(OverlapDate(DateConferenza.get(0), DateConferenza.get(1), (Conferenza) c)) {
                throw new DataInsertedException("Sede occupata nelle date scelte");
            }
        return true;
    }

    private boolean OverlapDate(Date Inizio, Date Fine, Conferenza c) {
        if(c.getCollocazione().equals(AddEditClassFrame.getSelectOne_comboBox13().getSelectedItem())) {
            if     (Inizio.after(c.getDataInizio())  && (Fine.before(c.getDataFine()))) return true;
            if     (Inizio.before(c.getDataInizio()) && Fine.after(c.getDataFine())) return true;
            if     (Inizio.before(c.getDataInizio()) && Fine.after(c.getDataInizio())) return true;
            if     (Inizio.before(c.getDataFine()) && Fine.after(c.getDataFine())) return true;
            return (Inizio.equals(c.getDataInizio())) || (Fine.equals(c.getDataFine()));
        }
        else
            return false;
    }

    private List<Date> GetDateFromInsertedConferenza() throws ParseException {
        List<Date> tempList = new ArrayList<>(2);
        tempList.add(getInizioConferenzaInDate());
        tempList.add(getFineConferenzaInDate());
        return tempList;
    }

    boolean tryConf_LDT_Conversion() {
        try {
            getInizioConferenzaInDate();
            getFineConferenzaInDate();
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private Date getFineConferenzaInDate() throws ParseException {
        String strData2 = AddEditClassFrame.getTextField3().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData2);
    }

    private Date getInizioConferenzaInDate() throws ParseException{
        String strData1 = AddEditClassFrame.getTextField2().getText();
        return new SimpleDateFormat("yyyy-MM-dd").parse(strData1);
    }
}
