package Entity.Service;


import Controller.ServiceController;
import Entity.Player;
import SuperEntity.Stuff;
import Util.PointType;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/3.
 * Bank entity
 */
public class Bank extends Stuff {
    public Bank() {
        setType(PointType.Bank);
    }
    @Override
    public void service(Player player) {
        helloAction.bankHello(this);
        while (true) {
            if (!serviceAction.bankService(player, this))
                break;
        }
    }

    @Override
    public void service(JPanel panel, Player player) {
        new ServiceController().bankService(panel, this, player);
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
