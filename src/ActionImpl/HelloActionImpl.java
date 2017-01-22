package ActionImpl;

import Action.IHelloAction;
import Entity.Service.*;

/**
 * Created by lenovo on 2016/4/27.
 */
public class HelloActionImpl implements IHelloAction {
    @Override
    public void bankHello(Bank bank) {
        System.out.println("Welcome to " + bank.getName() + "!");
    }

    @Override
    public void houseHelloNoOwner(House house) {
        System.out.println("Welcome to the " + house.getName() + " with level-" + house.getLevel() + " !");
    }

    @Override
    public void houseHelloNoMansion(House house) {
        System.out.println("Welcome to your house " + house.getName() + " with level-" + house.getLevel() + " !");
    }

    @Override
    public void houseHelloOthers(House house) {
        System.out.println("It is illegal to intrude other's house.");
    }


    @Override
    public void itemShopHello(ItemShop itemShop) {
        System.out.println("Welcome to " + itemShop.getName() + "!");
    }

    @Override
    public void itemSpotHello(ItemSpot itemSpot) {
        System.out.println("Welcome to " + itemSpot.getName() + "!");
    }

    @Override
    public void lotteryHello(Lottery lottery) {
        System.out.println("Welcome to Lottery Point-" + lottery.getName() + " !");
    }

    @Override
    public void newsHello(News news) {
        System.out.println("Welcome to " + news.getName() + "! Now let's watch the news!");
    }

    @Override
    public void ticketSpotHello(TicketSpot ticketSpot) {
        System.out.println("Welcome to " + ticketSpot.getName() + "!");
    }

    @Override
    public void vacancyHello() {
        System.out.println("You are in a vacancy, so nothing is happened!");
    }
}
