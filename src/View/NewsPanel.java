package View;

import Util.Utility;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2016/6/9.
 * The GUI panel of news business
 */
public class NewsPanel extends JPanel {
    private String message;
    private JButton confirm = new JButton();
    public NewsPanel(String message) {
        setLayout(null);
        setSize(334, 212);
        JTextArea textField = new JTextArea(message);
        textField.setLineWrap(true);
        textField.setEnabled(false);
        confirm.setIcon(new ImageIcon("pics/confirm.png"));
        Utility.initComponent(this, textField, 250, 100, (this.getWidth() - 250)/2, (this.getHeight() - 100)/2 - 30, Color.WHITE);
        Utility.initComponent(this, confirm, 148, 37, (this.getWidth() - 148)/2, 165, null);

    }
    public void setMessage(String message) {
        this.message = message;
    }

    public JButton getConfirm() {
        return confirm;
    }
}
