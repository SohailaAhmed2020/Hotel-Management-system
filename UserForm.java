package ui;

import models.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserForm extends BaseForm {
    private JLabel userName;
    private JTextField usernameInput;
    private JTextField emailInput;
    private JPasswordField passwordIpnut;
    private JButton saveEditButton;
    private JLabel email;
    private JLabel password;
    private JPanel jpanel;

    private int type;
    private User user;
    private IUserForm iUserForm;

    public static int USER_TYPE_PM=2;
    public static int USER_TYPE_SP=3;
    public static int USER_TYPE_CU=4;

    public UserForm(int userType,IUserForm iUserForm) {
        super("UserForm Form");
        this.iUserForm=iUserForm;
        this.type=userType;
        layoutInflater();
        formInNewMode();

    }

    private void formInNewMode() {
        saveEditButton.setText("New");
        saveEditButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                User ss=new User(type,usernameInput.getText(),emailInput.getText(),String.valueOf(passwordIpnut.getPassword()));
                ss.add();
                iUserForm.OnButtonPressed(ss);
                getFrame().dispose();
            }
        });
    }

    public UserForm(User user,IUserForm iUserForm){
        super("UserForm Form");
        this.iUserForm=iUserForm;
        this.type=user.getIdUser();
        this.user=user;
        layoutInflater();
        formInEditMode();
    }
    public void layoutInflater(){
        setJpanel(jpanel);
        inflateLayout();
    }
    private void formInEditMode() {
        saveEditButton.setText("Edit");
        emailInput.setText(this.user.getEmail());
        passwordIpnut.setText(this.user.getPassword());
        usernameInput.setText(this.user.getUserName());
        passwordIpnut.setEnabled(false);
        saveEditButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                user.setEmail(emailInput.getText());
                user.setUserName(usernameInput.getText());
                user.update();
                iUserForm.OnButtonPressed(user);
                getFrame().dispose();
            }
        });
    }
    public interface IUserForm{
        void OnButtonPressed(User user);
    }
}
