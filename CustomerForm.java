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

public class CustomerForm extends BaseForm {
    private JButton createEventButton;
    private JButton editButton;
    private JButton cancelButton;
    private JList events;
    private JTextArea messageInput;
    private JButton sendMessageButton;
    private JPanel jpanel;
    private JList messagesListV;
    private User user;
    private List<Event> eventList;
    private List<Message> messagesList;


    public CustomerForm(User user) {
        super("Customer Form");
        setJpanel(jpanel);
        inflateLayout();
        this.user = user;

        getListUpdates();

        createEventButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EventForm eventForm=new EventForm(new EventForm.IEventForm() {
                    @Override
                    public void OnButtonPressed(Event event) {
                        getListUpdates();
                    }
                },user);
                eventForm.show();
            }

        });
        editButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EventForm eventForm=new EventForm(
                        eventList.get(events.getSelectedIndex()),
                        new EventForm.IEventForm() {
                            @Override
                            public void OnButtonPressed(Event event) {
                                getListUpdates();
                            }
                        }
                );
                eventForm.show();
            }
        });
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventList.get(events.getSelectedIndex()).cancel();
                getListUpdates();
            }
        });
        sendMessageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Message message=new Message();
                message.setIdSender(user.getIdUser());
                message.setMessage(messageInput.getText());
                message.sendToProjectManager();
                messageInput.setText("");
                getListUpdates();

            }
        });
    }

    private void getListUpdates() {
        Event event = new Event();

        try {
            eventList = event.getMyEvents(user.getIdUser());
            String[] eventArray = Helpers.getEventsNameArray(eventList);

            messagesList=new Message().getMessages(user.getIdUser());
            String[] messageArray = Helpers.getMessages(messagesList);

            events.setListData(eventArray);
            messagesListV.setListData(messageArray);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
