package BizImpl;

import Biz.IHouseBiz;
import Entity.Player;
import Entity.Service.House;
import Util.HouseImage;
import Util.Utility;
import View.Stage;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/27.
 */
public class HouseBizImpl implements IHouseBiz {
    @Override
    public void buyHouse(Player player, House house) {
        int cost = house.getCost();
        int cash = player.getCash() - cost;
        player.getHouses().add(house);
        player.setCash(cash);
        house.setOwner(player);
        house.setSymbol(player.getHouseSymbol());
        house.setImage(Utility.getImageIcon(player.getHouseImage()[house.getLevel() - 1], 40, 40));
        if (house.getLevel() == 6)
            house.setState(house.getUpdate());
        else
            house.setState(house.getUpdate());
    }

    @Override
    public void updateHouse(Player player, House house) {
        int cost = house.getOriginalCost() >> 1;
        int level = house.getLevel() + 1;
        int costs = house.getCost() + cost;
        int profit = house.getProfit() << 1;

        int cash = player.getCash() - cost;
        player.setCash(cash);
        house.setCost(costs);
        house.setLevel(level);
        house.setProfit(profit);
        house.setImage(Utility.getImageIcon(player.getHouseImage()[house.getLevel() - 1], 40, 40));
        if (house.getLevel() == 6)
            house.setState(house.getUpdate());
    }

    @Override
    public void payHouse(Player player, House house) {
        int cost = house.getCost();
        int cash = player.getCash();
        int deposit = player.getDeposit();
        if (cash + deposit >= cost) {
            int cash_end = (cash >= cost) ? cash-cost : 0;
            int deposit_end = cash + deposit - cash_end - cost;
            player.setCash(cash_end);
            player.setDeposit(deposit_end);
            house.getOwner().setCash(house.getOwner().getCash()+cost);
        }
        else {
            house.getOwner().setCash(house.getOwner().getCash()+cash+deposit);
        }
    }

    @Override
    public House sellHouse(Player player) {
        ArrayList<House> houses = player.getHouses();
        int random = (int)(Math.random()*houses.size());
        int asset = houses.get(random).getCost();
        House house = player.getHouses().remove(random);
        player.setCash(player.getCash()+asset);
        house.setOwner(null);
        house.setSymbol("◎");
        house.setState(house.getNoOwner());
        house.setImage(Utility.getImageIcon(new ImageIcon("pics/noowner0.png"), 40, 40));
        return house;
    }

    @Override
    public void refresh(Player player) {
        player.getHouses().stream().forEach(e ->{
            e.setOwner(null);
            e.setSymbol("◎");
            e.setState(e.getNoOwner());
            e.setImage(Utility.getImageIcon(new ImageIcon("pics/noowner0.png"), 40, 40));
            Stage.getUniqueInstance().refreshPoint(e.getPosition());
        });
    }
}
