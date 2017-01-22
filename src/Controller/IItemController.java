package Controller;

import Entity.Player;
import SuperEntity.Item;

/**
 * Created by lenovo on 2016/6/9.
 * The item function interface
 */
public interface IItemController {
    //average card
    public void averageMoney(Player player, Item item);
    //set a barrier in a specific position
    public void setBarrier(Player player, Item item);
    //set a specific dice number
    public void controlDice(Player player, Item item);
    //rob a item from a rival
    public void robItem(Player player, Item item);
    //change a direction of a player
    public void steering(Player player, Item item);
    //stay at the place
    public void stop(Player player, Item item);
    //pay the tax
    public void payTax(Player player, Item item);
}
