package Helpers;


import models.Event;
import models.Message;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class Helpers {

    public static String[] getUsersNamesArray(List<User> userList){
        List<String> strings=new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            strings.add(userList.get(i).getUserName());
        }
        return strings.toArray(new String[strings.size()]);
    }

    public static String[] getEventsNameArray(List<Event> eventsList){
        List<String> strings=new ArrayList<>();
        for (int i = 0; i < eventsList.size(); i++) {
            strings.add(eventsList.get(i).getEventName());
        }
        return strings.toArray(new String[strings.size()]);
    }


    public static String[] getPriceEventNameFormatted(List<Event> eventsList){
        List<String> strings=new ArrayList<>();
        for (int i = 0; i < eventsList.size(); i++) {
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("Event Name : ");
            stringBuilder.append(eventsList.get(i).getEventName());
            stringBuilder.append(" Event Price ->");
            stringBuilder.append(eventsList.get(i).getPrice()+"");
            strings.add(stringBuilder.toString());
        }
        return strings.toArray(new String[strings.size()]);
    }


    public static String[] getMessages(List<Message> messages){
        List<String> strings=new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            strings.add(messages.get(i).getMessage());
        }
        return strings.toArray(new String[strings.size()]);
    }





}
