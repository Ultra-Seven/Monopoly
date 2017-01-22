package Controller;

import BizImpl.*;
import Entity.Player;
import Entity.Service.*;
import Util.ItemType;
import Util.Utility;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/6.
 */
public class ServiceController implements IController {

    @Override
    public void bankService(JPanel panel, Bank bank, Player player) {
        BankBizImpl bankBiz = new BankBizImpl();
        BankPanel bankPanel = new BankPanel();
        Stage stage = Stage.getUniqueInstance();
        bankPanel.getConfirm().addActionListener(e -> {
            if (bankPanel.getSave().isSelected()) {
                int upper = player.getCash();
                if (Utility.validate(bankPanel.getInput().getText(), upper, 0)) {
                    bankBiz.saveMoney(player, Integer.parseInt(bankPanel.getInput().getText()));
                    stage.refreshPlayer();
                    JOptionPane.showMessageDialog(null, "Save successfully!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong input! Save failed!");
                }
            }
            else {
                int upper = player.getDeposit();
                if (Utility.validate(bankPanel.getInput().getText(), upper, 0)) {
                    bankBiz.drawMoney(player, Integer.parseInt(bankPanel.getInput().getText()));
                    stage.refreshPlayer();
                    JOptionPane.showMessageDialog(null, "Draw successfully!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong input! Draw failed!");
                }
            }
        });
        ServicePanel servicePanel = new ServicePanel(bankPanel);
        bankPanel.getCancel().addActionListener(e -> {
            if(PlayerStep.getInstance().isCondition())
                servicePanel.process();
            else {
                servicePanel.refresh();
                Utility.wakeUpStep();
            }
        });
        int x = (Stage.getUniqueInstance().getWidth() - servicePanel.getWidth()) / 2;
        int y = (Stage.getUniqueInstance().getHeight() - servicePanel.getHeight()) / 2;
        Stage.getUniqueInstance().getLayeredPane().add(servicePanel, new Integer(Integer.MAX_VALUE));
        servicePanel.setBounds(x, y, servicePanel.getWidth(),servicePanel.getHeight());
    }

    @Override
    public void itemSpotService(JPanel panel, ItemSpot itemSpot, Player player) {
        ItemType type = new ItemSpotBizImpl().getItem(player);
        JTextArea label = new JTextArea("Congratulations! You have got a " + type);
        label.setLineWrap(true);
        JButton confirm = new JButton(); confirm.setIcon(new ImageIcon("pics/confirm.png"));
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setSize(334, 212);
        ServicePanel servicePanel = new ServicePanel(jPanel);
        Utility.initComponent(jPanel, label, 250, 100, (jPanel.getWidth() - 250)/2, (jPanel.getHeight() - 100)/2 - 30, Color.WHITE);
        Utility.initComponent(jPanel, confirm, 148, 37, (jPanel.getWidth() - 148)/2, 165, null);
        confirm.addActionListener(e -> servicePanel.process());
        int x = (Stage.getUniqueInstance().getWidth() - servicePanel.getWidth()) / 2;
        int y = (Stage.getUniqueInstance().getHeight() - servicePanel.getHeight()) / 2;
        Stage.getUniqueInstance().getLayeredPane().add(servicePanel, new Integer(Integer.MAX_VALUE));
        servicePanel.setBounds(x, y, servicePanel.getWidth(),servicePanel.getHeight());
    }

    @Override
    public void lotteryService(JPanel panel, Lottery lottery, Player player) {
        LotteryBizImpl lotteryBiz = new LotteryBizImpl();
        LotteryPanel lotteryPanel = new LotteryPanel();
        ServicePanel servicePanel = new ServicePanel(lotteryPanel);
        lotteryPanel.getConfirm().addActionListener(e -> {
            String string = lotteryPanel.getTextField().getText();
            if(Utility.validate(string, 200, 0)) {
                int choice = Integer.parseInt(string);
                if (lotteryBiz.isSame(choice))
                    JOptionPane.showMessageDialog(null, "Someone has bought this number! Please choose another number!");
                else {
                    lotteryBiz.buyLottery(player, choice);
                    Stage.getUniqueInstance().refreshPlayer();
                    JOptionPane.showMessageDialog(null, "Good luck! You've bought lottery:" + choice);
                    servicePanel.process();
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Wrong input! Failed!");
        });
        lotteryPanel.getCancel().addActionListener(e -> servicePanel.process());
        int x = (Stage.getUniqueInstance().getWidth() - servicePanel.getWidth()) / 2;
        int y = (Stage.getUniqueInstance().getHeight() - servicePanel.getHeight()) / 2;
        Stage.getUniqueInstance().getLayeredPane().add(servicePanel, new Integer(Integer.MAX_VALUE));
        servicePanel.setBounds(x, y, servicePanel.getWidth(),servicePanel.getHeight());
    }

    @Override
    public void newsService(JPanel panel, News news, Player player) {
        int num = 6;
        int event = (int)(Math.random() * num);
        NewsBizImpl newsBiz = new NewsBizImpl();
        String message = "";
        switch (event) {
            case 0: {
                ArrayList<Player> players = newsBiz.appraisal();
                String name = "";
                for (Player player1 : players) {
                    name = name + player1.getName() + ", ";
                }
                message = " We plan to give " + name + "$" + newsBiz.getPrize() + " for prize!";
                break;
            }
            case 1: {
                ArrayList<Player> players = newsBiz.assistance();
                String name = "";
                for (Player player1 : players) {
                    name = name + player1.getName() + ", ";
                }
                message = "We plan to give " + name + "$" + newsBiz.getPrize() + " for prize!";
                break;
            }
            case 2: {
                message = "New bonus: every player should get 10% deposit";
                newsBiz.bonus();
                break;
            }
            case 3: {
                message = "New policy: every player should pay 10% deposit";
                newsBiz.tax();
                break;
            }
            case 4:{
                message = "Good news! Everyone will get an item!";
                newsBiz.getItem();
                break;
            }
            case 5: {
                message = "Terrible! You have been sent to hospital";
                newsBiz.addToHospital(player);
                break;
            }
        }
        NewsPanel newsPanel = new NewsPanel(message);
        ServicePanel servicePanel = new ServicePanel(newsPanel);
        newsPanel.getConfirm().addActionListener(e -> servicePanel.process());
        int x = (Stage.getUniqueInstance().getWidth() - servicePanel.getWidth()) / 2;
        int y = (Stage.getUniqueInstance().getHeight() - servicePanel.getHeight()) / 2;
        Stage.getUniqueInstance().getLayeredPane().add(servicePanel, new Integer(Integer.MAX_VALUE));
        servicePanel.setBounds(x, y, servicePanel.getWidth(),servicePanel.getHeight());
    }

    @Override
    public void ticketService(JPanel panel, TicketSpot ticketSpot, Player player) {
        TicketSpotBizImpl ticketSpotBiz = new TicketSpotBizImpl();
        ticketSpotBiz.sendTicket(player);
        int ticket = ticketSpotBiz.getTicket();
        JTextArea label = new JTextArea("Congratulations!\n You get " + ticket + " tickets from the spot");
        Stage.getUniqueInstance().refreshPlayer();
        label.setLineWrap(true);
        JButton confirm = new JButton(); confirm.setIcon(new ImageIcon("pics/confirm.png"));
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setSize(334, 212);
        ServicePanel servicePanel = new ServicePanel(jPanel);
        Utility.initComponent(jPanel, label, 250, 100, (jPanel.getWidth() - 250)/2, (jPanel.getHeight() - 100)/2 - 30, Color.WHITE);
        Utility.initComponent(jPanel, confirm, 148, 37, (jPanel.getWidth() - 148)/2, 165, null);
        confirm.addActionListener(e -> servicePanel.process());
        int x = (Stage.getUniqueInstance().getWidth() - servicePanel.getWidth()) / 2;
        int y = (Stage.getUniqueInstance().getHeight() - servicePanel.getHeight()) / 2;
        Stage.getUniqueInstance().getLayeredPane().add(servicePanel, new Integer(Integer.MAX_VALUE));
        servicePanel.setBounds(x, y, servicePanel.getWidth(),servicePanel.getHeight());
    }

    @Override
    public void vacancyService(JPanel panel, Vacancy vacancy, Player player) {
        JOptionPane.showMessageDialog(null, "Vacancy...");
        ServicePanel servicePanel = new ServicePanel();
        servicePanel.process();
    }

    @Override
    public void itemShopService(JPanel panel, ItemShop itemShop, Player player) {
        ItemShopPanel itemShopPanel = new ItemShopPanel();
        Stage.getUniqueInstance().getLayeredPane().add(itemShopPanel, new Integer(Integer.MAX_VALUE));
        itemShopPanel.setBounds((panel.getWidth() - itemShopPanel.getWidth())/ 2, 0 - itemShopPanel.getHeight(), itemShopPanel.getWidth(), itemShopPanel.getHeight());
        itemShopPanel.setOpaque(false);

        SuspendThread.getUniqueInstance().setDown(true);
        SuspendThread.getUniqueInstance().setItemShopPanel(itemShopPanel);
        new Thread(SuspendThread.getUniqueInstance()).start();

        itemShopPanel.getRe().addActionListener(e -> {
            try {
                SuspendThread.getUniqueInstance().setDown(false);
                SuspendThread.getUniqueInstance().setItemShopPanel(itemShopPanel);
                new Thread(SuspendThread.getUniqueInstance()).start();
                SuspendThread.getUniqueInstance().join();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            new ServicePanel().process();
        });
    }

    @Override
    public void interestMonthly() {
        new BankBizImpl().sendInterest();
        JOptionPane.showMessageDialog(null, "Everyone will have interests from banks");
    }

    @Override
    public void runLottery() {
        int number = LotteryBizImpl.getLuckyNumber();
        Player player = LotteryBizImpl.winPrice();
        if (player == null)
            JOptionPane.showMessageDialog(null, "The lucky number is " + number +"\nWhat a pity! No one wins!");
        else
            JOptionPane.showMessageDialog(null, "The lucky number is " + number +"\nCongratulations!" + player.getName() + " wins the lottery");

    }
}
