package Biz;

import Entity.Player;
import SuperEntity.Item;

/**
 * Created by lenovo on 2016/4/27.
 */
public interface IItemShopBiz {
    //buy item
    public void buyItem(Player player, Item item);
}
