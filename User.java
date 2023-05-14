package models;

import dataBase.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User extends Model {
    private int idUser;
    private String userName;
    private String email;
    private String password;
    private int idUserType;


    private boolean IsAdmin = false;
    private boolean IsPM = false;
    private boolean IsSp = false;
    private boolean IsCustomer = false;

    public boolean login(String mail, String password) throws SQLException {
        String query = String.format("Select * from users where Email='%s' and Password='%s'", mail, password);
        ResultSet re = select(query);
        int rowCount = 0;

        if (re.last()) {
            rowCount = re.getRow();
            re.beforeFirst();
        }

        if (rowCount > 0) {
            re.last();
            idUser = re.getInt("IdUser");
            idUserType = re.getInt("IdUserType");
            email = re.getString("Email");
            password = re.getString("Password");
            userName = re.getString("UserName");
            determinedUserType(idUserType);
        }
        close();

        return rowCount > 0;

    }

    private void determinedUserType(int idUserType) {
        switch (idUserType) {
            case 1:{
                IsAdmin=true;
                break;
            }
            case 2:{
                IsPM=true;
                break;
            }
            case 3:{
                IsSp=true;
                break;
            }
            case 4:{
                IsCustomer=true;
                break;
            }

        }
    }



    public List<User> getUserByType(int type) throws SQLException {
        String query = "Select * from users where IdUserType="+type;

        connect();
        ResultSet re = select(query);

        List<User> data=new ArrayList<>();

        while (re.next()){
           data.add(new User(
                   re.getInt("IdUser"),
                   re.getInt("IdUserType"),
                   re.getString("UserName"),
                   re.getString("Email"),
                   re.getString("Password")

           ));
        }
        close();
        return data;
    }



    public void update() {
        String query = String.format("update users set UserName='%s' , Email='%s' where IdUser=%s", userName,email,idUser);
        excuteDbOperation(query);
    }
    public void add() {
        String query = String.format("INSERT INTO users (IdUserType,UserName,Email,Password) VALUES (%s,'%s','%s','%s')",
                idUserType,userName,email,password);
        excuteDbOperation(query);

    }
    public void deleteUser(){
        String query = String.format("delete  from users where IdUser=%s", idUser);
        excuteDbOperation(query);


    }

    public User() { }
    public User(int idUser, int idUserType , String userName, String email, String password) {
        this.idUser = idUser;
        this.idUserType = idUserType;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(int idUserType , String userName, String email, String password) {
        this.idUserType = idUserType;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
    }

    public boolean isPM() {
        return IsPM;
    }

    public void setPM(boolean PM) {
        IsPM = PM;
    }

    public boolean isSp() {
        return IsSp;
    }

    public void setSp(boolean sp) {
        IsSp = sp;
    }

    public boolean isCustomer() {
        return IsCustomer;
    }

    public void setCustomer(boolean customer) {
        IsCustomer = customer;
    }
}
