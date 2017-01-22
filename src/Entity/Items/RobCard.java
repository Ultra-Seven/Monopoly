package Entity.Items;

import Controller.ItemController;
import Entity.Player;
import SuperEntity.Item;
import Util.ItemType;

/**
 * Created by lenovo on 2016/4/3.
 */
public class RobCard extends Item {
    private static int range;
    public RobCard() {
        this.itemType = ItemType.RobCard;
        this.range = 5;
        this.ticket = 20;
    }
    public RobCard(Player player) {
        this.itemType = ItemType.RobCard;
        this.player = player;
        this.range = 5;
        this.ticket = 20;
    }

    public static int getRange() {
        return range;
    }

    public void useItem() {
        itemAction.robItem(player, this);
    }

    @Override
    public void useGUI() {
        new ItemController().robItem(player, this);
    }
}
