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

public class SPForm  extends BaseForm{
    private JList rqList;
    private JButton showDetailButton;
    private JPanel jp;
    private User sp;
    private List<Event> eventList;
    public SPForm(User user) {
        super("Service Provier");
        this.sp=user;
        setJpanel(jp);
        inflateLayout();
        getUpdatedLists();
        showDetailButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                EventForm eventForm=new EventForm(eventList.get(rqList.getSelectedIndex()), new EventForm.IEventForm() {
                    @Override
                    public void OnButtonPressed(Event event) {
                        getUpdatedLists();
                    }
                },true);
                eventForm.show();
            }
        });
    }

    private void getUpdatedLists() {
        Event event = new Event();

        try {
            eventList = event.getProviderEvents(sp.getIdUser());
            String[] eventArray = Helpers.getEventsNameArray(eventList);
            rqList.setListData(eventArray);


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
