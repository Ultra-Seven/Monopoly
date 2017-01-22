package Controller;

import BizImpl.ItemBizImpl;
import Entity.Items.RobCard;
import Entity.Items.SteeringCard;
import Entity.Items.TaxCard;
import Entity.Player;
import Entity.Point;
import SuperEntity.Item;
import Util.ItemType;
import Util.Map;
import Util.Utility;
import View.ItemShopPanel;
import View.Stage;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/9.
 */
public class ItemController implements IItemController {
    private ItemBizImpl itemBiz = new ItemBizImpl();
    @Override
    public void averageMoney(Player player, Item item) {
        int money = itemBiz.averageMoney(player, item);
        Stage.getUniqueInstance().refreshPlayer();
        JOptionPane.showMessageDialog(null, "The everyone's cash will $" + money + " !");
    }

    @Override
    public void setBarrier(Player player, Item item) {
        String[] options ={ "Front", "Back" };
        int m = JOptionPane.showOptionDialog(null, "Choose to set at the front or back ？", "Option",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (m != -1) {
            String input = JOptionPane.showInputDialog(null,"Please input the distance(0-8)：\n","Input",JOptionPane.PLAIN_MESSAGE);
            if (input != null) {
                if (Utility.validate(input, 8, 0)) {
                    int position = player.getPosition();
                    int size = Map.getUniqueInstance().getSize();
                    if ((m == 0 && player.isDirection()) || (m != 0 && !player.isDirection())) {
                        position = (position + Integer.parseInt(input)) % size;
                    }
                    else {
                        position = (position - Integer.parseInt(input) + size) % size;
                    }
                    Point point = Map.getUniqueInstance().getPoints().get(position);
                    itemBiz.setBarrier(player, point, item);
                    ItemShopPanel.getCurrentPanel().refreshNumber(ItemType.Barrier);
                    JOptionPane.showMessageDialog(null, "Set barrier successfully!");
                }
                else
                    JOptionPane.showMessageDialog(null, "Wrong input! Setting failed!");
            }
        }
    }

    @Override
    public void controlDice(Player player, Item item) {
        String[] obj2 ={ "1", "2", "3", "4", "5", "6" };
        String s = (String) JOptionPane.showInputDialog(null,"Input the number of the dice:\n", "Dice", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), obj2, "1");
        if(s != null) {
            int dice = Integer.parseInt(s);
            itemBiz.setDice(player, dice, item);
            ItemShopPanel.getCurrentPanel().refreshNumber(ItemType.ControlDice);
            JOptionPane.showMessageDialog(null, "You have the dice turn to " + dice + " compulsively!");
        }
    }

    @Override
    public void robItem(Player player, Item item) {
        ArrayList<Player> players = itemBiz.getRangePlayer(player, RobCard.getRange(),false);
        if (players.size() > 0) {
            String[] names = new String[players.size()];
            for (int i = 0; i < names.length; i++)
                names[i] = players.get(i).getName();
            String rival_st = (String) JOptionPane.showInputDialog(null,  "Choose a target you want to rob", "Rob", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), names, names[0]);
            if(rival_st != null) {
                Player rival = players.stream().filter(player1 -> player1.getName().equals(rival_st)).findFirst().orElse(null);
                ArrayList<Item> items = rival.getItems();
                if (items.size() == 0)
                    JOptionPane.showMessageDialog(null, "Sorry you robbed nothing because " + rival_st + " has nothing items!");
                else {
                    Item rob = itemBiz.robItem(player, rival, item);
                    ItemShopPanel.getCurrentPanel().refreshNumber(ItemType.RobCard);
                    ItemShopPanel.getCurrentPanel().refreshNumber(rob.getItemType());
                    JOptionPane.showMessageDialog(null, "You have managed to rob " + rival_st + " of " + rob.getItemType());
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No target!");
        }
    }

    @Override
    public void steering(Player player, Item item) {
        ArrayList<Player> players = itemBiz.getRangePlayer(player, SteeringCard.getRange(), true);
        if(players.size() > 0) {
            String[] names = new String[players.size()];
            for (int i = 0; i < names.length; i++)
                names[i] = players.get(i).getName();
            String target_st = (String) JOptionPane.showInputDialog(null,  "Choose a target you want to steer", "Steering", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), names, names[0]);
            if(target_st != null) {
                Player target = players.stream().filter(player1 -> player1.getName().equals(target_st)).findFirst().orElse(null);
                itemBiz.changeDirection(player, target, item);
                ItemShopPanel.getCurrentPanel().refreshNumber(ItemType.SteeringCard);
                JOptionPane.showMessageDialog(null, "You have managed to change " + target_st + "'s direction!");
            }
        }
        else
            JOptionPane.showMessageDialog(null, "No target!");
    }

    @Override
    public void stop(Player player, Item item) {
        Point point = Map.getUniqueInstance().getPoints().get(player.getPosition());
        itemBiz.stayAtPoint(player, point, item);
        ItemShopPanel.getCurrentPanel().refreshNumber(ItemType.StopCard);
        JOptionPane.showMessageDialog(null, "You have to stay one more round");
    }

    @Override
    public void payTax(Player player, Item item) {
        ArrayList<Player> players = itemBiz.getRangePlayer(player, TaxCard.getRange(), false);
        if (players.size() > 0) {
            itemBiz.payTax(player, players, item);
            ItemShopPanel.getCurrentPanel().refreshNumber(ItemType.TaxCard);
            Stage.getUniqueInstance().refreshPlayer();
            JOptionPane.showMessageDialog(null, "You have managed to face all players with 5 steps to pay 30 percent tax!");
        }
        else
            JOptionPane.showMessageDialog(null, "No target!");
    }
}
