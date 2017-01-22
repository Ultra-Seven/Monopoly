package Controller;

import Entity.Player;
import Entity.Service.*;

import javax.swing.*;

/**
 * Created by lenovo on 2016/6/9.
 * The controller interface in MVC
 * implemented by ServiceControl handling some business about service
 */
public interface IController {
    //bank service
    public void bankService(JPanel panel, Bank bank, Player player);
    //item spot service
    public void itemSpotService(JPanel panel, ItemSpot itemSpot, Player player);
    //lottery service
    public void lotteryService(JPanel panel, Lottery lottery, Player player);
    //news service
    public void newsService(JPanel panel, News news, Player player);
    //ticket spot service
    public void ticketService(JPanel panel, TicketSpot ticketSpot, Player player);
    //vacancy service
    public void vacancyService(JPanel panel, Vacancy vacancy, Player player);
    //item shop service
    public void itemShopService(JPanel panel, ItemShop itemShop, Player player);
    //send interest every month
    public void interestMonthly();
    //run the lottery
    public void runLottery();
}
