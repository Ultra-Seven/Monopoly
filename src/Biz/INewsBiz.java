package Biz;

import Entity.Player;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/9.
 */
public interface INewsBiz {
    //give money to a player who has the most houses
    public ArrayList<Player> appraisal();
    //help the poor
    public ArrayList<Player> assistance();
    //everyone has bonus
    public void bonus();
    //pay tax
    public void tax();
    //get item
    public void getItem();
    //go to hospital
    public void addToHospital(Player player);
}
