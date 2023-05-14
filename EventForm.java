package ui;

import models.Event;
import models.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

public class EventForm extends BaseForm {
    private JTextField eventNameInput;
    private JTextField guestNumber;
    private JTextField date;
    private JTextField room;
    private JTextField desInput;
    private JButton editSave;
    private JPanel jpanel;
    private JLabel priceLabel;
    private JTextField priceFiled;
    private IEventForm iEventForm;
    private Event event;
    private User user;

    public EventForm(IEventForm iEventForm, User user) {
        super("Create Event");
        this.iEventForm = iEventForm;
        this.user = user;
        startForm();
        formInNewMode();
    }


    private void formInNewMode() {
        editSave.setText("New");
        editSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Event event = new Event(
                            eventNameInput.getText(),
                            Integer.valueOf(guestNumber.getText()),
                            Date.valueOf(date.getText()),
                            room.getText(), desInput.getText(), user.getIdUser()
                    );

                    event.add();
                    iEventForm.OnButtonPressed(event);
                    getFrame().dispose();
                } catch (Exception ee) {
                    showError("Data Is Not Vaild", "You Must Enter Right data");
                }
            }
        });
    }

    private void startForm() {
        setJpanel(jpanel);
        inflateLayout();
        priceFiled.setVisible(false);
        priceLabel.setVisible(false);
    }

    public EventForm(Event event, IEventForm iEventForm) {
        super("Edit Event");
        this.event = event;
        this.iEventForm = iEventForm;
        startForm();
        formInEditMode(false);
    }

    public EventForm(Event event, IEventForm iEventForm, boolean b) {
        super("Edit Event");
        this.event = event;
        this.iEventForm = iEventForm;
        startForm();
        formInEditMode(b);
    }

    private void formInEditMode(boolean b) {
        editSave.setText("Edit");
        eventNameInput.setText(event.getEventName());
        guestNumber.setText(event.getGuestNumber() + "");
        date.setText(event.getDate().toString());
        room.setText(event.getRoom());
        desInput.setText(event.getDes());
        priceFiled.setText(event.getPrice() + "");

        if (!b) {
            priceFiled.setVisible(false);
            priceLabel.setVisible(false);
            date.setEnabled(false);
            room.setEnabled(false);

            editSave.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    updateEvent();

                }
            });
        } else {


            priceFiled.setVisible(true);
            priceLabel.setVisible(true);
            eventNameInput.setEnabled(false);
            guestNumber.setEnabled(false);
            room.setEnabled(false);
            desInput.setEnabled(false);
            date.setEnabled(true);


            editSave.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    updateEvent();
                }
            });
        }
    }

    private void updateEvent() {
        try {
            event.setEventName(eventNameInput.getText());
            event.setGuestNumber(Integer.valueOf(guestNumber.getText()));
            event.setDes(desInput.getText());
            event.setDate(Date.valueOf(date.getText()));
            event.setPrice(Float.valueOf(priceFiled.getText()));
            event.update();
            iEventForm.OnButtonPressed(event);
            getFrame().dispose();
        } catch (Exception ee) {
            showError("Data Is Not Vaild", "You Must Enter Right data");
        }
    }

    public interface IEventForm {
        void OnButtonPressed(Event event);
    }


}
