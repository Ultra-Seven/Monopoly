package ActionImpl;

import Action.IServiceAction;
import BizImpl.*;
import Entity.Player;
import Entity.Service.Bank;
import Entity.Service.Lottery;
import Entity.Service.TicketSpot;
import SuperEntity.Item;
import Util.ItemType;
import Util.Logic;
import Util.Utility;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/27.
 */
public class ServiceActionImpl implements IServiceAction {
    BankBizImpl bankBiz = new BankBizImpl();
    ItemShopBizImpl itemShopBiz = new ItemShopBizImpl();
    ItemSpotBizImpl itemSpotBiz = new ItemSpotBizImpl();
    NewsBizImpl newsBiz = new NewsBizImpl();
    TicketSpotBizImpl ticketSpotBiz = new TicketSpotBizImpl();
    LotteryBizImpl lotteryBiz = new LotteryBizImpl();
    @Override
    public boolean bankService(Player player, Bank bank) {
        System.out.println("Now you can choose following services: 1.Save money\t2.Draw money\t3.quit");
        System.out.print("Your choice:");
        String choice = Utility.input();
        if (choice.equals("1")) {
            int upper = player.getCash();
            if (upper >  0) {
                System.out.print("Please input the number of money you save(0-" + upper + "):");
                String save = Utility.input();
                if (Utility.validate(save, upper, 0)) {
                    bankBiz.saveMoney(player, Integer.parseInt(save));
                    System.out.println("Saving successfully! Now your cash is $" + player.getCash() + " and your deposit is $" + player.getDeposit() + " !");
                }
                else {
                    System.out.println("Out of range!");
                }
            }
            else {
                System.out.println("You have no cash!");
            }
            return true;
        }
        else if (choice.equals("2")) {
            int upper = player.getDeposit();
            if (upper >  0) {
                System.out.print("Please input the number of money you draw(0-" + upper + "):");
                String draw = Utility.input();
                if (Utility.validate(draw, upper, 0)) {
                    bankBiz.drawMoney(player, Integer.parseInt(draw));
                    System.out.println("Drawing successfully! Now your cash is $" + player.getCash() + " and your deposit is $" + player.getDeposit() + " !");
                }
                else {
                    System.out.println("Out of range!");
                }
            }
            else {
                System.out.println("You have no cash!");
            }
            return true;
        }
        else if (choice.equals("3")) {
            System.out.println("Bye!");
            return false;
        }
        else {
            System.out.println("Wrong input!");
            return true;
        }

    }

    @Override
    public boolean itemShopService(Player player) {
        System.out.println("The items are presented as follow:");
        for (int i = 0; i < ItemType.values().length; i++) {
            ItemType type = ItemType.values()[i];
            System.out.println((i+1) + ". " + type + "\tticket:" + ItemType.getItem(type).getTicket());
        }
        System.out.print("Your choice(q-quit):");
        String choice = Utility.input();
        if (Utility.validate(choice, ItemType.values().length, 1)) {
            Item item = ItemType.getItem(ItemType.values()[Integer.parseInt(choice)-1]);
            item.setPlayer(player);
            int ticket = item.getTicket();
            if (player.getTicket() >= ticket) {
                itemShopBiz.buyItem(player, item);
                System.out.println("You have bought " + item.getItemType() + " successfully!");
            }
            else {
                System.out.println("You need more tickets!");
            }
        } else if (choice.equals("q")) {
            return false;
        } else {
            System.out.println("Wrong input!");
        }
        return true;
    }

    @Override
    public void itemSpotService(Player player) {
        ItemType type = itemSpotBiz.getItem(player);
        System.out.println("Congratulations! You have got a " + type);
    }

    @Override
    public boolean lotteryService(Player player, Lottery lottery) {
        int range = lottery.getRange();
        int price = lottery.getPrice();
        System.out.println("Do you want to pay $" + price +" to buy a lottery?(if yes input a number(0-" + range + "), otherwise input \"n\"-no)");
        String choice = Utility.input();
        if (Utility.validate(choice, range, 1)) {
            if (player.getCash() >= price) {
                if (lotteryBiz.isSame(Integer.parseInt(choice))) {
                    System.out.println("Someone has bought this number! Please choose another number!");
                    return true;
                }
                else {
                    lotteryBiz.buyLottery(player, Integer.parseInt(choice));
                    System.out.println("Good luck! You've bought lottery-" + choice);
                }
            }
            else {
                System.out.println("You have no more money!");
            }
            return false;
        }
        else if (choice.equals("n")) {
            return false;
        } else {
            System.out.println("Wrong input!");
            return true;
        }
    }

    @Override
    public void newsService(Player player) {
        int num = 5;
        int event = (int)(Math.random() * num);
        switch (event) {
            case 0: {
                System.out.println("Excellent!");
                ArrayList<Player> players = newsBiz.appraisal();
                String name = "";
                for (int i = 0; i < players.size(); i++) {
                    name = name + players.get(i).getName() + ", ";
                }
                System.out.println(" We plan to give " + name + "$" + newsBiz.getPrize() + " for prize!");
                break;
            }
            case 1: {
                System.out.println("How miserable!");
                ArrayList<Player> players = newsBiz.assistance();
                String name = "";
                for (int i = 0; i < players.size(); i++) {
                    name = name + players.get(i).getName() + ", ";
                }
                System.out.println(" We plan to give " + name + "$" + newsBiz.getPrize() + " for prize!");
                break;
            }
            case 2: {
                System.out.println("New bonus: every player should get 10% deposit");
                newsBiz.bonus();
                break;
            }
            case 3: {
                System.out.println("New policy: every player should pay 10% deposit");
                newsBiz.tax();
                break;
            }
            case 4:{
                System.out.println("Good news! Everyone will get an item!");
                newsBiz.getItem();
                ArrayList<Player> players = Logic.getUniqueInstance().getPlayers();
                for (int i = 0; i < players.size(); i++) {
                    Player player1 = players.get(i);
                    System.out.println("Congratulations! " + player1.getName() + " have got a " + player1.getItems().get(player1.getItems().size()-1).getItemType());
                }
                break;
            }
        }
    }

    @Override
    public void ticketSpotService(Player player) {
        ticketSpotBiz.sendTicket(player);
        int ticket = ticketSpotBiz.getTicket();
        System.out.println("Congratulations! You get " + ticket + " tickets from the spot");
    }

    @Override
    public void vacancyService(Player player) {
        System.out.println("This is vacancy! Nothing is happened!");
    }

    @Override
    public void interestMonthly() {
        System.out.println("Everyone will have interests from banks");
        bankBiz.sendInterest();
    }

    public static void runLottery() {
        System.out.println("The lottery is running! The lucky number is " + LotteryBizImpl.getLuckyNumber() + " !");
        Player player = LotteryBizImpl.winPrice();
        if (player == null)
            System.out.println("What a pity! No one wins!");
        else
            System.out.println("Congratulations!" + player.getName() + " wins the lottery");
    }
}
