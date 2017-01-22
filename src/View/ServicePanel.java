package View;

import Entity.Service.Hospital;
import Util.Logic;
import Util.Utility;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2016/6/9.
 */

public class ServicePanel extends JPanel {
    public ServicePanel() {}
    public ServicePanel(JPanel panel) {
        setLayout(null);
        setSize(334, 212);
        int x = (this.getWidth() - panel.getWidth()) / 2;
        int y = (this.getHeight() - panel.getHeight()) / 2;
        Utility.initComponent(this, panel, panel.getWidth(), panel.getHeight(), x, y, null);
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(new ImageIcon("pics/message.png").getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
    }

    public void refresh() {
        this.setVisible(false);
    }
    public void process() {
        this.setVisible(false);
        while (true) {
            Logic.getUniqueInstance().process();
            if(Hospital.getRound(Logic.getUniqueInstance().getCurrentPlayer()) == 0) {
                Stage.getUniqueInstance().getMapDisplay().addListener();
                Stage.getUniqueInstance().refreshState();
                break;
            }
            else {
                Stage.getUniqueInstance().refreshState();
                JOptionPane.showMessageDialog(null, "During the treatment...You must stay at hospital!");
                Hospital.reduceCount();
            }
        }
    }
}

