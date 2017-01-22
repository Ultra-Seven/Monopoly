package Biz;

import Entity.Player;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/5.
 */
public interface IInformationBiz {
    //alarm information
    public ArrayList<Integer> alarm(Player player);
    public void specificStep(Player player, int step);
    //asset information
    public void asset();
}
