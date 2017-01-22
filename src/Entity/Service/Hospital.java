package Entity.Service;

import Entity.Player;
import SuperEntity.Stuff;
import Util.PointType;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lenovo on 2016/6/10.
 * Hospital entity
 */
public class Hospital extends Stuff{
    private static HashMap<Player, Integer> count = new HashMap<>();
    public Hospital() {
        setType(PointType.Hospital);
    }
    @Override
    public void service(Player player) {

    }

    @Override
    public void service(JPanel panel, Player player) {
        JOptionPane.showMessageDialog(null, "This is hospital");
    }

    @Override
    public void printInformation() {

    }

    @Override
    public String getInformation() {
        return "Type: " + getType() + "\nName: " + getName();
    }
    //get the remaining round of a specific player
    public static int getRound(Player player) {
        return (count.get(player) == null) ? 0 : count.get(player);
    }
    //reduce the round of a player
    public static void reduceCount() {
        Iterator iterator = count.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Player player = (Player) entry.getKey();
            Integer val = (Integer) entry.getValue();
            int temp = --val;
            if (val == 0)
                count.remove(player);
            else
                count.put(player, temp);
        }
    }
    public void addToHospital(Player player) {
        count.put(player, 2);
    }
}
