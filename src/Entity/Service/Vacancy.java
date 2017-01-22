package Entity.Service;


import Controller.ServiceController;
import Entity.Player;
import SuperEntity.Stuff;
import Util.PointType;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/3.
 */
public class Vacancy  extends Stuff {
    public Vacancy() {
        setType(PointType.Vacancy);
    }
    @Override
    public void service(Player player) {
        helloAction.vacancyHello();
        serviceAction.vacancyService(player);
    }

    @Override
    public void service(JPanel panel, Player player) {
        new ServiceController().vacancyService(panel, this, player);
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
