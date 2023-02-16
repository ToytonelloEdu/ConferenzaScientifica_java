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
        AddIstanceClassFrame = business_logic.AddEditClassFrame;
        addInstFrame_controller = business_logic.AddEditFrame_controller;
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

    public void AnnullaButtonLoginClicked(String classSelected){
        business_logic.MainFrame.getAddButton().setEnabled(true);
        business_logic.MainFrame.getDeleteButton().setEnabled(true);
        business_logic.MainFrame.getLoginButton().setEnabled(true);
        if(classSelected.equals("Conferenza")){
            business_logic.MainFrame.getEditButton().setEnabled(true);
        }
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
        business_logic.deleteObject();
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
            business_logic.deleteObject();
    }



}


