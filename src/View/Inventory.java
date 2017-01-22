package View;

import javax.swing.*;
import java.awt.*;

class Inventory extends JPanel {
    private boolean down = true;
    Inventory() {
        setSize(100, 100);
        setLayout(null);
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(new ImageIcon("pics/toolbag.png").getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}