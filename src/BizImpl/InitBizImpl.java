package BizImpl;

import Biz.IInitBiz;
import Entity.Player;
import Util.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/4.
 */
public class InitBizImpl implements IInitBiz {
    @Override
    public void getPlayer(int number) {
        ArrayList<Player> players = new ArrayList<Player>();
        for (int i = 0; i < number; i++) {
            Player player = new Player();
            players.add(player);
            player.setHouseImage(HouseImage.getHouseImage(HouseImage.values()[i]));
            Map.getUniqueInstance().getPoints().get(0).getStuff().add(1, player);
        }
        Logic.getUniqueInstance().setPlayers(players);
    }

    @Override
    public void inputName(int index, String name) {
        Player player = Logic.getUniqueInstance().getPlayers().get(index);
        player.setName(name);
        player.setSymbol(SymbolType.values()[index].toString());
        player.setHouseSymbol(String.valueOf(index + 1));
    }

    @Override
    public void setTime(int day) {
        TimeCount.getInstance().setTimeBoundary(day);
    }

    @Override
    public void setPlayerAssert(int cash) {
        Logic.getUniqueInstance().getPlayers().stream().forEach(e->e.setCash(cash));
    }

    @Override
    public void setIcon(int index, ImageIcon icon) {
        Player player = Logic.getUniqueInstance().getPlayers().get(index);
        player.setImage(icon);
        player.setHouseImage(HouseImage.getHouseImage(HouseImage.values()[index]));
    }
}
