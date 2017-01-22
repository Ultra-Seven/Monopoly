package View;

import BizImpl.ItemShopBizImpl;
import Entity.Player;
import SuperEntity.Item;
import Util.ItemType;
import Util.Logic;
import Util.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lenovo on 2016/6/9.
 * The GUI panel of item shop and item bag business
 */
public class ItemShopPanel extends JPanel {
    private JButton re = new JButton();
    private ItemType[] itemTypes = ItemType.values();
    private ItemPanel[] itemPanels = new ItemPanel[itemTypes.length];
    private ArrayList<ItemPanel> itemArray = new ArrayList<>();
    private static ItemShopPanel currentPanel;
    public ItemShopPanel() {
        currentPanel = this;
        setLayout(null);
        setSize(415, 343);
        re.setIcon(new ImageIcon("pics/return.png"));
        JLabel shop = new JLabel();
        shop.setIcon(Utility.getImageIcon(new ImageIcon("pics/shop.png"), 115, 40));
        Utility.initComponent(this, shop, 115, 40, (this.getWidth() - shop.getWidth()) / 2 - 50, 70, null);
        for (int i = 0; i< itemPanels.length; i++) {
            itemPanels[i] = new ItemPanel(itemTypes[i]);
            int x = i / 4;
            int y = i - x * 4;
            int index_x = y * (itemPanels[i].getWidth() + 26) + 60;
            int index_y = x * (itemPanels[i].getHeight() + 7) + 145;
            Utility.initComponent(this, itemPanels[i], itemPanels[i].getWidth(), itemPanels[i].getHeight(), index_x, index_y, null);
            final int finalI = i;
            itemPanels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount() == 2) {
                        Item item = ItemType.getItem(itemTypes[finalI]);
                        item.setPlayer(Logic.getUniqueInstance().getCurrentPlayer());
                        int ticket = item.getTicket();
                        if (Logic.getUniqueInstance().getCurrentPlayer().getTicket() >= ticket) {
                            new ItemShopBizImpl().buyItem(Logic.getUniqueInstance().getCurrentPlayer(), item);
                            Stage.getUniqueInstance().refreshPlayer();
                            JOptionPane.showMessageDialog(null, "You have bought " + item.getItemType() + " successfully!");
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "You need more tickets!");
                        }
                    }
                }
            });
            setOpaque(true);
            itemPanels[i].setToolTipText(itemTypes[i].name());
        }
        Utility.initComponent(this, re, 148, 37, (this.getWidth() - 148) / 2, 300, null);
    }
    public ItemShopPanel(HashMap<ItemType, Integer> hashMap) {
        currentPanel = this;
        setLayout(null);
        setSize(415, 343);
        Iterator iter = hashMap.entrySet().iterator();
        int index = 0;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            ItemType key = (ItemType) entry.getKey();
            Integer val = (Integer) entry.getValue();
            ItemPanel itemPanel = new ItemPanel(key);
            itemPanel.getNumber().setText(String.valueOf(val));
            int x = index / 4;
            int y = index - x * 4;
            int index_x = y * (itemPanel.getWidth() + 26) + 60;
            int index_y = x * (itemPanel.getHeight() + 7) + 145;
            Utility.initComponent(this, itemPanel, itemPanel.getWidth(), itemPanel.getHeight(), index_x, index_y, null);
            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount() == 2) {
                        Player player = Logic.getUniqueInstance().getCurrentPlayer();
                        Item item = player.getItems().stream().filter(item1 -> item1.getItemType() == key).findFirst().orElse(null);
                        if(item != null) {
                            item.setPoint(Util.Map.getUniqueInstance().getPoints().get(player.getPosition()));
                            item.useGUI();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "You have no such an item!");
                        }
                    }
                }
            });
            itemPanel.setToolTipText(key.name());
            index++;
            itemArray.add(itemPanel);
            Utility.initComponent(this, itemPanel.getNumber(), 30, 30, itemPanel.getX() - 20, itemPanel.getY() - 20, Color.WHITE);
        }
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(new ImageIcon("pics/bag.png").getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
    }

    public JButton getRe() {
        return re;
    }

    public ItemPanel[] getItemPanels() {
        return itemPanels;
    }

    public void refreshNumber(ItemType type) {
        ItemPanel panel = itemArray.stream().filter(e -> e.getItemType() == type).findFirst().orElse(null);
        long count = Logic.getUniqueInstance().getCurrentPlayer().getItems().stream().filter(e->e.getItemType() == type).count();
        panel.getNumber().setText(String.valueOf(count));
    }

    public static ItemShopPanel getCurrentPanel() {
        return currentPanel;
    }

    public static void setCurrentPanel(ItemShopPanel currentPanel) {
        ItemShopPanel.currentPanel = currentPanel;
    }
}
class ItemPanel extends JPanel {
    private ImageIcon imageIcon;
    private ItemType itemType;
    private JLabel number = new JLabel();
    ItemPanel(ItemType itemType) {
        setSize(60, 59);
        this.imageIcon = ItemType.getImage(itemType);
        this.itemType = itemType;
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                try {
//                    Thread.sleep(1000);
//                    JOptionPane.showMessageDialog(null, itemType.name());
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(Utility.getImageIcon(imageIcon, this.getWidth(), this.getHeight()).getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
    }

    public ItemType getItemType() {
        return itemType;
    }

    public JLabel getNumber() {
        return number;
    }

    public void setNumber(JLabel number) {
        this.number = number;
    }
}
