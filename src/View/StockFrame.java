package View;

import BizImpl.StockBizImpl;
import Controller.StockController;
import Entity.Player;
import Entity.Stock;
import Util.Logic;
import Util.StockMarket;
import Util.Utility;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/10.
 * The stock frame
 */
public class StockFrame extends JFrame {
    private StockController stockController = new StockController();
    private StockBizImpl stockBiz = new StockBizImpl();
    private StockTable stockTable = new StockTable();
    private  ChartPanel chartPanel;
    public StockFrame(String title) {
        super(title);
        setSize(700, 800);
        CategoryDataset dataSet = stockController.createDataSet(StockMarket.getUniqueInstance().getStocks().get(0));
        JFreeChart chart = stockController.createChart(dataSet, StockMarket.getUniqueInstance().getStocks().get(0));
        chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        chartPanel.setMouseZoomable(true, false);
        setLayout(new GridLayout(2, 1));
        add(chartPanel);
        add(stockTable);
        addListener();
        setLocationRelativeTo(null);
    }
    private void addListener() {
        JTable table = stockTable.getTable();
        ArrayList<Stock> stocks = StockMarket.getUniqueInstance().getStocks();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                String s = (String) table.getValueAt(row,0);
                Stock target = stocks.stream().filter(stock -> stock.getName().equals(s)).findFirst().orElse(null);
                Player player = Logic.getUniqueInstance().getCurrentPlayer();
                if(e.getClickCount() == 2) {
                    if (target != null) {
                        Object[] options ={ "buy", "sell" };
                        int m = JOptionPane.showOptionDialog(null, "What's your choice?", "choice",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if(m != -1) {
                            if (m == 0) {
                                String number = (String) JOptionPane.showInputDialog(null, "How many to buy：\n", "title", JOptionPane.PLAIN_MESSAGE, null, null, "");
                                if(number != null) {
                                    if (Utility.isNumber(number) && !number.equals("0")) {
                                        int value = Integer.parseInt(number);
                                        int money = target.getPrice() * value;
                                        if (player.getAsset() > money) {
                                            stockBiz.buyStock(player, target, Integer.parseInt(number));
                                            stockTable.refreshStock(Logic.getUniqueInstance().getCurrentPlayer(), target);
                                            JOptionPane.showMessageDialog(null, "You have bought " + value + " " + target.getName() + " successfully!");
                                        } else
                                            JOptionPane.showMessageDialog(null, "More money will be required!");
                                    } else
                                        JOptionPane.showMessageDialog(null, "Wrong input!");
                                }
                            } else {
                                String number = (String) JOptionPane.showInputDialog(null, "How many to sell：\n", "title", JOptionPane.PLAIN_MESSAGE, null, null, "");
                                if(number != null) {
                                    if (Utility.isNumber(number)) {
                                        int value = Integer.parseInt(number);
                                        if (player.getStockIntegerHashMap().get(target) != null && value <= player.getStockIntegerHashMap().get(target) && value > 0) {
                                            stockBiz.sellStock(player, target, value);
                                            stockTable.refreshStock(Logic.getUniqueInstance().getCurrentPlayer(), target);
                                            JOptionPane.showMessageDialog(null, "You have sold " + value + " " + target.getName() + " successfully!");
                                        } else
                                            JOptionPane.showMessageDialog(null, "Wrong stocks input!");
                                    } else
                                        JOptionPane.showMessageDialog(null, "Wrong input!");
                                }
                            }
                        }
                    }
                }
                if (e.getClickCount() == 1) {
                    CategoryDataset dataSet = stockController.createDataSet(target);
                    JFreeChart chart = stockController.createChart(dataSet, target);
                    chartPanel.setChart(chart);
                }
            }
        });
    }
}
//stock table
class StockTable extends JPanel {
    private JTable table;
    private String[][] obj;
    public StockTable() {
        intiComponent();
    }
    private void intiComponent() {
        //table column names
        ArrayList<Player> players = Logic.getUniqueInstance().getPlayers();
        int size = 4 + players.size();
        String[] columnNames = new String[size];
        columnNames[0] = "stock name";
        columnNames[1] = "deal";
        columnNames[2] = "increase";
        columnNames[3] = "average";
        for (int i = 0; i < players.size(); i++) {
            columnNames[i + 4] = players.get(i).getName();
        }
        ArrayList<Stock> stocks = StockMarket.getUniqueInstance().getStocks();
        obj = new String[stocks.size()][size];
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < obj[0].length; j++) {
                Stock stock = stocks.get(i);
                ArrayList<Integer> hi = stock.getHistory();
                final Integer sum = hi.stream().reduce(0, (a, b) -> a + b);
                int average = sum / hi.size();
                switch (j) {
                    case 0:
                        obj[i][j] = stock.getName();
                        break;
                    case 1:
                        obj[i][j] = String.valueOf(stock.getPrice());
                        break;
                    case 2:
                        obj[i][j] = stock.getIncrease();
                        break;
                    case 3:
                        obj[i][j] = String.valueOf(average);
                        break;
                    default:
                        obj[i][j] = String.valueOf(players.get(j - 4).getStock(stocks.get(i)));
                        break;
                }
            }
        }

        table = new JTable(obj, columnNames);

        //width and height
        TableColumn column;
        table.setEnabled(false);
        int colunms = table.getColumnCount();
        for(int i = 0; i < colunms; i++) {
            column = table.getColumnModel().getColumn(i);
            /*将每一列的默认宽度设置为100*/
            column.setPreferredWidth(70);
        }
        /*
         * 设置JTable自动调整列表的状态，此处设置为关闭
         */
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        /*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/
        JScrollPane scroll = new JScrollPane(table);
        scroll.setSize(300, 200);
        add(scroll);
    }

    public JTable getTable() {
        return table;
    }

    public void refreshStock(Player player, Stock stock) {
        int row =  StockMarket.getUniqueInstance().getStocks().indexOf(stock);
        int column = 4 + Logic.getUniqueInstance().getPlayers().indexOf(player);
        if (player.getStockIntegerHashMap().get(stock) != null)
            obj[row][column] = player.getStockIntegerHashMap().get(stock).toString();
        else
            obj[row][column] = "0";
        repaint();
    }
}
