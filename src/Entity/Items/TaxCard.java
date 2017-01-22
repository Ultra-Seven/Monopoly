package Entity.Items;

import Controller.ItemController;
import Entity.Player;
import SuperEntity.Item;
import Util.ItemType;

/**
 * Created by lenovo on 2016/4/3.
 */
public class TaxCard extends Item {
    private static int range;
    private static double rate;
    public TaxCard() {
        this.itemType = ItemType.TaxCard;
        this.range = 5;
        this.ticket = 20;
        rate = 0.3;
    }
    public TaxCard(Player player) {
        this.itemType = ItemType.TaxCard;
        this.range = 5;
        this.player = player;
        this.ticket = 20;
        rate = 0.3;
    }

    public static int getRange() {
        return range;
    }

    public static double getRate() {
        return rate;
    }

    public void useItem() {
        itemAction.payTax(player, this);
    }

    @Override
    public void useGUI() {
        new ItemController().payTax(player, this);
    }
}
