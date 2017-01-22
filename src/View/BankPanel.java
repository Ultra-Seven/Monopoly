package View;

import Util.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Created by lenovo on 2016/6/9.
 * The GUI panel of bank service
 */
public class BankPanel extends JPanel {
    private JButton confirm = new JButton();
    private JButton cancel = new JButton();
    private JRadioButton save = new JRadioButton("save", true);
    private JTextField input = new JTextField("save/withdraw money");
    public BankPanel() {
        setLayout(null);
        setSize(334, 212);
        JLabel welcome = new JLabel("Welcome to our bank!");
        ButtonGroup group = new ButtonGroup();
        JRadioButton draw = new JRadioButton("withdraw");
        group.add(save);
        group.add(draw);
        confirm.setIcon(new ImageIcon("pics/confirm.png"));
        cancel.setIcon(new ImageIcon("pics/return.png"));
        JPanel jpanel = new JPanel();
        jpanel.add(save); jpanel.add(draw);
        Utility.initComponent(this, welcome, 200, 30, (this.getWidth() - 200) / 2, 30, Color.WHITE);
        Utility.initComponent(this, jpanel, 200, 50, (this.getWidth() - 200) / 2, 80, Color.WHITE);
        Utility.initComponent(this, input, 200, 30, (this.getWidth() - 200) / 2, 130, Color.WHITE);
        Utility.initComponent(this, confirm, 148, 37, 20, 165, null);
        Utility.initComponent(this, cancel, 148, 37, 166, 165, null);
        add(input);
        input.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                input.setText("");
            }
        });
    }

    public JButton getCancel() {
        return cancel;
    }

    public JButton getConfirm() {
        return confirm;
    }

    public JTextField getInput() {
        return input;
    }

    public JRadioButton getSave() {
        return save;
    }
}
