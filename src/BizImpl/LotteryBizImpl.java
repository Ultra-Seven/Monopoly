package BizImpl;

import Biz.ILotteryBiz;
import Entity.Player;
import Entity.Service.Lottery;

import java.util.HashMap;

/**
 * Created by lenovo on 2016/4/28.
 */
public class LotteryBizImpl implements ILotteryBiz {
    private static int luckyNumber = 0;
    @Override
    public void buyLottery(Player player, int number) {
        Lottery.getHashMap().put(number, player);
        player.setCash(player.getCash() - Lottery.getPrice());
    }

    @Override
    public boolean isSame(int number) {
        return Lottery.getHashMap().get(number) == null ? false : true;
    }

    public static int getLuckyNumber() {
        return (int) (Math.random() * 200);
    }

    public static Player winPrice() {
        luckyNumber = (int)(Math.random() * Lottery.getRange() + 1);
        Player player = Lottery.getHashMap().get(luckyNumber);
        if (player != null) {
            int cash = player.getCash() + luckyNumber * Lottery.getPrice();
            player.setCash(cash);
        }
        Lottery.setHashMap(new HashMap<>());
        return player;
    }
}
