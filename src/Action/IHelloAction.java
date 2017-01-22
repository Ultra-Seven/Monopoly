package Action;

import Entity.Service.*;

/**
 * Created by lenovo on 2016/4/27.
 * action used to display some information when first invoking some service
 */
public interface IHelloAction {
    public void bankHello(Bank bank);
    public void houseHelloNoOwner(House house);
    public void houseHelloNoMansion(House house);
    public void houseHelloOthers(House house);
    public void itemShopHello(ItemShop itemShop);
    public void itemSpotHello(ItemSpot itemSpot);
    public void lotteryHello(Lottery lottery);
    public void newsHello(News news);
    public void ticketSpotHello(TicketSpot ticketSpot);
    public void vacancyHello();
}
