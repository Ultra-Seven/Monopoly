package Action;

import Entity.Player;
import Entity.Point;
import SuperEntity.Item;

/**
 * Created by lenovo on 2016/4/30.
 */
public interface IItemAction {
    public void displayItems(Player player);
    public void averageMoney(Player player, Item item);
    public void barrier(Player player, Item item);
    public void controlDice(Player player, Item item);
    public void robItem(Player player, Item item);
    public void steering(Player player, Item item);
    public void stop(Player player, Item item);
    public void payTax(Player player, Item item);
}
