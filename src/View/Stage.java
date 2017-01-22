package View;

import BizImpl.InformationBizImpl;
import BizImpl.ItemBizImpl;
import BizImpl.StepBizImpl;
import Controller.StockController;
import Entity.*;
import Entity.Service.Hospital;
import Util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/7.
 * The main stage of the game
 * player must do something in this panel
 */
public class Stage extends JFrame {
    private static Stage uniqueInstance;
    private MapDisplay mapDisplay = new MapDisplay();
    private Stage() {
        setLayout(null);
        setSize(1200,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Monopoly");
        add(mapDisplay);
        getLayeredPane().add(mapDisplay.getBag(), new Integer(Integer.MAX_VALUE - 1));
        addMenu();
    }
    public static Stage getUniqueInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Stage();
        }
        return uniqueInstance;
    }

    public MapDisplay getMapDisplay() {
        return mapDisplay;
    }

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu doc = new JMenu("Game");
        JMenuItem  lose = new JMenuItem ("Give up");
        JMenu check = new JMenu("Check");
        JMenuItem  player = new JMenuItem ("Player information");
        JMenuItem  alert = new JMenuItem ("Alert");
        setJMenuBar(menuBar);
        menuBar.add(doc);menuBar.add(check);
        doc.add(lose);
        check.add(player);check.add(alert);
        lose.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "You leave the game!");
            new StepBizImpl().removePlayer(Logic.getUniqueInstance().getCurrentPlayer());
            while (true) {
                Logic.getUniqueInstance().process();
                if(Hospital.getRound(Logic.getUniqueInstance().getCurrentPlayer()) == 0) {
                    Stage.getUniqueInstance().refreshState();
                    break;
                }
                else {
                    Stage.getUniqueInstance().refreshState();
                    JOptionPane.showMessageDialog(null, "During the treatment...You must stay at hospital!");
                    Hospital.reduceCount();
                }
            }
        });
        player.addActionListener(e -> {
            String message = "";
            for (int i = 0; i < Logic.getUniqueInstance().getPlayers().size(); i++) {
                message = message + Logic.getUniqueInstance().getPlayers().get(i).getInformation();
            }
            JOptionPane.showMessageDialog(null, message);
        });
        alert.addActionListener(e -> {
            String message = "";
            ArrayList<Integer> index = new InformationBizImpl().alarm(Logic.getUniqueInstance().getCurrentPlayer());
            for (Integer anIndex : index) {
                message = message + "There is a barrier at the distance of " + anIndex + "\n";
            }
            if (index.size() == 0)
                message = "Clear!";
            JOptionPane.showMessageDialog(null, message);
        });
    }

    public void refreshState() {
        mapDisplay.getPlayerState().setPlayerInformation();
        mapDisplay.setBag(new ItemShopPanel(new ItemBizImpl().returnAllItems(Logic.getUniqueInstance().getCurrentPlayer())));
        ItemShopPanel bag = mapDisplay.getBag();
        bag.setBounds((this.getWidth() - bag.getWidth())/ 2, 0 - bag.getHeight(), bag.getWidth(), bag.getHeight());
        bag.setOpaque(false);
        getLayeredPane().add(bag, new Integer(Integer.MAX_VALUE));
    }
    public void refreshPoint(int index) {
        JLabel label = mapDisplay.getPointDisplay().getPoints().get(index);
        int last = Map.getUniqueInstance().getPoints().get(index).getStuff().size() - 1;
        label.setIcon(Utility.getImageIcon(Map.getUniqueInstance().getPoints().get(index).getStuff().get(last).getImage(), label.getWidth(), label.getHeight()));
    }
    public void refreshPlayer() {
        mapDisplay.getPlayerState().setPlayerInformation();
    }
}
//map display. Every player walk on this panel.
class MapDisplay extends JPanel {
    private DicePanel dicePanel = new DicePanel();
    private PlayerState playerState = new PlayerState();
    private Inventory inventory = new Inventory();
    private PointDisplay pointDisplay = new PointDisplay();
    private ItemShopPanel bag = new ItemShopPanel(new ItemBizImpl().returnAllItems(Logic.getUniqueInstance().getCurrentPlayer()));
    private StockPanel stockPanel = new StockPanel();
    private MouseAdapter stockListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1 ) {
                if (!TimeCount.getInstance().isWeekend())
                    new StockController().processStock(Logic.getUniqueInstance().getCurrentPlayer());
                else
                    JOptionPane.showMessageDialog(null, "This is weekend and the market is closed!");
            }
        }
    };
    private MouseAdapter mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            dicePanel.removeMouseListener(this);
            inventory.removeMouseListener(bagListener);
            stockPanel.removeMouseListener(stockListener);
            Thread dice = new Thread(dicePanel.getRollingDice());
            dice.start();
        }
    };
    private MouseAdapter bagListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
                if (!SuspendThread.getUniqueInstance().isExit()) {
                    SuspendThread.getUniqueInstance().setExit(true);
                    try {
                        SuspendThread.getUniqueInstance().join();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                SuspendThread.getUniqueInstance().setItemShopPanel(bag);
                SuspendThread.getUniqueInstance().setDown(inventory.isDown());
                inventory.setDown(!inventory.isDown());
                new Thread(SuspendThread.getUniqueInstance()).start();
                if (inventory.isDown()) {
                    dicePanel.addMouseListener(mouseListener);
                    stockPanel.addMouseListener(stockListener);
                }
                else {
                    dicePanel.removeMouseListener(mouseListener);
                    stockPanel.removeMouseListener(stockListener);
                }
            }
        }
    };
    MapDisplay() {
        setLayout(null);
        setSize(1200, 700);
        pointDisplay.setParent(this);

        dicePanel.setPointDisplay(pointDisplay);
        dicePanel.setToolTipText("Dice");
        Utility.initComponent(this, playerState, playerState.getWidth(), playerState.getHeight(), (this.getWidth() - playerState.getWidth()) / 2, this.getHeight() - playerState.getHeight(), null);
        Utility.initComponent(this, pointDisplay, pointDisplay.getWidth(), pointDisplay.getHeight(), (this.getWidth() - pointDisplay.getWidth()) / 2, 20, null);
        Utility.initComponent(this, dicePanel, dicePanel.getWidth(), dicePanel.getHeight(), 20, 400, null);
        Utility.initComponent(this, inventory, inventory.getWidth(), inventory.getHeight(), 20, 520, null);
        Utility.initComponent(this, stockPanel, stockPanel.getWidth(), stockPanel.getHeight(), 20, 280, null);
        bag.setBounds((this.getWidth() - bag.getWidth())/ 2, 0 - bag.getHeight(), bag.getWidth(), bag.getHeight());
        bag.setOpaque(false);
        inventory.setToolTipText("Item bag");
        addListener();
        stockPanel.setToolTipText("Stock Market");
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(new ImageIcon("pics/map.png").getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
    }
    public void addListener() {
        dicePanel.addMouseListener(mouseListener);
        inventory.addMouseListener(bagListener);
        stockPanel.addMouseListener(stockListener);
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setBag(ItemShopPanel bag) {
        this.bag = bag;
    }

    public ItemShopPanel getBag() {
        return bag;
    }

    public PointDisplay getPointDisplay() {
        return pointDisplay;
    }
}
//the bottom of the frame used as the display of player state
class PlayerState extends JPanel {
    private JLabel playerImage = new JLabel();
    private JLabel name = new JLabel(Logic.getUniqueInstance().getPlayers().get(0).getName());
    private JLabel cash = new JLabel(String.valueOf(Logic.getUniqueInstance().getPlayers().get(0).getCash()));
    private JLabel deposit = new JLabel(String.valueOf(Logic.getUniqueInstance().getPlayers().get(0).getDeposit()));
    private JLabel stock = new JLabel(String.valueOf(Logic.getUniqueInstance().getPlayers().get(0).getStock()));
    private JLabel ticket = new JLabel(String.valueOf(Logic.getUniqueInstance().getPlayers().get(0).getTicket()));
    private JLabel asset = new JLabel(String.valueOf(Logic.getUniqueInstance().getPlayers().get(0).getAsset()));
    private JLabel timeLabel = new JLabel(TimeCount.getInstance().getTime());
    PlayerState() {
        setLayout(null);
        setSize(800, 200);
        Utility.initComponent(this, playerImage,150, 150, 130, 30, null);
        ImageIcon player = Logic.getUniqueInstance().getCurrentPlayer().getImage();
        playerImage.setIcon(Utility.getImageIcon(player, 100, 100));
        JLabel name_left = new JLabel("name:");
        Utility.initComponent(this, timeLabel, 200, 20, this.getWidth() - 370 , 40, Color.white);
        Utility.initComponent(this, name_left, 80, 20, 350, 80, Color.white);Utility.initComponent(this, name, 80, 20, 410, 80, Color.white);
        JLabel cash_left = new JLabel("cash:");
        Utility.initComponent(this, cash_left, 80, 20, 350, 110, Color.white);Utility.initComponent(this, cash, 80, 20, 400, 110, Color.white);
        JLabel deposit_left = new JLabel("deposit:");
        Utility.initComponent(this, deposit_left, 80, 20, 350, 140, Color.white);Utility.initComponent(this, deposit, 80, 20, 420, 140, Color.white);
        JLabel stock_left = new JLabel("stock:");
        Utility.initComponent(this, stock_left, 80, 20, 600, 80, Color.white);Utility.initComponent(this, stock, 80, 20, 660, 80, Color.white);
        JLabel ticket_left = new JLabel("ticket:");
        Utility.initComponent(this, ticket_left, 80, 20, 600, 110, Color.white);Utility.initComponent(this, ticket, 80, 20, 660, 110, Color.white);
        JLabel asset_left = new JLabel("asset:");
        Utility.initComponent(this, asset_left, 80, 20, 600, 140, Color.white);Utility.initComponent(this, asset, 80, 20, 650, 140, Color.white);

    }
    public void setPlayerInformation() {
        Player current = Logic.getUniqueInstance().getCurrentPlayer();
        playerImage.setIcon(Utility.getImageIcon(current.getImage(), 100, 100));
        name.setText(String.valueOf(current.getName()));
        cash.setText(String.valueOf(current.getCash()));
        deposit.setText(String.valueOf(current.getDeposit()));
        stock.setText(String.valueOf(current.getStock()));
        ticket.setText(String.valueOf(current.getTicket()));
        asset.setText(String.valueOf(current.getAsset()));
        timeLabel.setText(TimeCount.getInstance().getTime());
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(new ImageIcon("pics/state.png").getImage(), 0, 0, 800, 215, this);
    }
}
class PointDisplay extends JPanel {
    private ArrayList<JLabel> points = new ArrayList<>();
    private PlayerStep step = PlayerStep.getInstance();

    PointDisplay() {
        setLayout(null);
        setSize(1010, 475);
        step.setPre(this);
        int size = Map.getUniqueInstance().getSize();
        for (int i = 0; i < size; i++) {
            points.add(new JLabel());
        }
        points.stream().forEach(e -> {
            int index = points.indexOf(e);
            int sizePoints = Map.getUniqueInstance().getPoints().get(index).getStuff().size();
            e.setIcon(Utility.getImageIcon(Map.getUniqueInstance().getPoints().get(index).getStuff().get(sizePoints - 1).getImage(), 40, 40));
            Utility.initComponent(this, e, 40, 40, Map.getUniqueInstance().getY(index), Map.getUniqueInstance().getX(index), null);
            e.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2)
                        JOptionPane.showMessageDialog(null, Map.getUniqueInstance().getPoints().get(index).getStuff().get(0).getInformation());
                }
            });
        });
    }

    public PlayerStep getStep() {
        return step;
    }

    public void setParent(MapDisplay parent) {
        MapDisplay parent1 = parent;
    }

    public ArrayList<JLabel> getPoints() {
        return points;
    }
}
//dice panel
class DicePanel extends JLabel {
    private ImageIcon[] dice = {new ImageIcon("pics/dice1.jpg"), new ImageIcon("pics/dice2.jpg"), new ImageIcon("pics/dice3.jpg"), new ImageIcon("pics/dice4.jpg"),
            new ImageIcon("pics/dice5.jpg"), new ImageIcon("pics/dice6.jpg"),};
    private RollingDice rollingDice = new RollingDice();
    private static int dices = 1;
    private PointDisplay pointDisplay;
    DicePanel() {
        setIcon(dice[0]);
        setSize(82, 82);
    }

    public RollingDice getRollingDice() {
        return rollingDice;
    }

    public static int getDices() {
        return dices;
    }

    public void setPointDisplay(PointDisplay pointDisplay) {
        this.pointDisplay = pointDisplay;
    }

    class RollingDice implements Runnable {
        private int index = 0;
        @Override
        public void run() {
            int fi = Dice.getInstance().getDice();
            for (int i = 0; i < 10; i++) {
                index = (i == 9) ? (fi - 1) : (int) (Math.random() * 6);
                setIcon(dice[index]);
                try {
                    Thread.sleep(i * 50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dices = index + 1;
            int size = Map.getUniqueInstance().getSize();
            int start = Logic.getUniqueInstance().getCurrentPlayer().getPosition();
            int end = Logic.getUniqueInstance().getCurrentPlayer().isDirection() ? (start + DicePanel.getDices()) % size : (start - DicePanel.getDices() + size) % size;
            pointDisplay.getStep().setPlayer(Logic.getUniqueInstance().getCurrentPlayer());
            pointDisplay.getStep().setStart(start);
            pointDisplay.getStep().setEnd(end);
            new Thread(pointDisplay.getStep()).start();
        }
    }
}
//stock panel
class StockPanel extends JPanel {
    StockPanel() {
        setSize(100, 100);
        setLayout(null);
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(new ImageIcon("pics/stock.png").getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
    }
}