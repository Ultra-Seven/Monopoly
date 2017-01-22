package Util;

import ActionImpl.LogicActionImpl;
import ActionImpl.ServiceActionImpl;
import Controller.ServiceController;
import Entity.Player;
import Entity.Service.Hospital;
import Entity.Service.Lottery;
import View.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by lenovo on 2016/4/3.
 * The logic of the game,the life cycle is init,process and end
 */
public class Logic {
    private static Logic uniqueInstance;
    //the player in the game
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> nullPlayers = new ArrayList<>();
    private LogicActionImpl logic = new LogicActionImpl();
    //the player who is playing
    private Player currentPlayer;
    //the next player
    private Player nextPlayer;
    private Logic() {
    }
    public static Logic getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Logic();
        }
        return uniqueInstance;
    }
    //the init of the game
    public void init() {
        logic.init();
    }
    //the process of the game logic
    public void process() {
        int index = (players.indexOf(nextPlayer) + 1) % players.size();
        currentPlayer = nextPlayer;
        nextPlayer = players.get(index);
        if(index == 1) {
            TimeCount.getInstance().timeGoing();
            if (TimeCount.getInstance().isOver() || players.size() <= 1)
                logic.end();
            if (TimeCount.getInstance().isMonth()) {
                new ServiceController().interestMonthly();
                new ServiceController().runLottery();
            }
        }
        else {
            if (players.size() <= 1)
                logic.end();
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        currentPlayer = players.get(0);
        nextPlayer = players.get(1);
    }

    public ArrayList<Player> getNullPlayers() {
        return nullPlayers;
    }
    private void initNullPlayer() {
        for (int i = 0; i < players.size(); i++) {
            nullPlayers.add(players.get(i));
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}
