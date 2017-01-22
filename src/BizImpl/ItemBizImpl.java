package BizImpl;

import Biz.IItemBiz;
import Entity.Items.TaxCard;
import Entity.Player;
import Entity.Point;
import SuperEntity.Item;
import Util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2016/4/3.
 */
public class ItemBizImpl implements IItemBiz {
    @Override
    public HashMap<ItemType,Integer> returnAllItems(Player player) {
        ArrayList<Item> items = player.getItems();
        HashMap<ItemType, Integer> hashMap = new HashMap<>();
        for (Item item1 : items) {
            ItemType item = item1.getItemType();
            if (hashMap.get(item) == null || hashMap.get(item) == 0) {
                hashMap.put(item, 1);
            } else {
                int count = hashMap.get(item) + 1;
                hashMap.replace(item, count);
            }
        }
        return hashMap;
    }

    @Override
    public int averageMoney(Player player, Item item) {
        ArrayList<Player> players = Logic.getUniqueInstance().getPlayers();
        int sum = 0;
        for (Player player2 : players) {
            sum += player2.getCash();
        }
        sum /= players.size();
        final int finalCash = sum;
        players.stream().forEach(player1 -> player1.setCash(finalCash));
        player.getItems().remove(item);
        return sum;
    }

    @Override
    public void setBarrier(Player player, Point point, Item item) {
        point.getItems().add(ItemType.Barrier);
        player.getItems().remove(item);
    }

    @Override
    public void setDice(Player player, int dice, Item item) {
        Dice.getInstance().setDiceByControl(dice);
        Dice.getInstance().setControlled(true);
        player.getItems().remove(item);
    }

    @Override
    public Item robItem(Player player, Player target, Item item) {
        ArrayList<Item> items = target.getItems();
        int random = (int) (Math.random() * items.size());
        Item robItem = items.remove(random);
        player.addItem(robItem);
        player.getItems().remove(item);
        return robItem;
    }

    @Override
    public void changeDirection(Player player, Player target, Item item) {
        target.setDirection(!target.getDirection());
        player.getItems().remove(item);
    }

    @Override
    public void stayAtPoint(Player player, Point point, Item item) {
        point.getItems().add(ItemType.StopCard);
        player.getItems().remove(item);
    }

    @Override
    public void payTax(Player player, ArrayList<Player> players, Item item) {
        players.stream().forEach(player1 -> player1.setDeposit((int)(player1.getDeposit() * (1 - TaxCard.getRate()))));
        player.getItems().remove(item);
    }

    @Override
    public ArrayList<Player> getRangePlayer(Player player, int range, boolean myself) {
        ArrayList<Player> players = new ArrayList<>();
        if (myself)
            players.add(player);
        int size = Map.getUniqueInstance().getPoints().size();
        int position = player.getPosition();
        List<Player> playerArrayList= Logic.getUniqueInstance().getPlayers().stream()
                .filter(player1 -> (((Math.abs(position - player1.getPosition()) + size) % size <= range || (size - Math.abs(position - player1.getPosition())) % size <= range) && player1 != player))
                .collect(Collectors.toList());
        for (Player aPlayerArrayList : playerArrayList) {
            players.add(aPlayerArrayList);
        }
        return players;
    }
}
