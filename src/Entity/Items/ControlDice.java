package Entity.Items;

import Controller.ItemController;
import Entity.Player;
import SuperEntity.Item;
import Util.Dice;
import Util.ItemType;

/**
 * Created by lenovo on 2016/4/3.
 */
public class ControlDice extends Item {
    private int dice;
    private static int range;
    public ControlDice() {
        this.itemType = ItemType.ControlDice;
        this.ticket = 20;
    }
    public ControlDice(Player player) {
        this.itemType = ItemType.ControlDice;
        this.player = player;
        this.ticket = 20;
    }

    public int getDice() {
        return dice;
    }

    public void useItem() {
        itemAction.controlDice(player, this);
    }

    @Override
    public void useGUI() {
        new ItemController().controlDice(player, this);
    }
}
