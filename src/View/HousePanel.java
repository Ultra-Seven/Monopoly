package View;

import ActionImpl.HouseActionImpl;
import BizImpl.HouseBizImpl;
import BizImpl.StepBizImpl;
import Entity.Player;
import Entity.Service.House;
import Util.Logic;
import Util.Map;
import Util.Utility;
import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2016/6/9.
 * The GUI panel of house business
 */
public class HousePanel extends JPanel {
    private House house;
    private HouseBizImpl houseBiz = new HouseBizImpl();
    private ServicePanel servicePanel;
    public HousePanel() {
        setLayout(null);
        setSize(334, 212);
    }
    public void buy() {
        JTextArea label = new JTextArea("Welcome to the " + house.getName() + " with level-" + house.getLevel() + " !\r\n" +
                "Now do you want to buy it paying $" + house.getCost() + " ?");
        label.setLineWrap(true);
        JButton confirm = new JButton();
        confirm.setIcon(new ImageIcon("pics/confirm.png"));
        JButton cancel = new JButton();
        cancel.setIcon(new ImageIcon("pics/return.png"));
        Utility.initComponent(this, label, 250, 100, (this.getWidth() - 250)/2, (this.getHeight() - 100)/2 - 30, Color.WHITE);
        Utility.initComponent(this, confirm, 148, 37, 20, 165, null);
        Utility.initComponent(this, cancel, 148, 37, 166, 165, null);
        confirm.addActionListener(e -> {
            Player player = Logic.getUniqueInstance().getCurrentPlayer();
            if(Utility.canAfford(player, house.getCost())) {
                houseBiz.buyHouse(player, house);
                Stage.getUniqueInstance().refreshPlayer();
                JOptionPane.showMessageDialog(null, "Congratulations! You become the owner of " + house.getName());
            }
            else
                JOptionPane.showMessageDialog(null, "Sorry, your cash is not enough... Failed");
            servicePanel.process();
        });
        cancel.addActionListener(e -> servicePanel.process());

    }
    public void update() {
        if(house.getLevel() < 6) {
            int cost = house.getOriginalCost() >> 1;
            JTextArea label = new JTextArea("Welcome to your house " + house.getName() + " with level-" + house.getLevel() + " !\r\n" +
                    "Now do you want to expand your house paying $" + cost + " ?");
            label.setLineWrap(true);
            JButton confirm = new JButton();
            confirm.setIcon(new ImageIcon("pics/confirm.png"));
            JButton cancel = new JButton();
            cancel.setIcon(new ImageIcon("pics/return.png"));
            Utility.initComponent(this, label, 250, 100, (this.getWidth() - 250)/2, (this.getHeight() - 100)/2 - 30, Color.WHITE);
            Utility.initComponent(this, confirm, 148, 37, 20, 165, null);
            Utility.initComponent(this, cancel, 148, 37, 166, 165, null);
            confirm.addActionListener(e -> {
                Player player = Logic.getUniqueInstance().getCurrentPlayer();
                if(Utility.canAfford(player, cost)) {
                    houseBiz.updateHouse(player, house);
                    Stage.getUniqueInstance().refreshPlayer();
                    JOptionPane.showMessageDialog(null, "Congratulations! You You have expand your house to level-" + house.getLevel() + "!");
                }
                else
                    JOptionPane.showMessageDialog(null, "Sorry, your cash is not enough... Failed");
                servicePanel.process();
            });
            cancel.addActionListener(e -> servicePanel.process());
        }
        else {
            JTextArea label = new JTextArea("Welcome to your house " + house.getName() + " with level-" + house.getLevel() + " !\r\n" +
                    "But the level is the highest!");
            label.setLineWrap(true);
            JButton confirm = new JButton();
            confirm.setIcon(new ImageIcon("pics/confirm.png"));
            Utility.initComponent(this, label, 250, 100, (this.getWidth() - 250)/2, (this.getHeight() - 100)/2 - 30, Color.WHITE);
            Utility.initComponent(this, confirm, 148, 37, (this.getWidth() - 148)/2, 165, null);
            confirm.addActionListener(e -> servicePanel.process());
        }
    }
    public void pay() {
        String message = "It is illegal to intrude other's house.\n";
        int street = new HouseActionImpl().streetAdding(house);
        String streetName = Map.getUniqueInstance().getStreetName();
        int profit = house.getProfit() * street;
        Player owner = house.getOwner();
        Player player = Logic.getUniqueInstance().getCurrentPlayer();
        if (street == 1) {
            message = message + "So you should pay $" + profit + " to " + owner.getName();
        }
        else {
            message = message + "Because of entering " + streetName +" you must pay $" + profit + " to " + owner.getName();
        }
        JTextArea label = new JTextArea(message);
        label.setLineWrap(true);
        JButton confirm = new JButton();
        confirm.setIcon(new ImageIcon("pics/confirm.png"));
        Utility.initComponent(this, label, 250, 100, (this.getWidth() - 250)/2, (this.getHeight() - 100)/2 - 30, Color.WHITE);
        Utility.initComponent(this, confirm, 148, 37, (this.getWidth() - 148)/2, 165, null);
        confirm.addActionListener(e -> {
            if (Utility.canAfford(player, profit)) {
                houseBiz.payHouse(player, house);
                Stage.getUniqueInstance().refreshPlayer();
            }
            else {
                if (Utility.canAfford(player, profit-player.getDeposit())) {
                    houseBiz.payHouse(player, house);
                    Stage.getUniqueInstance().refreshPlayer();
                    JOptionPane.showMessageDialog(null, "You don't have enough cash. So you must withdraw money from the bank!");
                }
                else {
                    while (true) {
                        if(player.getHouses().size() > 0) {
                            House house_sell = houseBiz.sellHouse(player);
                            Stage.getUniqueInstance().refreshPoint(house_sell.getPosition());
                            JOptionPane.showMessageDialog(null, "Sorry, because of debts you have to sell " + house_sell.getName());
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Er...You have gone bankruptcy!");
                            houseBiz.payHouse(player, house);
                            StepBizImpl stepBiz = new StepBizImpl();
                            stepBiz.removePlayer(player);
                            break;
                        }
                        if (Utility.canAfford(player, profit-player.getDeposit())) {
                            houseBiz.payHouse(player, house);
                            Stage.getUniqueInstance().refreshPlayer();
                            break;
                        }
                    }
                }
            }
            servicePanel.process();
        });

    }
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public void setServicePanel(ServicePanel servicePanel) {
        this.servicePanel = servicePanel;
    }

}
