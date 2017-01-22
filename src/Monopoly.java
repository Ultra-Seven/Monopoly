import Util.*;
import View.Preface;

/**
 * Created by lenovo on 2016/4/3.
 */
public class Monopoly {
    public static void main(String[] args) {
        start();
    }
    public static void start() {
        Dice.getInstance();
        StockMarket.getUniqueInstance().init();
        TimeCount.getInstance();
        Map.getUniqueInstance();
        new Preface().setVisible(true);
    }
}
