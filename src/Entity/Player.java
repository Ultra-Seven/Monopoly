package Entity;

import Entity.Items.*;
import Entity.Service.House;
import SuperEntity.Item;
import SuperEntity.Stuff;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/4/3.
 */
public class Player extends Stuff{
    private int cash;
    private int deposit;
    private int ticket = 100;
    private int asset;
    private int stock;
    private String houseSymbol;
    private ImageIcon[] houseImage;
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<House> houses = new ArrayList<>();

    public HashMap<Stock, Integer> getStockIntegerHashMap() {
        return stockIntegerHashMap;
    }

    private HashMap<Stock, Integer> stockIntegerHashMap = new HashMap<>();
    //true is clockwise
    private boolean direction = true;
    public Player() {
        for (int i = 0; i < 10; i++)
            items.add(new AverageCard(this));
        for (int i = 0; i < 10; i++)
            items.add(new Barrier(this));
        for (int i = 0; i < 10; i++)
            items.add(new RobCard(this));
        for (int i = 0; i < 10; i++)
            items.add(new SteeringCard(this));
        for (int i = 0; i < 10; i++)
            items.add(new ControlDice(this));
        for (int i = 0; i < 10; i++)
            items.add(new StopCard(this));
        for (int i = 0; i < 10; i++)
            items.add(new TaxCard(this));
    }
    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public boolean getDirection() {
        return this.direction;
    }

    public void addItem(Item item) {
        item.setPlayer(this);
        this.items.add(item);
    }

    public ArrayList<House> getHouses() {
        return houses;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getHouseSymbol() {
        return houseSymbol;
    }

    public void setHouseSymbol(String houseSymbol) {
        this.houseSymbol = houseSymbol;
    }

    public void addMap(Stock stock, int delta) {
        stockIntegerHashMap.put(stock, delta);
    }
    public int getStock(Stock stock) {
        return stockIntegerHashMap.get(stock) == null ? 0 : stockIntegerHashMap.get(stock);
    }

    public ImageIcon[] getHouseImage() {
        return houseImage;
    }

    public void setHouseImage(ImageIcon[] houseImage) {
        this.houseImage = houseImage;
    }

    public int getAsset() {
        setAsset(cash + deposit);
        return asset;
    }

    public void setAsset(int asset) {
        this.asset = asset;
    }

    public int getStock() {
        int stock_temp = 0;
        for (Object o : stockIntegerHashMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Stock key = (Stock) entry.getKey();
            Integer val = (Integer) entry.getValue();
            stock_temp += val * key.getPrice();
        }
        stock = stock_temp;
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public void service(Player player) {

    }

    @Override
    public void service(JPanel panel, Player player) {

    }

    @Override
    public void printInformation() {
        String name = getName();
        int cash = getCash();
        int deposit = getDeposit();
        int ticket = getTicket();
        int houseCost = 0;
        for (House house : houses) houseCost += house.getCost();
        System.out.println(name + "\t\t" + cash + "\t\t" + deposit + "\t\t" + ticket + "\t" + houseCost + "\t" + (deposit+cash+houseCost));
    }

    @Override
    public String getInformation() {
        String name = getName();
        int cash = getCash();
        int deposit = getDeposit();
        int ticket = getTicket();
        int houseCost = 0;
        for (House house : houses) houseCost += house.getCost();
        return "name:" + name + "\ncash:" + cash + "\ndeposit:" + deposit + "\nticket" + ticket + "\nhouse:" + houseCost + "\nasset:" + (deposit + cash + houseCost) + "\n\n";
    }
}
