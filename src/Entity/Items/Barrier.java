package Entity.Items;

import Controller.ItemController;
import Entity.Player;
import Entity.Point;
import SuperEntity.Item;
import Util.ItemType;
import Util.Utility;

import java.util.Scanner;

/**
 * Created by lenovo on 2016/4/3.
 */
public class Barrier extends Item {
    private static int range;
    public Barrier() {
        this.itemType = ItemType.Barrier;
        this.range = 8;
        this.ticket = 20;
    }
    public Barrier(Player player) {
        this.itemType = ItemType.Barrier;
        this.range = 8;
        this.player = player;
        this.ticket = 20;
    }
    public static int getRange() {
        return range;
    }
    public void useItem() {
        itemAction.barrier(player, this);
    }

    @Override
    public void useGUI() {
        new ItemController().setBarrier(player, this);
    }
}
