package BizImpl;

import Biz.IItemShopBiz;
import Entity.Player;
import SuperEntity.Item;
import Util.ItemType;

/**
 * Created by lenovo on 2016/4/27.
 */
public class ItemShopBizImpl implements IItemShopBiz {

    @Override
    public void buyItem(Player player, Item item) {
        int ticket = item.getTicket();
        player.addItem(item);
        player.setTicket(player.getTicket() - ticket);
    }
}
