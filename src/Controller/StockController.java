package Controller;

import BizImpl.StockBizImpl;
import Entity.Player;
import Entity.Stock;
import View.Stage;
import View.StockFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/6/10.
 */
public class StockController implements IStockController {
    StockBizImpl stockBiz = new StockBizImpl();
    @Override
    public void initStock() {
        stockBiz.initStock();
    }

    @Override
    public CategoryDataset createDataSet(Stock stock) {
        ArrayList<Integer> history = stock.getHistory();
        String[] rowKeys = {stock.getName()};
        String[] colKeys = new String[history.size()];
        double[][] data = new double[1][history.size()];
        for (int i = 0; i < history.size(); i++) {
            data[0][i] = history.get(i);
            colKeys[i] = String.valueOf(i);
        }
        // 或者使用类似以下代码
        // DefaultCategoryDataset categoryDataset = new
        // DefaultCategoryDataset();
        // categoryDataset.addValue(10, "rowKey", "colKey");

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }

    @Override
    public JFreeChart createChart(CategoryDataset dataset, Stock stock) {
        JFreeChart jfreechart = ChartFactory.createLineChart(stock.getName(), // 标题
                "Day", // categoryAxisLabel （category轴，横轴，X轴标签）
                "Value", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                dataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs

        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其他设置 参考 CategoryPlot类

        return jfreechart;
    }

    @Override
    public void processStock(Player player) {
        StockFrame stockFrame = new StockFrame("Stock Market");
        stockFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Stage.getUniqueInstance().setVisible(true);
            }
        });
        Stage.getUniqueInstance().setVisible(false);
        stockFrame.setVisible(true);
    }

    @Override
    public void endStock() {
        stockBiz.updateStock();
    }
}
