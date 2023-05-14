package models;

import dataBase.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Event extends Model {

    private int IdEvent;
    private String EventName;
    private int GuestNumber;
    java.sql.Date Date;
    private String Room;
    private String Des;
    private int BookingStatus;
    private int Provider;
    private float Price;
    private int IsReady;
    private int IdCreator;


    public Event() {
    }


    public Event(String eventName, int guestNumber, java.sql.Date date, String room, String des, int idCreator) {
        EventName = eventName;
        GuestNumber = guestNumber;
        Date = date;
        Room = room;
        Des = des;
        IdCreator = idCreator;
    }

    public Event(int idEvent, String eventName, int guestNumber, java.sql.Date date, String room, String des, int bookingStatus, int provider, float price, int isReady, int idCreator) {
        IdEvent = idEvent;
        EventName = eventName;
        GuestNumber = guestNumber;
        Date = date;
        Room = room;
        Des = des;
        BookingStatus = bookingStatus;
        Provider = provider;
        Price = price;
        IsReady = isReady;
        IdCreator = idCreator;
    }

    public Event(String eventName, int guestNumber, java.sql.Date date, String room, String des, int bookingStatus, int provider, float price, int isReady, int idCreator) {
        EventName = eventName;
        GuestNumber = guestNumber;
        Date = date;
        Room = room;
        Des = des;
        BookingStatus = bookingStatus;
        Provider = provider;
        Price = price;
        IsReady = isReady;
        IdCreator = idCreator;
    }

    public void add() {
        String query = String.format("INSERT INTO event (EventName,GuestNumber,Date,Room,Des,IdCreator) VALUES ('%s',%s,'%s','%s','%s',%s)",
                getEventName(), getGuestNumber(), getDate(), getRoom(), getDes(), getIdCreator());
        excuteDbOperation(query);
    }




    public void sendRequestToSp(int idSp) {
        String query = String.format("update event set Provider=%s,BookingStatus=%s where IdEvent=%s",
                idSp,2,getIdEvent());
        excuteDbOperation(query);
    }



    public void update() {
        String query = String.format("update event set EventName='%s',GuestNumber=%s,Des='%s' " +
                        ", Price=%s" +
                        ", Date='%s'" +
                        "" +
                        "where IdEvent=%s",
                getEventName(),getGuestNumber(),getDes(),getPrice(),getDate(),getIdEvent());
        excuteDbOperation(query);
    }

    public void cancel() {
        String query = String.format("update event set IsReady=%s where IdEvent=%s",
                2,getIdEvent());
        excuteDbOperation(query);
    }


    public List<Event> getMyEvents(int idCreator) throws SQLException {
        String query = String.format("Select * from event where IdCreator=%s and IsReady!=2" ,idCreator);
        print(query);
        return getEvents(query);
    }

    public List<Event> getProviderEvents(int idCreator) throws SQLException {
        String query = String.format("Select * from event where Provider=%s and IsReady!=2" ,idCreator);
        return getEvents(query);
    }

    public List<Event> getEventByStatus(int status) throws SQLException {
        String query = String.format("Select * from event where BookingStatus=%s",status);
        return getEvents(query);
    }

    private List<Event> getEvents(String query) throws SQLException {
        connect();
        ResultSet re = select(query);

        List<Event> data = new ArrayList<>();

        while (re.next()) {
            data.add(new Event(
                            re.getInt("IdEvent"),
                            re.getString("EventName"),
                            re.getInt("GuestNumber"),
                            re.getDate("Date"),
                            re.getString("Room"),
                            re.getString("Des"),
                            re.getInt("BookingStatus"),
                            re.getInt("Provider"),
                            re.getFloat("Price"),
                            re.getInt("IsReady"),
                            re.getInt("IdCreator")
                    )
            );
        }
        close();
        return data;
    }


    public int getIdEvent() {
        return IdEvent;
    }

    public void setIdEvent(int idEvent) {
        IdEvent = idEvent;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public int getGuestNumber() {
        return GuestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        GuestNumber = guestNumber;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public int getBookingStatus() {
        return BookingStatus;
    }

    public void setBookingStatus(int bookingStatus) {
        BookingStatus = bookingStatus;
    }

    public int getProvider() {
        return Provider;
    }

    public void setProvider(int provider) {
        Provider = provider;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getIsReady() {
        return IsReady;
    }

    public void setIsReady(int isReady) {
        IsReady = isReady;
    }

    public int getIdCreator() {
        return IdCreator;
    }

    public void setIdCreator(int idCreator) {
        IdCreator = idCreator;
    }
}
