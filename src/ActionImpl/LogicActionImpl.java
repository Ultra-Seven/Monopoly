package ActionImpl;

import Action.ILogicAction;
import Entity.Player;
import Util.Logic;
import Util.TimeCount;
import Util.Utility;

import javax.swing.*;
import java.util.ArrayList;


/**
 * Created by lenovo on 2016/4/3.
 */
public class LogicActionImpl implements ILogicAction {
    private InitActionImpl initAction = new InitActionImpl();
    private ProcessActionImpl processAction = new ProcessActionImpl();
    private Player player;
    @Override
    public void init() {
        initAction.welcome();
        initAction.setPlayer();
        initAction.inputName();
        initAction.setTime();
        initAction.setPlayerAssert();
    }

    @Override
    public void process() {
        System.out.println("Today is " + TimeCount.getInstance().getYear() + "/" + TimeCount.getInstance().getMonth() + "/" + TimeCount.getInstance().getDay());
        while (true) {
            processAction.step(player);
            System.out.print("Your choice:");
            String choice = Utility.input();
            if (Utility.validate(choice, 8, 0)) {
                int choice_int = Integer.parseInt(choice);
                if(choice_int == 6 || choice_int == 7) {
                    processAction.react(player, choice_int);
                    processAction.display();
                    break;
                }
                else processAction.react(player, choice_int);

            }
            else {
                System.out.println("Please input a number ranging from 0 to 8!");
            }
        }
        if (processAction.isEnd()) {
            end();
        }
     }

    @Override
    public void end() {
        ArrayList<Player> winner = getWinner();
        String win = "Congratulations! The winner is ";
        for (Player aWinner : winner) {
            win = win + aWinner.getName() + " ";
        }
        win = win + " !";
        System.out.println(win);
        JOptionPane.showMessageDialog(null, win);
        System.exit(0);
    }
    public void start() {
        processAction.start();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private ArrayList<Player> getWinner() {
        ArrayList<Player> winner = new ArrayList<>();
        int size = Logic.getUniqueInstance().getPlayers().size();
        int[] assets = new int[size];
        int max = 0;
        for (int i = 0; i < size; i++){
            Player player = Logic.getUniqueInstance().getPlayers().get(i);
            int asset = player.getCash() + player.getDeposit();
            for (int j =0; j < player.getHouses().size(); j++) {
                asset += player.getHouses().get(j).getCost();
            }
            assets[i] = asset;
            if (asset >= max) {
                max = asset;
            }
        }
        for (int i = 0; i < size; i++){
            if (assets[i] == max) {
                winner.add(Logic.getUniqueInstance().getPlayers().get(i));
            }
        }
        return winner;
    }
}
