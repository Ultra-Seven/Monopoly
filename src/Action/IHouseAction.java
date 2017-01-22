package Action;

import Entity.Player;
import Entity.Service.House;

/**
 * Created by lenovo on 2016/4/27.
 * action of house service
 */
public interface IHouseAction {
    public boolean houseBuyService(Player player, House house);
    public boolean houseUpdateService(Player player, House house);
    public boolean houseIntrudeService(Player player, House house);
}
