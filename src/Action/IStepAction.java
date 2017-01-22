package Action;

import Entity.Player;

/**
 * Created by lenovo on 2016/4/29.
 */
public interface IStepAction {
    public void printCurrentMap();
    public void printOriginalMap();
    public void go(Player player);
    public void useItem(Player player);
    public void alertDangerous(Player player);
    public void specificInformation(Player player);
    public void assertInformation();
    public void giveUp(Player player);
    public void stockMarket(Player player);
}
