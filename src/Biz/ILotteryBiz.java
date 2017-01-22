package Biz;

import Entity.Player;

/**
 * Created by lenovo on 2016/4/28.
 */
public interface ILotteryBiz {
    //buy lottery
    public void buyLottery(Player player, int number);
    //whether to have a same number with other player
    public boolean isSame(int number);
}
