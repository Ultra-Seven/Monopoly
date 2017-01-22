package Entity.Service;


import Controller.ServiceController;
import Entity.Player;
import SuperEntity.Stuff;
import Util.PointType;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/3.
 * Ticket Spot
 */
public class TicketSpot extends Stuff {
    public TicketSpot() {
        setType(PointType.TicketSpot);
    }
    @Override
    public void service(Player player) {
        helloAction.ticketSpotHello(this);
        serviceAction.ticketSpotService(player);
    }

    @Override
    public void service(JPanel panel, Player player) {
        new ServiceController().ticketService(panel, this, player);
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
