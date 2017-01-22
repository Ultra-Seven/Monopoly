package Entity.Items;

import Controller.ItemController;
import Entity.Player;
import SuperEntity.Item;
import Util.ItemType;
import Util.Map;

/**
 * Created by lenovo on 2016/4/3.
 * Stay at the place one more round
 */
public class StopCard extends Item {
    public StopCard() {
        this.itemType = ItemType.StopCard;
        this.ticket = 20;
    }
    public StopCard(Player player) {
        this.player = player;
        this.itemType = ItemType.StopCard;
        this.ticket = 20;
    }
    public void useItem() {
        itemAction.stop(player, this);
    }

    @Override
    public void useGUI() {
        new ItemController().stop(player, this);
    }
}
