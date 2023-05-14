package ui;

import com.mysql.jdbc.log.Log;
import models.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Login extends BaseForm {
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private JPanel jpanell;
    private JButton createAccountButton;

    public Login() {
        super(Login.class.getName());

        setJpanel(jpanell);
        inflateLayout();

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (validateInputs()) {
                    User s = new User();
                    try {
                        boolean isLogin = s.login(emailInput.getText(), String.valueOf(passwordInput.getPassword()));
                        if (isLogin) {
                            if (s.isAdmin()) {
                                AdminForm adminForm = new AdminForm("Admin Form");
                                adminForm.show();
                                getFrame().dispose();
                                System.out.println("Login Success");
                            } else if (s.isPM()) {
                                ProjectManagerFrom pform=new ProjectManagerFrom(s);
                                pform.show();
                                getFrame().dispose();
                            }else if(s.isCustomer()){
                                CustomerForm customerForm=new CustomerForm(s);
                                customerForm.show();
                                getFrame().dispose();
                            }
                            else if(s.isSp()){
                                SPForm spForm=new SPForm(s);
                                spForm.show();
                                getFrame().dispose();
                            }

                        } else {
                            showError("Error", "Wrong Data");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        createAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);


                UserForm userForm = new UserForm(UserForm.USER_TYPE_CU, new UserForm.IUserForm() {
                    @Override
                    public void OnButtonPressed(User user) {
//                         On Create Customer Account
                    }
                });
                userForm.show();
                getFrame().dispose();
            }
        });
    }

    private boolean validateInputs() {
        if (emailInput.getText().equals("")) {
            showError("Mail Is Empty", "You Must Enter Email Address");
            return false;
        } else if (String.valueOf(passwordInput.getPassword()).equals("")) {
            showError("Password Is Empty", "You Must Enter Password");
            return false;
        }
        return true;
    }


}
