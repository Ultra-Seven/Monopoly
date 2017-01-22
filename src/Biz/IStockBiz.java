package Biz;

import Entity.Player;
import Entity.Stock;

/**
 * Created by lenovo on 2016/4/23.
 */
public interface IStockBiz {
    //init stock
    public void initStock();
    //buy stock
    public void buyStock(Player player, Stock stock, int number);
    //sell stock
    public void sellStock(Player player, Stock stock, int number);
    //update stock
    public void updateStock();
}
