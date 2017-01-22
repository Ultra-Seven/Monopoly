package ActionImpl;

import Action.IInformationAction;
import BizImpl.InformationBizImpl;
import Entity.Player;
import SuperEntity.Stuff;
import Util.Map;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/29.
 */
public class InformationActionImpl implements IInformationAction {
    InformationBizImpl informationBiz = new InformationBizImpl();
    @Override
    public void alarm(Player player) {
        ArrayList<Integer> barrierIndex = informationBiz.alarm(player);
        if (barrierIndex.size() > 0) {
            barrierIndex.stream().forEach(e -> System.out.println("There is a barrier at " + e + " steps in front of you!\n"));
        }
        else
            System.out.println("Clear!");
    }

    @Override
    public void printAsset() {
        System.out.println("Player\tCash\tDeposit\tTicket\tHouse\tAsset");
        informationBiz.asset();
    }

    @Override
    public void specificInformation(Player player, int step) {
        Stuff stuff = Map.getUniqueInstance().getPoints().get(step).getStuff().get(0);
        stuff.printInformation();
    }
}
