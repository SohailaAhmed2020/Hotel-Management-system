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

public class CustomerBillContact extends BaseForm {
    private JList customerMessages;
    private JTextArea textArea1;
    private JButton sendButton;
    private JPanel jp;
    private JList billList;
    private User pm,customer;
    private List<Event> userEvents;
    private List<Message> userMessages;

    public CustomerBillContact(User pm ,User cu) {
        super("Contact");
        setJpanel(jp);
        inflateLayout();
        onCloseOperationHide();
        this.pm=pm;
        customer=cu;
        getupdateLists();
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Message message=new Message();
                message.setMessage(textArea1.getText());
                message.setIdReciver(customer.getIdUser());
                message.setIdSender(pm.getIdUser());
                message.send();
                textArea1.setText("");
            }
        });
    }

    private void getupdateLists() {
        Event event = new Event();

        try {
            userEvents = event.getMyEvents(customer.getIdUser());
            String[] eventArray = Helpers.getPriceEventNameFormatted(userEvents);
            billList.setListData(eventArray);

            userMessages = new Message().getMessagesByUserType(pm.getIdUserType(),customer.getIdUser());
            String[] meArray = Helpers.getMessages(userMessages);
            customerMessages.setListData(meArray);





        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
