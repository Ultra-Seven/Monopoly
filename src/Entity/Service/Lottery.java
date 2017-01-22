package Entity.Service;

import ActionImpl.ServiceActionImpl;
import Controller.ServiceController;
import Entity.Player;
import SuperEntity.Stuff;
import Util.PointType;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by lenovo on 2016/4/24.
 * Lottery entity
 */
public class Lottery extends Stuff {
    private static HashMap<Integer, Player> hashMap = new HashMap<Integer, Player>();
    public Lottery() {
        setType(PointType.ItemShop.Lottery);
    }
    private static int range = 200;
    private static int price = 200;
    @Override
    public void service(Player player) {
        helloAction.lotteryHello(this);
        while (true) {
            if (!serviceAction.lotteryService(player, this))
                break;
        }
    }

    @Override
    public void service(JPanel panel, Player player) {
        new ServiceController().lotteryService(panel, this, player);
    }

    public static void runLottery() {
        ServiceActionImpl.runLottery();
    }

    public static HashMap<Integer, Player> getHashMap() {
        return hashMap;
    }

    public static void setHashMap(HashMap<Integer, Player> hashMap) {
        Lottery.hashMap = hashMap;
    }

    public static int getRange() {
        return range;
    }

    public static int getPrice() {
        return price;
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
