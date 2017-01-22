package Biz;

import Entity.Player;

/**
 * Created by lenovo on 2016/4/27.
 * bank biz interface
 */
public interface IBankBiz {
    //save money
    public void saveMoney(Player player, int cash);
    //draw money
    public void drawMoney(Player player, int deposit);
    //send interest every month
    public void sendInterest();
}
