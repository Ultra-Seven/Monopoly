package Entity.IState;

import Entity.Player;
import Entity.Service.House;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/25.
 * state of house
 */
public interface State {
    //buy house function
    public void buy(Player player, House house);
    //update the house
    public void update(Player player, House house);
    //pay when intruding other's house
    public void pay(Player player, House house);
    /*
    the same function for GUI
     */
    public void buy_GUI(JPanel panel, Player player, House house);
    public void update_GUI(JPanel panel, Player player, House house);
    public void pay_GUI(JPanel panel, Player player, House house);
}
