package models;

import dataBase.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Message extends Model {
    private int IdMessage;
    private int IdSender;
    private int IdReciver;
    private int IdUserTypeReciver;
    private String Message;




    public List<Message> getMessages(int idRecive) throws SQLException {
        String query = String.format("Select * from messages where IdReciver=%s" ,idRecive);
        return getMessages(query);
    }
    public List<Message> getMessagesByUserType(int idReciveType, int idUser) throws SQLException {
        String query = String.format("Select * from messages where IdUserTypeReciver=%s and IdSender=%s" ,idReciveType,idUser);
        return getMessages(query);
    }

    private List<Message> getMessages(String query) throws SQLException {
        connect();
        ResultSet re = select(query);

        List<Message> data = new ArrayList<>();

        while (re.next()) {
            data.add(
                    new Message
                            (
                            re.getInt("IdMessage"),
                            re.getInt("IdSender"),
                            re.getInt("IdReciver"),
                            re.getString("Message"))

            );
        }
        close();
        return data;
    }


    public void sendToProjectManager() {
        String query = String.format("INSERT INTO messages (IdSender,IdUserTypeReciver,Message) VALUES (%s,%s,'%s')",
                getIdSender(),2,getMessage());
        excuteDbOperation(query);

    }
    public void send() {
        String query = String.format("INSERT INTO messages (IdSender,IdReciver,Message) VALUES (%s,%s,'%s')",
                getIdSender(),getIdReciver(),getMessage());
        excuteDbOperation(query);

    }

    public Message() {
    }

    public Message(int idMessage, int idSender, int idReciver, String message) {
        IdMessage = idMessage;
        IdSender = idSender;
        IdReciver = idReciver;
        Message = message;
    }



    public int getIdUserTypeReciver() {
        return IdUserTypeReciver;
    }

    public void setIdUserTypeReciver(int idUserTypeReciver) {
        IdUserTypeReciver = idUserTypeReciver;
    }

    public int getIdMessage() {
        return IdMessage;
    }

    public void setIdMessage(int idMessage) {
        IdMessage = idMessage;
    }

    public int getIdSender() {
        return IdSender;
    }

    public void setIdSender(int idSender) {
        IdSender = idSender;
    }

    public int getIdReciver() {
        return IdReciver;
    }

    public void setIdReciver(int idReciver) {
        IdReciver = idReciver;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
