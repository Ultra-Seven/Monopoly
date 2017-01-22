package BizImpl;

import Biz.IBankBiz;
import Entity.Player;
import Util.Logic;

/**
 * Created by lenovo on 2016/4/27.
 */
public class BankBizImpl implements IBankBiz {
    @Override
    public void saveMoney(Player player, int cash) {
        int deposit_end = player.getDeposit() + cash;
        int cash_end = player.getCash() - cash;
        player.setCash(cash_end);
        player.setDeposit(deposit_end);
    }

    @Override
    public void drawMoney(Player player, int deposit) {
        int deposit_end = player.getDeposit() - deposit;
        int cash_end = player.getCash() + deposit;
        player.setCash(cash_end);
        player.setDeposit(deposit_end);
    }

    @Override
    public void sendInterest() {
        Logic.getUniqueInstance().getPlayers().stream().forEach(player -> player.setDeposit((int) (player.getDeposit()*1.1)));
    }
}
