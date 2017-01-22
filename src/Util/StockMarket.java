package Util;

import ActionImpl.StockActionImpl;
import Entity.Player;
import Entity.Stock;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/3.
 */
public class StockMarket {
    private static StockMarket uniqueInstance;
    private StockActionImpl stockAction = new StockActionImpl();
    public ArrayList<Stock> getStocks() {
        return stocks;
    }
    //all stocks in the market
    private ArrayList<Stock> stocks;
    private StockMarket() {
        stocks = new ArrayList<>();
    }
    public static StockMarket getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new StockMarket();
        }
        return uniqueInstance;
    }
    /*
     *The life cycle of stock market
     */
    public void init() {
        stockAction.initStock();
    }
    public void process(Player player) {
        stockAction.processStock(player);
    }
    public void end() {
        stockAction.endStock();
    }
}
