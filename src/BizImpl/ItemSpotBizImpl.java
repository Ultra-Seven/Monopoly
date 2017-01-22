package BizImpl;

import Biz.IItemSpotBiz;
import Entity.Player;
import SuperEntity.Item;
import Util.ItemType;

/**
 * Created by lenovo on 2016/4/27.
 */
public class ItemSpotBizImpl implements IItemSpotBiz {
    @Override
    public ItemType getItem(Player player) {
        int ran = (int)(Math.random() * (ItemType.values().length));
        ItemType type = ItemType.values()[ran];
        Item item = ItemType.getItem(type);
        item.setPlayer(player);
        player.addItem(item);
        return type;
    }
}
