package ActionImpl;

import Action.IProcessAction;
import Entity.Player;
import Util.Logic;
import Util.TimeCount;
import Util.Utility;

import java.util.stream.Collectors;

/**
 * Created by lenovo on 2016/4/29.
 */
public class ProcessActionImpl implements IProcessAction{
    StepActionImpl stepAction = new StepActionImpl();
    @Override
    public void start() {
        System.out.println("========================== Game Starting ==========================");
    }

    @Override
    public void step(Player player) {
        String clockwise = player.isDirection()?"clockwise":"anticlockwise";
        System.out.println("Now, it's the turn of player " + player.getName() + ", and the direction is " + clockwise);
        System.out.println("Then you can choose to:");
        System.out.println("0   -   look up the current map");
        System.out.println("1   -   look up the original map");
        System.out.println("2   -   use the items");
        System.out.println("3   -   alert the dangers within 10 steps");
        System.out.println("4   -   look up the specific information");
        System.out.println("5   -   look up asset information");
        System.out.println("6   -   dice");
        System.out.println("7   -   give up");
        System.out.println("8   -   go into stock market");
    }

    @Override
    public void react(Player player, int choice) {
        switch (choice) {
            case 0: {
                stepAction.printCurrentMap();
                break;
            }
            case 1: {
                stepAction.printOriginalMap();
                break;
            }
            case 2: {
                stepAction.useItem(player);
                break;
            }
            case 3: {
                stepAction.alertDangerous(player);
                break;
            }
            case 4: {
                stepAction.specificInformation(player);
                break;
            }
            case 5: {
                stepAction.assertInformation();
                break;
            }
            case 6: {
                stepAction.go(player);
                break;
            }
            case 7: {
                stepAction.giveUp(player);
                break;
            }
            case 8: {
                stepAction.stockMarket(player);
                break;
            }
        }
    }

    @Override
    public void display() {
        stepAction.printCurrentMap();
    }

    @Override
    public boolean isEnd() {
        return Logic.getUniqueInstance().getPlayers().stream().filter(e->(e!=null)).collect(Collectors.toList()).size() == 1;
    }
}
