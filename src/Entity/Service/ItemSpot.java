package Entity.Service;

import Controller.ServiceController;
import Entity.Player;
import SuperEntity.Stuff;
import Util.PointType;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/3.
 * Item Spot
 */
public class ItemSpot extends Stuff {
    public ItemSpot() {
        setType(PointType.ItemSpot);
    }
    @Override
    public void service(Player player) {
        helloAction.itemSpotHello(this);
        serviceAction.itemSpotService(player);
    }

    @Override
    public void service(JPanel panel, Player player) {
        new ServiceController().itemSpotService(panel, this, player);
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
