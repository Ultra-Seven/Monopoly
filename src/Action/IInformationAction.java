package Action;

import Entity.Player;

/**
 * Created by lenovo on 2016/4/29.
 */
public interface IInformationAction {
    public void alarm(Player player);
    public void printAsset();
    public void specificInformation(Player player, int step);
}
