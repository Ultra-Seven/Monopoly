package Action;

import Entity.Player;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/29.
 */
public interface IProcessAction {
    public void start();
    public void step(Player player);
    public void react(Player player, int choice);
    public void display();
    public boolean isEnd();
}
