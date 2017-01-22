package Util;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/6.
 * The different type of point
 */
public enum PointType {
    House, ItemShop, Bank, News, ItemSpot, TicketSpot, Vacancy, Lottery, Hospital;
    public static ImageIcon getImage(PointType type) {
        switch (type) {
            case House: return Utility.getImageIcon(new ImageIcon("pics/noowner0.png"), 40, 40);
            case ItemShop: return Utility.getImageIcon(new ImageIcon("pics/toolshop.png"), 40, 50);
            case Bank: return Utility.getImageIcon(new ImageIcon("pics/bank.jpg"), 40, 40);
            case News: return Utility.getImageIcon(new ImageIcon("pics/news.png"), 40, 40);
            case ItemSpot: return Utility.getImageIcon(new ImageIcon("pics/spot.png"), 40, 40);
            case TicketSpot: return Utility.getImageIcon(new ImageIcon("pics/quan.png"), 40, 40);
            case Vacancy: return Utility.getImageIcon(new ImageIcon("pics/moon.jpg"), 40, 40);
            case Lottery: return Utility.getImageIcon(new ImageIcon("pics/lottery.jpg"), 40, 40);
            case Hospital: return Utility.getImageIcon(new ImageIcon("pics/hospital.png"), 40, 40);
            default: return Utility.getImageIcon(new ImageIcon("pics/moon.jpg"), 40, 40);
        }
    }
}
