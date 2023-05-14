package ui;

import Helpers.Helpers;
import models.Event;
import models.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminForm extends BaseForm  {
    private JList pmList;
    private JList spList;
    private JButton deletepm;
    private JButton editpm;
    private JButton addPMButton;
    private JButton addSPButton;
    private JButton editsp;
    private JButton deletesp;
    private JList customerRequests;
    private JList cuList;
    private JButton addCustomerButton;
    private JButton editcu;
    private JButton deletecu;
    private JPanel jpanel;

    private List<User> pmListData=new ArrayList<>();
    private List<User> spListData=new ArrayList<>();
    private List<User> CustomerData=new ArrayList<>();
    private List<Event> events=new ArrayList<>();

    public AdminForm(String screenName) {
        super(screenName);
        setJpanel(jpanel);
        inflateLayout();
        getUpdateList();
        buttonsClicks();



    }

    private void buttonsClicks() {

        editsp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                editUser(spListData.get(spList.getSelectedIndex()));
            }
        });
        editpm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                editUser(pmListData.get(pmList.getSelectedIndex()));

            }
        });
        editcu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                editUser(CustomerData.get(cuList.getSelectedIndex()));

            }
        });




        deletesp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                spListData.get(spList.getSelectedIndex()).deleteUser();
                getUpdateList();
            }
        });
        deletepm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pmListData.get(pmList.getSelectedIndex()).deleteUser();
                getUpdateList();
            }
        });
        deletecu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CustomerData.get(cuList.getSelectedIndex()).deleteUser();
                getUpdateList();
            }
        });


        addPMButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                UserForm userForm=new UserForm(UserForm.USER_TYPE_PM, new UserForm.IUserForm() {
                    @Override
                    public void OnButtonPressed(User user) {
                        getUpdateList();
                    }
                });
                userForm.onCloseOperationHide();
                userForm.show();
            }
        });

        addSPButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                UserForm userForm=new UserForm(UserForm.USER_TYPE_SP, new UserForm.IUserForm() {
                    @Override
                    public void OnButtonPressed(User user) {
                        getUpdateList();
                    }
                });
                userForm.onCloseOperationHide();
                userForm.show();
            }
        });


        addCustomerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                UserForm userForm=new UserForm(UserForm.USER_TYPE_CU, new UserForm.IUserForm() {
                    @Override
                    public void OnButtonPressed(User user) {
                        getUpdateList();
                    }
                });
                userForm.onCloseOperationHide();
                userForm.show();
            }
        });












    }

    public void getUpdateList(){
        User user = new User();

        try {
            pmListData=user.getUserByType(2);
            spListData=user.getUserByType(3);
            CustomerData=user.getUserByType(4);
            events=new Event().getEventByStatus(1);


           String[] pmString= Helpers.getUsersNamesArray(pmListData);
           String[] spStrings= Helpers.getUsersNamesArray(spListData);
           String[] cuStrings= Helpers.getUsersNamesArray(CustomerData);
           String[] eventsAr= Helpers.getEventsNameArray(events);

            pmList.setListData(pmString);
            spList.setListData(spStrings);
            cuList.setListData(cuStrings);
            customerRequests.setListData(eventsAr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(User user){
        UserForm userForm=new UserForm(user, new UserForm.IUserForm() {
            @Override
            public void OnButtonPressed(User user) {
                getUpdateList();
            }
        });
        userForm.onCloseOperationHide();
        userForm.show();
    }
}
