package Biz;

import Entity.Player;
import Entity.Service.House;

/**
 * Created by lenovo on 2016/4/27.
 */
public interface IHouseBiz {
    //buy house
    public void buyHouse(Player player, House house);
    //update house
    public void updateHouse(Player player, House house);
    //pay money when intruding others' house
    public void payHouse(Player player, House house);
    //sell house
    public House sellHouse(Player player);
    //refresh the state
    public void refresh(Player player);
}
