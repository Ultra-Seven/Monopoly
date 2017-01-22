package ActionImpl;

import Action.IItemAction;
import BizImpl.ItemBizImpl;
import Entity.Items.Barrier;
import Entity.Items.RobCard;
import Entity.Items.SteeringCard;
import Entity.Items.TaxCard;
import Entity.Player;
import Entity.Point;
import SuperEntity.Item;
import Util.ItemType;
import Util.Map;
import Util.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by lenovo on 2016/4/30.
 */
public class ItemActionImpl implements IItemAction {
    private ItemBizImpl itemBiz = new ItemBizImpl();
    @Override
    public void displayItems(Player player) {
        HashMap<ItemType, Integer> hashMap = itemBiz.returnAllItems(player);
        HashMap<Integer,ItemType> itemTypeHashMap = new HashMap<Integer,ItemType>();
        if (hashMap.size() > 0) {
            System.out.println("Now you can choose one of items to use");
            int index = 0;
            for (Iterator iterator = hashMap.keySet().iterator(); iterator.hasNext(); ) {
                index ++;
                ItemType key = (ItemType) iterator.next();
                itemTypeHashMap.put(index, key);
                System.out.println(index + ". " + key + "\t" + hashMap.get(key));
            }
            System.out.print("Your choice(q:quit):");
            String input = Utility.input();
            if (Utility.validate(input,index, 1)) {
                final ItemType itemType = itemTypeHashMap.get(Integer.parseInt(input));
                Item item = player.getItems().stream().filter(e->(e.getItemType() == itemType)).findFirst().orElse(null);
                if (item != null) {
                    item.setPoint(Map.getUniqueInstance().getPoints().get(player.getPosition()));
                    item.useItem();
                }
            }
            else if(input.equals("q")) {

            }
            else
                System.out.println("Wrong input!");
        }
        else
            System.out.println("You have no items");
    }

    @Override
    public void averageMoney(Player player, Item item) {
        System.out.println("-------------Average Card works-------------");
        System.out.println("The everyone's cash will $" + itemBiz.averageMoney(player, item) + " !");
    }

    @Override
    public void barrier(Player player, Item item) {
        System.out.print("Front or Back?(f:front, b:back)");
        String input = Utility.input();
        if (!(input.equals("f") || input.equals("b"))) {
            System.out.println("Wrong input!");
        }
        else {
            int range = Barrier.getRange();
            System.out.println("How far?(0-" + range + ")" );
            int mask = (input.equals("f"))? 1 : (-1);
            String input2 = Utility.input();
            if(Utility.validate(input2, range, 0)) {
                int size = Map.getUniqueInstance().getPoints().size();
                int step = (Integer.parseInt(input2) * mask + player.getPosition() + size) % size;
                Point point = Map.getUniqueInstance().getPoints().get(step);
                System.out.println("-------------Barrier works-------------");
                System.out.println("You set a barrier at " + point.getStuff().get(0).getName() + " successfully!");
                itemBiz.setBarrier(player, point, item);
            }
            else {
                System.out.println("Wrong input!");
            }
        }
    }

    @Override
    public void controlDice(Player player, Item item) {
        System.out.print("Please input the target dice:");
        String input = Utility.input();
        if (Utility.validate(input, 6, 1)) {
            int dice = Integer.parseInt(input);
            System.out.println("-------------Control Dice works-------------");
            System.out.println("You have the dice turn to " + dice + " compulsively!");
            itemBiz.setDice(player, dice, item);
        }
        else {
            System.out.println("Wrong input!");
        }
    }

    @Override
    public void robItem(Player player, Item item) {
        ArrayList<Player> players = itemBiz.getRangePlayer(player, RobCard.getRange(),false);
        if (players.size() > 0) {
            System.out.println("Your targets:");
            for (int i = 0; i < players.size(); i++) {
                System.out.println((i+1) + ". " + players.get(i).getName());
            }
            System.out.print("Your choice:");
            String input = Utility.input();
            if (Utility.validate(input, players.size(), 1)) {
                Player rival = players.get(Integer.parseInt(input)-1);
                ArrayList<Item> items = rival.getItems();
                if (items.size() == 0) {
                    System.out.println("Sorry you robbed nothing because " + rival.getName() + " has nothing items!");
                } else {
                    Item rob = itemBiz.robItem(player, rival, item);
                    System.out.println("-------------Rob Card works-------------");
                    System.out.println("You have managed to rob " + rival.getName() + " of " + rob.getItemType());
                }
            }
            else {
                System.out.println("Wrong input!");
            }
        }
        else {
            System.out.println("No one is adjacent to you!");
        }
    }

    @Override
    public void steering(Player player, Item item) {
        ArrayList<Player> players = itemBiz.getRangePlayer(player, SteeringCard.getRange(), true);
        System.out.println("Choose your target:");
        for (int i = 0; i < players.size(); i++)
            System.out.println((i+1) + ". " + players.get(i).getName());
        System.out.print("Your choice:");
        String input = Utility.input();
        if (Utility.validate(input, players.size(), 1)) {
            Player target = players.get(Integer.parseInt(input) - 1);
            itemBiz.changeDirection(player, target, item);
            System.out.println("-------------Steering Card works-------------");
            System.out.println("You have managed to change " + target.getName() + "'s direction!");
        }
        else {
            System.out.println("Wrong input!");
        }
    }

    @Override
    public void stop(Player player, Item item) {
        Point point = Map.getUniqueInstance().getPoints().get(player.getPosition());
        itemBiz.stayAtPoint(player, point, item);
        System.out.println("-------------Stop Card works-------------");
        System.out.println("You have to stay one more round");
    }

    @Override
    public void payTax(Player player, Item item) {
        ArrayList<Player> players = itemBiz.getRangePlayer(player, TaxCard.getRange(), false);
        if (players.size() > 0) {
            itemBiz.payTax(player, players, item);
            System.out.println("-------------Tax Card works-------------");
            System.out.println("You have managed to face all players with 5 steps to pay 30 percent tax!");
        }
        else {
            System.out.println("No one is adjacent to you!");
        }
    }
}
