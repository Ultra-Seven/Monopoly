package ActionImpl;

import Action.IStepAction;
import BizImpl.HouseBizImpl;
import BizImpl.StepBizImpl;
import Entity.Player;
import Entity.Point;
import SuperEntity.Stuff;
import Util.*;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/29.
 */
public class StepActionImpl implements IStepAction {
    private InformationActionImpl informationAction = new InformationActionImpl();
    private HouseBizImpl houseBiz = new HouseBizImpl();
    private StepBizImpl stepBiz = new StepBizImpl();
    private ItemActionImpl itemAction = new ItemActionImpl();
    @Override
    public void printCurrentMap() {
        printMap(true);
    }

    @Override
    public void printOriginalMap() {
        printMap(false);
    }

    @Override
    public void go(Player player) {
        ArrayList<Point> points = Map.getUniqueInstance().getPoints();
        int dice = Dice.getInstance().getDice();
        int mask = player.isDirection() ? 1 : (-1);
        int size = points.size();
        int position_start = player.getPosition();
        int position_end = (position_start + dice * mask + size) % size;
        System.out.println("The number on the dice is " + dice + " !");

        ArrayList<Point>  reactPoint = stepBiz.getReactPoint(player, position_start, position_end);
        for (int i = 0; i < reactPoint.size(); i++) {
            Point point = reactPoint.get(i);
            Stuff stuff = point.getStuff().get(0);
            if (stepBiz.isStop(point)) {
                System.out.println("You stay at the " + stuff.getName() + " because of effect of items");
                stepBiz.cleanBarrier(point);
            }
            stuff.service(player);
        }
    }

    @Override
    public void useItem(Player player) {
        if (player.getItems().size() > 0) {
            itemAction.displayItems(player);
        }
        else {
            System.out.println("Sorry, you have no items!");
        }
    }

    @Override
    public void alertDangerous(Player player) {
        informationAction.alarm(player);
    }

    @Override
    public void specificInformation(Player player) {
        System.out.print("Please input the number of steps back or forth(-:back, +:forth):  ");
        String input = Utility.input();
        int size = Map.getUniqueInstance().getPoints().size();
        int low = size * (-1) + 1;
        int high =  size - 1;
        int mask = player.getDirection() ? 1 : -1;
        if (Utility.validate(input, high, low)) {
            int position = (player.getPosition() + mask * Integer.parseInt(input) + size) % size;
            System.out.println("Information:");
            informationAction.specificInformation(player, position);
        }
        else {
            System.out.println("Wrong input!");
        }
    }

    @Override
    public void assertInformation() {
        informationAction.printAsset();
    }

    @Override
    public void giveUp(Player player) {
        System.out.println(player.getName() + " chooses to give up!");
        stepBiz.removePlayer(player);
    }

    @Override
    public void stockMarket(Player player) {
        int round = TimeCount.getInstance().getRound();
        int re = round % 7;
        if (re != 3 && re != 4) {
            StockMarket.getUniqueInstance().process(player);
        }
        else {
            System.out.println("The Stock Market is closed on weekend");
        }
    }

    private void printMap(boolean current) {
        int width = Map.getUniqueInstance().getWidth();
        int height = Map.getUniqueInstance().getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Point point = Map.getUniqueInstance().getPoint(i, j);
                String string = " \t";
                if(point != null) {
                    ArrayList<Stuff> stuffs = point.getStuff();
                    int index = current?(stuffs.size()-1):0;
                    Stuff stuff = stuffs.get(index);
                    string = stuff.getSymbol() + "\t";
                }

                System.out.print(string);
            }
            System.out.println("");
            System.out.println("");
        }
    }
}
