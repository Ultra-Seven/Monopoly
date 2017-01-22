package View;

import Entity.Service.Lottery;
import Util.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Created by lenovo on 2016/6/9.
 * The GUI panel of lottery business
 */
public class LotteryPanel extends JPanel{
    private JTextField textField = new JTextField("Please input your lucky number");
    private JButton confirm = new JButton();
    private JButton cancel = new JButton();
    public LotteryPanel() {
        setLayout(null);
        setSize(334, 212);
        JLabel label = new JLabel("Welcome to our lottery site!\n");
        confirm.setIcon(new ImageIcon("pics/confirm.png"));
        cancel.setIcon(new ImageIcon("pics/return.png"));
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText("");
            }
        });
        Utility.initComponent(this, label, 250, 30, (this.getWidth() - 250) / 2, 30, Color.WHITE);
        Utility.initComponent(this, textField, 200, 30, (this.getWidth() - 200) / 2, 100, Color.WHITE);
        Utility.initComponent(this, confirm, 148, 37, 20, 165, null);
        Utility.initComponent(this, cancel, 148, 37, 166, 165, null);
    }

    public JButton getCancel() {
        return cancel;
    }

    public JButton getConfirm() {
        return confirm;
    }

    public JTextField getTextField() {
        return textField;
    }
}
