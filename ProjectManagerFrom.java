package ui;

import Helpers.Helpers;
import models.Event;
import models.Message;
import models.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class ProjectManagerFrom extends BaseForm {
    private JList reList;
    private JButton sendRequestToSPButton;
    private JList spList;
    private JList customerList;
    private JButton contanctCustomerButton;
    private JPanel jpanel;
    private User user;
    private List<Event> eventList;
    private List<User> listOfSp;
    private List<User> listOfCustomer;

    public ProjectManagerFrom(User user) {
        super("Project Manager");
        this.user = user;
        setJpanel(jpanel);
        inflateLayout();
        getListUpdates();
        sendRequestToSPButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    eventList.get(reList.getSelectedIndex()).sendRequestToSp(listOfSp.get(spList.getSelectedIndex()).getIdUser());
                    getListUpdates();

                }catch (Exception asd){
                    showError("Error","You Must Choose Both Sp and Event");
                }

            }
        });
        contanctCustomerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CustomerBillContact customerBillContact=new CustomerBillContact(user,listOfCustomer.get(customerList.getSelectedIndex()));
                customerBillContact.show();
            }
        });
    }

    private void getListUpdates() {
        Event event = new Event();

        try {
            eventList = event.getEventByStatus(1);
            String[] eventArray = Helpers.getEventsNameArray(eventList);
            reList.setListData(eventArray);

            listOfSp = new User().getUserByType(3);
            String[] spArray = Helpers.getUsersNamesArray(listOfSp);
            spList.setListData(spArray);


            listOfCustomer = new User().getUserByType(4);
            String[] cuArray = Helpers.getUsersNamesArray(listOfCustomer);
            customerList.setListData(cuArray);


//            messagesListV.setListData(messageArray);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
