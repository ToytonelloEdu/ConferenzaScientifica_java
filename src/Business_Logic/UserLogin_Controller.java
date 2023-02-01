package Business_Logic;

import DAO_classes.*;
import GUI_classes.*;
import Model_classes.*;

import javax.swing.*;

public class UserLogin_Controller {
    Controller business_logic;
    Organizzatore AccessUser = null;
    CF_AddEditClassFrame AddIstanceClassFrame;
    AddEditFrameAppearanceController addInstFrame_controller;

    public UserLogin_Controller(Controller c){
        business_logic = c;
        AddIstanceClassFrame = new CF_AddEditClassFrame(business_logic);
        addInstFrame_controller = new AddEditFrameAppearanceController(business_logic, AddIstanceClassFrame);
    }

    public void AccediButtonLoginClicked() {
        if (CheckNoCampiVuoti()) {
            String emailInserita = business_logic.NewLoginFrame.getTextField1().getText();
            String passwordInserita = business_logic.NewLoginFrame.getTextField2().getText();
            if (check_accesso(emailInserita, passwordInserita)) {
                JOptionPane.showMessageDialog(business_logic.NewLoginFrame, "Accesso eseguito");
                tipo_login();
            }
        } else
            JOptionPane.showMessageDialog(business_logic.NewLoginFrame, "Inserimento fallito: dati mancanti");
    }

    public void AnnullaButtonLoginClicked(){
        business_logic.MainFrame.getAddButton().setEnabled(true);
        business_logic.MainFrame.getDeleteButton().setEnabled(true);
        business_logic.MainFrame.getLoginButton().setEnabled(true);
        business_logic.NewLoginFrame.setVisible(false);
        business_logic.NewLoginFrame.getTextField1().setText("");
        business_logic.NewLoginFrame.getTextField2().setText("");
    }

    public boolean CheckNoCampiVuoti() {
        return !(business_logic.NewLoginFrame.getTextField1().getText().equals("")) && !(business_logic.NewLoginFrame.getTextField2().getText().equals(""));
    }

    public boolean check_accesso(String emailInserita, String passwordInserita) {
        try {
            AccessUser = Organizzatore_DAO.getDAO().getByEmail(emailInserita);
            business_logic.MainFrame.getLabelLogin().setText(AccessUser.getNome() +" "+ AccessUser.getCognome());
            return check_password(passwordInserita);
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(business_logic.NewLoginFrame, "Email errata!");
            return false;
        }
    }

    private boolean check_password(String passwordInserita) {
        if(!passwordInserita.equals("StaniLobo") && !passwordInserita.equals("sangio")) {
            JOptionPane.showMessageDialog(business_logic.NewLoginFrame, "Password errata!");
            return false;
        }
        else
            return true;
    }

    private void tipo_login() {
        if(!business_logic.MainFrame.getAddButton().isEnabled())
            login_eseguito_foradd();
        else if (!business_logic.MainFrame.getDeleteButton().isEnabled()) {
            login_eseguito_fordelete();
        } else
            login_eseguito();
    }

    public void login_eseguito() {
        business_logic.NewLoginFrame.setVisible(false);
        business_logic.MainFrame.getLoginButton().setVisible(false);
        business_logic.MainFrame.getLoginButton().setEnabled(true);
        business_logic.MainFrame.getLabelLogin().setVisible(true);
    }

    public void login_eseguito_foradd() {
        business_logic.NewLoginFrame.setVisible(false);
        business_logic.MainFrame.getLoginButton().setVisible(false);
        business_logic.MainFrame.getLoginButton().setEnabled(true);
        business_logic.MainFrame.getLabelLogin().setVisible(true);
        AddIstanceClassFrame.setVisible(true);
    }

    public void login_eseguito_fordelete(){
        business_logic.NewLoginFrame.setVisible(false);
        business_logic.MainFrame.getLoginButton().setVisible(false);
        business_logic.MainFrame.getLoginButton().setEnabled(true);
        business_logic.MainFrame.getLabelLogin().setVisible(true);
        deleteObject();
    }

    public void LoginButtonClicked() {
        business_logic.MainFrame.getLoginButton().setEnabled(false);
        business_logic.NewLoginFrame.setVisible(true);
    }

    public void DeleteButtonClicked(){
        business_logic.MainFrame.getDeleteButton().setEnabled(false);
        if(business_logic.MainFrame.getLoginButton().isVisible())
            business_logic.NewLoginFrame.setVisible(true);
        else
            deleteObject();
    }

    public void deleteObject() {
        int CurrentSpinnerValue = (Integer) business_logic.MainFrame.getSelection_spinner().getValue() - 1;
        ModelClass CurrentObjectOutput = business_logic.Current_Main_outputList.get(CurrentSpinnerValue);
        int risposta = JOptionPane.showConfirmDialog(business_logic.MainFrame.getDetails_panel(), "Vuoi cancellare l'oggetto: "+ CurrentObjectOutput +"?");
        if(risposta == 0) {
            CurrentObjectOutput.getDao().Delete(CurrentObjectOutput);
            business_logic.MainFrame_searchButton_clicked(business_logic.MainFrame);
        }
        business_logic.MainFrame.getDeleteButton().setEnabled(true);
    }

}


