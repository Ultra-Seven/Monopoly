package Action;

import Entity.Player;

/**
 * Created by lenovo on 2016/4/30.
 */
public interface IStockAction {
    public void initStock();
    public void processStock(Player player);
    public void endStock();
}
