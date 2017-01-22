package View;

import BizImpl.StepBizImpl;
import Entity.Player;

/**
 * Created by lenovo on 2016/6/10.
 * The thread handling the walking step of a player
 */
public class PlayerStep extends Thread{
    private static PlayerStep uniqueInstance;
    private int start;
    private int end;
    private Player player;
    private StepBizImpl stepBiz = new StepBizImpl();
    private PointDisplay pre;
    private boolean condition = true;
    private PlayerStep() {
    }
    public static PlayerStep getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PlayerStep();
        }
        return uniqueInstance;
    }
    @Override
    public void run() {
        try {
            stepBiz.react_GUI(player, start, end, pre.getPoints());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public void setPre(PointDisplay pre) {
        this.pre = pre;
    }
    //semaphore for passing the bank
    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public boolean isCondition() {
        return condition;
    }
}
