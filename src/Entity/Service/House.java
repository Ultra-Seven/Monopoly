package Entity.Service;

import Entity.IState.State;
import Entity.Player;

import Entity.State.NoOwnerState;
import Entity.State.UpdateState;
import SuperEntity.Stuff;
import Util.PointType;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/3.
 * House entity
 */
public class House extends Stuff{
    private int originalCost;
    private int cost;
    private Player owner;
    private int level = 1;
    private int profit;
    private State noOwner = new NoOwnerState();
    private State update = new UpdateState();
    private State state = noOwner;
    public House () {
        setType(PointType.House);
    }

    public int getOriginalCost() {
        return originalCost;
    }

    public void setOriginalCost(int originalCost) {
        this.originalCost = originalCost;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getNoOwner() {
        return noOwner;
    }

    public State getUpdate() {
        return update;
    }

    @Override
    public void service(Player player) {
        if(getState() == getNoOwner()) {
            state.buy(player, this);
        }
        else {
            if (getOwner() == player) {
                state.update(player, this);
            }
            else {
                state.pay(player, this);
            }
        }
    }
    public void service(JPanel panel, Player player) {
        if(getState() == getNoOwner()) {
            state.buy_GUI(panel, player, this);
        }
        else {
            if (getOwner() == player) {
                state.update_GUI(panel, player, this);
            }
            else {
                state.pay_GUI(panel, player, this);
            }
        }
    }
    @Override
    public void printInformation() {
        String owner = getOwner() == null ? "No" : getOwner().getName();
        System.out.println("Type: " + getType());
        System.out.println("Name: " + getName());
        System.out.println("Owner: " + owner);
        System.out.println("Level:" + getLevel());
        System.out.println("Cost: " + getCost());
        System.out.println("Profit: " + getProfit());
    }

    @Override
    public String getInformation() {
        String owner = getOwner() == null ? "No" : getOwner().getName();
        return "Type: " + getType() + "\nName: " + getName() + "\nOwner: " + owner + "\nLevel:" + getLevel() + "\nCost:" + getCost() + "\nProfit: " + getProfit();
    }
}
