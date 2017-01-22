package Entity.Service;


import Controller.ServiceController;
import Entity.Player;
import SuperEntity.Stuff;
import Util.PointType;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/3.
 * Item shopo
 */
public class ItemShop extends Stuff {
    public ItemShop() {
        setType(PointType.ItemShop);
    }
    @Override
    public void service(Player player) {
        helloAction.itemShopHello(this);
        while (true) {
            if (!serviceAction.itemShopService(player))
                break;
        }
    }

    @Override
    public void service(JPanel panel, Player player) {
        new ServiceController().itemShopService(panel, this, player);
    }

    @Override
    public void printInformation() {
        System.out.println("Type: " + getType());
        System.out.println("Name: " + getName());
    }

    @Override
    public String getInformation() {
        return "Type: " + getType() + "\nName: " + getName();
    }
}
