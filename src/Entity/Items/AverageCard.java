package Entity.Items;

import Controller.ItemController;
import Entity.Player;
import SuperEntity.Item;
import Util.ItemType;

/**
 * Created by lenovo on 2016/4/3.
 */
public class AverageCard extends Item {
    private static int range;
    public AverageCard() {
        this.itemType = ItemType.AverageCard;
        this.ticket = 20;
    }
    public AverageCard(Player player) {
        this.itemType = ItemType.AverageCard;
        this.player = player;
        this.ticket = 20;
    }
    public void useItem() {
        itemAction.averageMoney(this.player, this);
    }

    @Override
    public void useGUI() {
        new ItemController().averageMoney(player, this);
    }
}
