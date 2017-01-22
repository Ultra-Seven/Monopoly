package ActionImpl;

import Action.IHouseAction;
import BizImpl.HouseBizImpl;
import BizImpl.StepBizImpl;
import Entity.Player;
import Entity.Service.House;
import Util.Map;
import Util.Utility;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2016/4/27.
 */
public class HouseActionImpl implements IHouseAction {
    HouseBizImpl houseBiz = new HouseBizImpl();
    @Override
    public boolean houseBuyService(Player player, House house) {
        int cost = house.getCost();
        System.out.print("Now do you want to buy it paying $" + cost + " ?(y-yes/n-no)");
        String choice = Utility.input();
        if (choice.equals("y")) {
            if (Utility.canAfford(player, cost)) {
                houseBiz.buyHouse(player, house);
                System.out.println("Congratulations! You become the owner of " + house.getName());
            }
            else {
                System.out.println("Sorry, your cash is not enough... Failed");
            }
            return false;
        }
        else if (choice.equals("n")) {
            return false;
        }
        else {
            System.out.println("Please input y or n!");
            return true;
        }
    }

    @Override
    public boolean houseUpdateService(Player player, House house) {
        if (house.getLevel() < 6) {
            int cost = house.getOriginalCost() >> 1;
            System.out.print("Now do you want to expand your house paying $" + cost + " ?(y-yes/n-no)");
            String choice = Utility.input();
            if (choice.equals("y")) {
                if (Utility.canAfford(player, cost)) {
                    houseBiz.updateHouse(player, house);
                    System.out.println("Congratulations! You You have expand your house to level-" + house.getLevel() + "!");
                } else {
                    System.out.println("Sorry, your cash is not enough... Failed");
                }
                return false;
            } else if (choice.equals("n")) {
                return false;
            } else {
                System.out.println("Please input y or n!");
                return true;
            }
        }
        else {
            System.out.println("Your house has reach the final level!");
            return false;
        }
    }

    @Override
    public boolean houseIntrudeService(Player player, House house) {
        int street = streetAdding(house);
        String streetName = Map.getUniqueInstance().getStreetName();
        int profit = house.getProfit() * street;
        Player owner = house.getOwner();
        if (street == 1) {
            System.out.println("So you should pay $" + profit + " to " + owner.getName());
        }
        else {
            System.out.println("Because of entering " + streetName +" you must pay $" + profit + " to " + owner.getName());
        }
        if (Utility.canAfford(player, profit)) {
            houseBiz.payHouse(player, house);
        }
        else {
            if (Utility.canAfford(player, profit-player.getDeposit())) {
                System.out.println("You don't have enough cash. So you must fetch $" + (profit-player.getCash()) + " from the bank!");
                houseBiz.payHouse(player, house);
            }
            else {
                while (true) {
                    if(player.getHouses().size() > 0) {
                        House house_sell = houseBiz.sellHouse(player);
                        System.out.println("Sorry, because of debts you have to sell " + house_sell.getName());
                    }
                    else {
                        System.out.println("Er...You have gone bankruptcy!");
                        houseBiz.payHouse(player, house);
                        StepBizImpl stepBiz = new StepBizImpl();
                        stepBiz.removePlayer(player);
                        break;
                    }
                    if (Utility.canAfford(player, profit-player.getDeposit())) {
                        houseBiz.payHouse(player, house);
                        break;
                    }
                }
            }
        }
        return false;
    }
    public int streetAdding(House house) {
        ArrayList<ArrayList<House>> streets = Map.getUniqueInstance().getStreets();
        ArrayList<House> houses = streets.stream().filter(e->(e.get(0).getPosition() <= house.getPosition() && e.get(e.size()-1).getPosition() >= house.getPosition()))
                .findFirst().orElse(null);
        if (houses == null) {
            return 1;
        }
        else
            return houses.stream().filter(e->(e.getOwner()==house.getOwner())).collect(Collectors.toList()).size();
    }
}
