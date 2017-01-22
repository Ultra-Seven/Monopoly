package Util;

import javax.swing.*;

/**
 * Created by lenovo on 2016/6/7.
 */
public enum HouseImage {
    HOUSE_IMAGE1, HOUSE_IMAGE2, HOUSE_IMAGE3, HOUSE_IMAGE4;
    public static ImageIcon[] getHouseImage(HouseImage image) {
        switch (image) {
            case HOUSE_IMAGE1:{
                return new ImageIcon[]{new ImageIcon("pics/ownerA0.png"), new ImageIcon("pics/ownerA1.png"), new ImageIcon("pics/ownerA2.png"), new ImageIcon("pics/ownerA3.png"), new ImageIcon("pics/ownerA4.png")
                , new ImageIcon("pics/ownerA5.png")};
            }
            case HOUSE_IMAGE2:{
                return new ImageIcon[]{new ImageIcon("pics/ownerB0.png"), new ImageIcon("pics/ownerB1.png"), new ImageIcon("pics/ownerB2.png"), new ImageIcon("pics/ownerB3.png"), new ImageIcon("pics/ownerB4.png")
                        , new ImageIcon("pics/ownerB5.png")};
            }
            case HOUSE_IMAGE3:{
                return new ImageIcon[]{new ImageIcon("pics/ownerA0.png"), new ImageIcon("pics/ownerA1.png"), new ImageIcon("pics/ownerA2.png"), new ImageIcon("pics/ownerA3.png"), new ImageIcon("pics/ownerA4.png")
                        , new ImageIcon("pics/ownerA5.png")};
            }
            case HOUSE_IMAGE4:{
                return new ImageIcon[]{new ImageIcon("pics/ownerA0.png"), new ImageIcon("pics/ownerA1.png"), new ImageIcon("pics/ownerA2.png"), new ImageIcon("pics/ownerA3.png"), new ImageIcon("pics/ownerA4.png")
                        , new ImageIcon("pics/ownerA5.png")};
            }
            default:
                return null;
        }
    }
}
