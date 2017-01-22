package Entity;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/3.
 */
public class Stock {
    //id of stock
    private int id;
    //name of stock
    private String name;
    //the value of the stock
    private int price;
    //increase or decrease rate of the stock
    private double rate;
    //the record of the price of stock within recent 10 days
    private ArrayList<Integer> history = new ArrayList<>();
    public Stock() {
        for (int i = 0; i < 10; i++) {
            history.add(i);
        }
    }
    public Stock(int id, String name, int price, double rate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rate = rate;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }
    public String getIncrease() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(rate);
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ArrayList<Integer> getHistory() {
        return history;
    }
    //update the record history
    public void addHistory(int price) {
        if (history.size() < 10)
            history.add(price);
        else {
            history.remove(0);
            history.add(price);
        }
    }
}
