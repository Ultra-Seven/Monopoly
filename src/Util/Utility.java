package Util;

import Entity.Player;
import Entity.Service.House;
import View.PlayerStep;
import javafx.print.PageLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2016/4/3.
 * The utility class used to a tool class to handle some strings or refresh the GUI
 */
public class Utility {
    private static Utility uniqueInstance;
    private Utility() {

    }
    public static Utility getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Utility();
        }
        return uniqueInstance;
    }
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    public static boolean validate(String input, int up, int down) {
        boolean re = true;
        if (!isNumber(input)) {
            re = false;
        }
        else {
            int in = Integer.parseInt(input);
            if (in > up || in < down) {
                re = false;
            }
        }
        return re;
    }
    public static boolean canAfford(Player player, int need) {
        return player.getCash() >= need ? true : false;
    }
    public static void updateHouse(House house, int cost, Player owner, int level, int profit, String symbol) {
        house.setCost(cost);
        house.setOwner(owner);
        house.setLevel(level);
        house.setProfit(profit);
        house.setSymbol(symbol);
    }
    public static String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    public static String inputLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void trash() {
        ArrayList<Player> players = Logic.getUniqueInstance().getNullPlayers();
        players.removeAll(players.stream().filter(player1 -> player1 == null).collect(Collectors.toList()));
    }
    //add a component into the parent component
    public static void initComponent(JComponent parent, JComponent component, int width, int height, int x, int y,  Color color) {
        parent.add(component);
        component.setSize(width, height);
        component.setBounds(x, y, component.getWidth(), component.getHeight());
        component.setOpaque(false);
        component.setForeground(color);
        component.setFont(new Font("Times New Roman", Font.BOLD, 20));
    }
    //modify the picture to fit the fixed size
    public static ImageIcon getImageIcon(ImageIcon imageIcon, int width, int height) {
        ImageIcon imageIcon1 = new ImageIcon(imageIcon.getDescription());
        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT));
        return imageIcon1;
    }
    public static void wakeUpStep() {
        synchronized(PlayerStep.getInstance()) {
            PlayerStep.getInstance().setCondition(true);
            PlayerStep.getInstance().notify();
        }
    }
}
