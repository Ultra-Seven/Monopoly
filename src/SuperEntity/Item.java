package SuperEntity;

import ActionImpl.ItemActionImpl;
import Entity.Player;
import Entity.Point;
import Util.ItemType;

/**
 * Created by lenovo on 2016/4/3.
 */
public abstract class Item {
    //item type
    protected ItemType itemType;
    protected ItemActionImpl itemAction = new ItemActionImpl();
    //point when used
    protected Point point;
    //who use it
    protected Player player;
    //price
    protected int ticket;
    public Item() {

    }

    public ItemType getItemType() {
        return itemType;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    //the use function of DOS
    public void useItem() {

    }
    //the use function of GUI
    public abstract void useGUI();
}
