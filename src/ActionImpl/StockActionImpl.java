package ActionImpl;

import Action.IStockAction;
import BizImpl.StockBizImpl;
import Entity.Player;
import Entity.Stock;
import Util.StockMarket;
import Util.Utility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/30.
 */
public class StockActionImpl implements IStockAction {
    StockBizImpl stockBiz = new StockBizImpl();
    @Override
    public void initStock() {
        stockBiz.initStock();
    }

    @Override
    public void processStock(Player player) {
        System.out.println("Welcome to our stock market!");
        while (true) {
            printStock();
            System.out.println("You can choose: 1.buy\t2.sell\t3.quit");
            System.out.print("Your choice: ");
            String choice = Utility.inputLine();
            if(Utility.validate(choice, 3, 1)) {
                if(choice.equals("1")) {
                    System.out.print("Buy(x/n): ");
                    String buy = Utility.input();
                    if (isValid(buy)) {
                        ArrayList<Stock> stocks = StockMarket.getUniqueInstance().getStocks();
                        String[] strings = buy.split("/");
                        int value = Integer.parseInt(strings[1]);
                        int index = Integer.parseInt(strings[0]);
                        if (Utility.validate(strings[0], stocks.size()-1, 0) && value > 0) {
                            buyStock(player, stocks.get(index), value);
                        }
                        else {
                            System.out.println("Out of range!");
                        }
                        continue;
                    }
                    else {
                        System.out.println("Wrong input!");
                        continue;
                    }
                }
                if(choice.equals("2")) {
                    if(!player.getStockIntegerHashMap().isEmpty()) {
                        Iterator iterator = player.getStockIntegerHashMap().entrySet().iterator();
                        ArrayList<Stock> stocks = new ArrayList<Stock>();
                        int i = 0;
                        while (iterator.hasNext()) {
                            Map.Entry entry = (Map.Entry) iterator.next();
                            Stock key = (Stock) entry.getKey();
                            Integer val = (Integer) entry.getValue();
                            System.out.println(i + "\t" + key.getName() + "\t" + val);
                            i++;
                            stocks.add(key);
                        }
                        System.out.print("Sell(x/n): ");
                        String sell = Utility.inputLine();
                        if (isValid(sell)) {
                            String[] strings = sell.split("/");
                            int value = Integer.parseInt(strings[1]);
                            int index = Integer.parseInt(strings[0]);
                            if (index >= 0 && index <= i) {
                                sellStock(player, stocks.get(index), value);
                            } else {
                                System.out.println("Out of range!");
                            }
                            continue;
                        } else {
                            System.out.println("Wrong input");
                            continue;
                        }
                    }
                    else {
                        System.out.println("You have no stocks!");
                    }
                }
                if (choice.equals("3")) {
                    break;
                }
            }
            else {
                System.out.println("Wrong input!");
            }
        }
    }

    @Override
    public void endStock() {
        stockBiz.updateStock();
    }

    private boolean isValid(String input) {
        String[] str = input.split("/");
        if (str.length != 2) {
            return false;
        }
        else {
            if (!(Utility.isNumber(str[0])&&Utility.isNumber(str[1]))) {
                return false;
            }
        }
        return true;
    }

    private void buyStock(Player player, Stock stock, int value) {
        int deposit = player.getDeposit();
        int cash = player.getCash();
        int totalPrice = stock.getPrice() * value;
        if (deposit + cash > totalPrice) {
            stockBiz.buyStock(player, stock, value);
            System.out.println("You have bought " + value + " " + stock.getName() + " successfully!");
        }
        else {
            System.out.println("More money will be required!");
        }
    }
    private void sellStock(Player player, Stock stock, int value) {
        int total = player.getStockIntegerHashMap().get(stock);
        if (value <= total && value > 0) {
            stockBiz.sellStock(player, stock, value);
            System.out.println("You have sold " + value + " " + stock.getName() + " successfully!");
        }
        else if (value <= 0) {
            System.out.println("Wrong input!");
        }
        else {
            System.out.println("You have inadequate stocks");
        }
    }
    private void printStock() {
        ArrayList<Stock> stocks = StockMarket.getUniqueInstance().getStocks();
        System.out.println("id\tname\tprice\tincrease\t");
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        for (int i = 0; i < stocks.size(); i++) {
            Stock stock = stocks.get(i);
            System.out.println(stock.getId() + "\t" + stock.getName() + "\t" + stock.getPrice() + "\t\t" + decimalFormat.format(stock.getRate()));
        }
    }
}
