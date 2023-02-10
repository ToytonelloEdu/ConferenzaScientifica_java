package Business_Logic;

import DAO_classes.Conferenza_DAO;
import Exceptions.DataInsertedException;
import GUI_classes.CF_AddEditClassFrame;
import GUI_classes.CF_NewSessioneFrame;
import GUI_classes.CF_NewSponsorFrame;
import Model_classes.Conferenza;
import Model_classes.Evento;
import Model_classes.ModelClass;
import Model_classes.Sessione;

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
        business_logic.instInsert_controller.setChecksBL(this);
        AddEditClassFrame = business_logic.AddEditClassFrame;
        NewSessioneFrame = business_logic.NewSessioneFrame;
    }


    private boolean isEmpty(JTextComponent text_Comp) {
        return text_Comp.getText().equals("");
    }

    boolean NoCampiVuotiInJPanel(JPanel mainPanel) {
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




    boolean CheckSessioneInserted(DefaultListModel<ModelClass> dlModel11) {
        return CheckNoCampiVuotiForSessione()
                && CheckDate()
                && CheckNoOverlapSessione(dlModel11)
                && CheckChair_e_Keynote();

    }

    private boolean CheckNoOverlapSessione(DefaultListModel<ModelClass> dlModel11) {
        List<LocalDateTime> listLDT = TryLocalDateTimeConversion();
        List<ModelClass> sessList = getAllbyDLModel(dlModel11);
        for(ModelClass sess: sessList){
            if (SessionsOverlap(listLDT.get(0), listLDT.get(1), (Sessione) sess))
                throw new DataInsertedException("Locazione scelta già occupata nell'intervallo di tempo");
        }
        return true;
    }

    private boolean SessionsOverlap(LocalDateTime Inizio, LocalDateTime Fine, Sessione sess) {
        if(sess.getLocazione().equals(NewSessioneFrame.getComboBox3().getSelectedItem())){
            if     (Inizio.isAfter(sess.getInizio())  && (Fine.isBefore(sess.getFine()))) return true;
            if     (Inizio.isBefore(sess.getInizio()) && Fine.isAfter(sess.getFine())) return true;
            if     (Inizio.isBefore(sess.getInizio()) && Fine.isAfter(sess.getInizio())) return true;
            if     (Inizio.isBefore(sess.getFine()) && Fine.isAfter(sess.getFine())) return true;
            return (Inizio.equals(sess.getInizio())) || (Fine.equals(sess.getFine()));
        }
        else
            return false;
    }

    private List<ModelClass> getAllbyDLModel(DefaultListModel<ModelClass> dlModel) {
        List<ModelClass> tempList = new ArrayList<>();
        for(int i = 0; i < dlModel.getSize(); i++){
            tempList.add( dlModel.getElementAt(i));
        }
        return tempList;
    }

    private Boolean CheckNoCampiVuotiForSessione() {
        if(NoCampiVuoti())
            return true;
        else
            throw new DataInsertedException("Uno o più campi vuoti");

    }

    private boolean NoCampiVuoti() {
        return !(NewSessioneFrame.getTextField1().getText().equals(""))
                && !(NewSessioneFrame.getTextField1_1().getText().equals(""))
                && !(NewSessioneFrame.getTextField2().getText().equals(""))
                && !(NewSessioneFrame.getTextField2_1().getText().equals(""))
                && !(NewSessioneFrame.getEvDLModel().isEmpty())
                && !(NewSessioneFrame.getTextField0().getText().equals(""));
    }

    private boolean CheckDate() {
        List<LocalDateTime> LDTList = TryLocalDateTimeConversion();

        if (LDTList.get(0).isBefore(LDTList.get(1)))
            return true;
        else
            throw new DataInsertedException("Inizio e fine sono invertiti (o coincidono)");
    }

    private List<LocalDateTime> TryLocalDateTimeConversion() {
        List<LocalDateTime> retList = new ArrayList<>(2);
        List<String> strDatas = new ArrayList<>(2);
        strDatas.add(NewSessioneFrame.getTextField1().getText()+" "+NewSessioneFrame.getTextField1_1().getText());
        strDatas.add(NewSessioneFrame.getTextField2().getText()+" "+NewSessioneFrame.getTextField2_1().getText());
        for(String strData : strDatas) {
            Timestamp tempTStamp = Timestamp.valueOf(strData);
            retList.add(tempTStamp.toLocalDateTime());
        }
        return retList;
    }

    private boolean CheckChair_e_Keynote() {
        if(NewSessioneFrame.getComboBox5().isEnabled()) {
            if (NewSessioneFrame.getComboBox4().getSelectedItem().equals(NewSessioneFrame.getComboBox5().getSelectedItem())) {
                throw new DataInsertedException("Keynote Speaker e Chair combaciano");
            } else
                return true;
        }
        else
            return true;

    }



    boolean NoCampiVuoti_forSpons(CF_NewSponsorFrame NewSponsorFrame) {
        return !(isEmpty(NewSponsorFrame.getTextField1()) || isEmpty(NewSponsorFrame.getTextField2()));
    }





    void CheckEventoInserted() {
        CheckNoCampiVuotiForEvento();
        List<LocalDateTime> listLDT = CheckDateForEvento();
        CheckDateEventoInSessione(listLDT);
        CheckNoOverlap(listLDT);
    }

    private List<LocalDateTime> CheckDateForEvento(){
        List<LocalDateTime> listLDT = TryLocalDateTimeConversionForEvento();
        if(!(listLDT.get(0).isBefore(listLDT.get(1))))
            throw new DataInsertedException("Fine e Inizio sono temporalmente invertiti");
        return listLDT;
    }

    private List<LocalDateTime> TryLocalDateTimeConversionForEvento() {
        List<LocalDateTime> tempListLDT = new ArrayList<>(2);
        tempListLDT.add(getInizioEventoInLDT());
        tempListLDT.add(getFineEventoInLDT());
        return tempListLDT;
    }

    private LocalDateTime getFineEventoInLDT() {
        String strData = NewSessioneFrame.getTextField4().getText();
        Timestamp data = Timestamp.valueOf(strData);
        return convertToLocalDateTime(data);
    }

    private LocalDateTime getInizioEventoInLDT() {
        String strData = NewSessioneFrame.getTextField3().getText();
        Timestamp data = Timestamp.valueOf(strData);
        return convertToLocalDateTime(data);
    }

    private void CheckDateEventoInSessione(List<LocalDateTime> listLDT) {
        LocalDateTime InizioSess = getInizioSessioneInLDT();
        LocalDateTime FineSess = getFineSessioneInLDT();
        if (!DateEventoInSessione(listLDT.get(0), listLDT.get(1), InizioSess, FineSess))
            throw new DataInsertedException("L'evento non rientra temporalmente nella sessione");
    }

    private boolean DateEventoInSessione(LocalDateTime InizioEv, LocalDateTime FineEv, LocalDateTime InizioSess, LocalDateTime FineSess){
        return  InizioEv.isAfter(InizioSess) || InizioEv.equals(InizioSess) &&
                FineEv.isAfter(InizioSess) &&
                InizioEv.isBefore(FineSess)  &&
                FineEv.isBefore(FineSess)  || FineEv.equals(FineSess);
    }

    private void CheckNoOverlap(List<LocalDateTime> listLDT) {
        List<Evento> EventiInseritiList = getAllEventiInseriti();
        for (Evento e : EventiInseritiList) {
            if(EventsOverlap(listLDT, e))
                throw new DataInsertedException("Evento inserito fa conflitto con un evento precedente");
        }
    }

    private boolean EventsOverlap(List<LocalDateTime> listLDT, Evento e) {
        return (listLDT.get(0).isAfter(e.getInizio()) && listLDT.get(1).isBefore(e.getFine()))
                ||(listLDT.get(0).isBefore(e.getInizio()) && listLDT.get(1).isAfter(e.getFine()))
                ||(listLDT.get(0).isBefore(e.getFine()) && listLDT.get(1).isAfter(e.getFine()))
                ||(listLDT.get(0).isBefore(e.getInizio()) && listLDT.get(1).isAfter(e.getInizio()));
    }

    private List<Evento> getAllEventiInseriti() {
        List<Evento> tempList = new ArrayList<>(NewSessioneFrame.getEvDLModel().getSize());
        for(int i = 0; i < NewSessioneFrame.getEvDLModel().getSize(); i++)
            tempList.add(NewSessioneFrame.getEvDLModel().getElementAt(i));
        return tempList;
    }



    private void CheckNoCampiVuotiForEvento() {
        if(CampiVuotiForEvento())
            throw new DataInsertedException("Dati mancanti");

    }

    private boolean CampiVuotiForEvento() {
        return NewSessioneFrame.getTextField3().getText().equals("") ||
                NewSessioneFrame.getTextField4().getText().equals("") ||
                ((NewSessioneFrame.getTextField5().getText().equals("") && NewSessioneFrame.getTextField6().getText().equals(""))
                        && NewSessioneFrame.getPausaButton().isEnabled());
    }

    public boolean CheckCorrectImporto(String textImporto) {

        try {
            Integer.parseInt(textImporto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
