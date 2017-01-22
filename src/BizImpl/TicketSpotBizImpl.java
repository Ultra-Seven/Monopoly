package BizImpl;

import Biz.ITicketSpotBiz;
import Entity.Player;

/**
 * Created by lenovo on 2016/4/28.
 */
public class TicketSpotBizImpl implements ITicketSpotBiz {
    private int ticket;
    @Override
    public void sendTicket(Player player) {
        ticket = (int)(Math.random() * 50);
        player.setTicket(player.getTicket()+ticket);
    }

    public int getTicket() {
        return ticket;
    }
}
