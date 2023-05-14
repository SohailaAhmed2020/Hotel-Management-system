package ui;

import javax.swing.*;
import java.awt.*;

public class BaseForm {
    private JFrame frame;
    private JPanel jp;
    private String screenName;

    public BaseForm(String screenName) {
       this.screenName=screenName;

    }

    public void inflateLayout() {
        frame = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 4 - frame.getSize().width / 2, dim.height / 6 - frame.getSize().height / 2);
        frame.setContentPane(jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle(screenName);
        frame.setSize(600, 600);
    }
    public void onCloseOperationHide(){
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void showError(String title, String message) {
        JOptionPane.showMessageDialog(jp,
                message,
                title,
                JOptionPane.WARNING_MESSAGE);
    }

    public JFrame getFrame() {
        return frame;
    }
    public void setJpanel(JPanel jPanel){
        this.jp=jPanel;

    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }
}
