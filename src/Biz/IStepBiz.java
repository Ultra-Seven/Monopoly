package Biz;

import Entity.Player;
import Entity.Point;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/4.
 */
public interface IStepBiz {
    public ArrayList<Point> getReactPoint(Player player, int start, int end);
    public void cleanBarrier(Point point);
    public boolean isStop(Point point);
    public void removePlayer(Player player);
}
