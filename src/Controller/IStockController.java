package Controller;

import Entity.Player;
import Entity.Stock;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;

/**
 * Created by lenovo on 2016/6/10.
 * The interface retaining with some business about stocks
 */
public interface IStockController {
    public void initStock();
    //create a data set for drawing the price picture
    public CategoryDataset createDataSet(Stock stock);
    //create a chart
    public JFreeChart createChart(CategoryDataset dataset, Stock stock);
    //running the stock market
    public void processStock(Player player);
    public void endStock();
}
