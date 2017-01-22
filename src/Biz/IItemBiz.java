package Biz;

import Entity.Player;
import Entity.Point;
import SuperEntity.Item;
import Util.ItemType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lenovo on 2016/4/3.
 */
public interface IItemBiz {
    public HashMap<ItemType, Integer> returnAllItems(Player player);
    //average card
    public int averageMoney(Player player, Item item);
    //barrier
    public void setBarrier(Player player, Point point, Item item);
    //dice card
    public void setDice(Player player, int dice, Item item);
    //rob card
    public Item robItem(Player player, Player target, Item item);
    //direction card
    public void changeDirection(Player player, Player target, Item item);
    //stop card
    public void stayAtPoint(Player player, Point point, Item item);
    //tax card
    public void payTax(Player player, ArrayList<Player> players, Item item);
    public ArrayList<Player> getRangePlayer(Player player, int range, boolean myself);
}
