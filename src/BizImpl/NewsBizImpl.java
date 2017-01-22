package BizImpl;

import Biz.INewsBiz;
import Entity.Player;
import Entity.Point;
import Entity.Service.Hospital;
import SuperEntity.Item;
import Util.ItemType;
import Util.Logic;
import Util.Map;
import Util.PointType;
import View.Stage;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/9.
 */
public class NewsBizImpl implements INewsBiz {
    private int prize;
    @Override
    public ArrayList<Player> appraisal() {
        ArrayList<Player> players = getTargetPlayer(true);
        prize = (int)(Math.random()*1000);
        update(players, prize);
        return players;
    }

    @Override
    public ArrayList<Player> assistance() {
        ArrayList<Player> players = getTargetPlayer(false);
        prize = (int)(Math.random()*1000);
        update(players, prize);
        return players;
    }

    @Override
    public void bonus() {
        updateDeposit(1.1);
    }

    @Override
    public void tax() {
        updateDeposit(0.9);
    }

    @Override
    public void getItem() {
        ArrayList<Player> players = Logic.getUniqueInstance().getPlayers();
        for (Player player : players) {
            int random = (int) (Math.random() * (ItemType.values().length));
            ItemType type = ItemType.values()[random];
            Item item = ItemType.getItem(type);
            item.setPlayer(player);
            player.addItem(item);
        }
    }

    @Override
    public void addToHospital(Player player) {
        Point points = Map.getUniqueInstance().getPoints().stream().filter(point -> point.getStuff().get(0).getType() == PointType.Hospital).findFirst().orElse(null);
        Hospital hospital = (Hospital) points.getStuff().get(0);
        hospital.addToHospital(player);
        int position = player.getPosition();
        Map.getUniqueInstance().getPoints().get(position).getStuff().remove(player);
        Stage.getUniqueInstance().refreshPoint(position);
        position = hospital.getPosition();
        player.setPosition(position);
        Map.getUniqueInstance().getPoints().get(position).getStuff().add(player);
        Stage.getUniqueInstance().refreshPoint(hospital.getPosition());
        player.setDirection(false);
    }

    private void updateDeposit(double ratio) {
        ArrayList<Player> players = Logic.getUniqueInstance().getPlayers();
        for (Player player : players) {
            int deposit = (int) (player.getDeposit() * ratio);
            player.setDeposit(deposit);
        }
    }
    private ArrayList<Player> getTargetPlayer(boolean max) {
        ArrayList<Player> players = Logic.getUniqueInstance().getPlayers();
        ArrayList<Player> re = new ArrayList<>();
        int max_size = 0;
        int mask = max ? 1 : -1;
        for (Player player : players) {
            int size = mask * player.getHouses().size();
            if (size >= max_size) {
                max_size = size;
            }
        }
        for (Player player : players) {
            int size = mask * player.getHouses().size();
            if (size == max_size) re.add(player);
        }
        return re;
    }
    private void update(ArrayList<Player> players, int add) {
        for (Player player : players) {
            player.setCash(player.getCash() + add);
        }
    }
    public int getPrize() {
        return prize;
    }
}
