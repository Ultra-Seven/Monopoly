package Entity.Items;

import Controller.ItemController;
import Entity.Player;
import SuperEntity.Item;
import Util.ItemType;

/**
 * Created by lenovo on 2016/4/3.
 */
public class SteeringCard extends Item {
    private static int range;
    public SteeringCard() {
        this.itemType = ItemType.SteeringCard;
        this.range = 5;
        this.ticket = 20;
    }
    public SteeringCard(Player player) {
        this.itemType = ItemType.SteeringCard;
        this.player = player;
        this.range = 5;
        this.ticket = 20;
    }

    public static int getRange() {
        return range;
    }

    public void useItem() {
        itemAction.steering(player, this);
    }

    @Override
    public void useGUI() {
        new ItemController().steering(player, this);
    }
}
