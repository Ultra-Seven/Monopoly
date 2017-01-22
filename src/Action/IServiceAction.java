package Action;

import Entity.Player;
import Entity.Service.Bank;
import Entity.Service.House;
import Entity.Service.Lottery;

/**
 * Created by lenovo on 2016/4/27.
 */
public interface IServiceAction {
    public boolean bankService(Player player, Bank bank);
    public boolean itemShopService(Player player);
    public void itemSpotService(Player player);
    public boolean lotteryService(Player player, Lottery lottery);
    public void newsService(Player player);
    public void ticketSpotService(Player player);
    public void vacancyService(Player player);
    public void interestMonthly();
}
