package BizImpl;

import Biz.IStockBiz;
import Entity.Player;
import Entity.Stock;
import Util.StockMarket;
import java.io.*;
/**
 * Created by lenovo on 2016/4/23.
 */
public class StockBizImpl implements IStockBiz {
    @Override
    public void initStock() {
        try {
            readStocks("data/stocks");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buyStock(Player player, Stock stock, int number) {
        int deposit = player.getDeposit();
        int cash = player.getCash();
        int totalPrice = number * stock.getPrice();
        if (deposit > totalPrice) {
            player.setDeposit(deposit - totalPrice);
        }
        else {
            player.setDeposit(0);
            player.setCash(cash + deposit - totalPrice);
        }
        if (player.getStockIntegerHashMap().keySet().stream().filter(e->e==stock).findFirst().orElse(null) == null) {
            player.addMap(stock, number);
        }
        else {
            int origin = player.getStockIntegerHashMap().get(stock);
            player.getStockIntegerHashMap().replace(stock, origin + number);
        }
    }

    @Override
    public void sellStock(Player player, Stock stock, int number) {
        int totalPrice = stock.getPrice() * number;
        player.setDeposit(player.getDeposit() + totalPrice);
        int delta = player.getStockIntegerHashMap().get(stock) - number;
        if (delta == 0)
            player.getStockIntegerHashMap().remove(stock);
        else
            player.getStockIntegerHashMap().replace(stock, delta);
    }

    @Override
    public void updateStock() {
        StockMarket.getUniqueInstance().getStocks().stream().forEach(e -> {
            updateIncrease(e);
            e.addHistory(e.getPrice());
        });
    }
    private void updateIncrease(Stock stock) {
        double rate = Math.random() * 0.2 - 0.1;
        int price = stock.getPrice();
        stock.setRate(rate);
        stock.setPrice((int)(price*(rate+1)));
    }
    private void readStocks(String filePath) throws IOException {
        File file = new File(filePath);
        if(file.isFile() && file.exists()){ //判断文件是否存在
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            int size = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < size; i++) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                int id = Integer.parseInt(strings[0]);
                String name = strings[1];
                int price = Integer.parseInt(strings[2]);
                double rate = Double.parseDouble(strings[3]);
                Stock stock = new Stock(id, name, price, rate);
                stock.addHistory(price);
                StockMarket.getUniqueInstance().getStocks().add(stock);
            }
            read.close();
        }else{
            System.out.println("No such a file");
        }
    }
}
