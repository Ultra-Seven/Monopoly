package Entity.Service;


import Controller.ServiceController;
import Entity.Player;
import SuperEntity.Stuff;
import Util.PointType;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/3.
 * News entity
 */
public class News extends Stuff {
    public News() {
        setType(PointType.News);
    }
    @Override
    public void service(Player player) {
        helloAction.newsHello(this);
        serviceAction.newsService(player);
    }

    @Override
    public void service(JPanel panel, Player player) {
        new ServiceController().newsService(panel, this, player);
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
