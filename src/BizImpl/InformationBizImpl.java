package BizImpl;
import Biz.IInformationBiz;
import Entity.Player;
import Entity.Point;
import SuperEntity.Item;
import SuperEntity.Stuff;
import Util.ItemType;
import Util.Logic;
import Util.Map;
import Util.Utility;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lenovo on 2016/4/5.
 */
public class InformationBizImpl implements IInformationBiz {
    private int alarmSize = 10;
    @Override
    public ArrayList<Integer> alarm(Player player) {
        ArrayList<Integer> re = new ArrayList<>();
        int position = player.getPosition();
        int mask = player.getDirection() ? 1 : -1;
        for (int i = 0; i < alarmSize; i++) {
            int size = Map.getUniqueInstance().getPoints().size();
            int pos = (position + mask * i + size) % size;
            if (hasBarrier(Map.getUniqueInstance().getPoints().get(pos)) != null) {
                re.add(i);
            }
        }
        return re;
    }

    @Override
    public void specificStep(Player player, int step) {

    }

    @Override
    public void asset() {
        ArrayList<Player> players = Logic.getUniqueInstance().getPlayers();
        players.stream().forEach(e -> e.printInformation());
    }

    private ItemType hasBarrier(Point point) {
        return point.getItems().stream().filter(e->(e == ItemType.Barrier)).findFirst().orElse(null);
    }
}
