package Entity.State;

import ActionImpl.HelloActionImpl;
import ActionImpl.HouseActionImpl;
import Entity.IState.State;
import Entity.Player;
import Entity.Service.House;
import Util.Utility;
import View.HousePanel;
import View.ServicePanel;
import View.Stage;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/25.
 * update house
 */
public class UpdateState implements State {
    protected HelloActionImpl helloAction = new HelloActionImpl();
    protected HouseActionImpl serviceAction = new HouseActionImpl();
    @Override
    public void buy(Player player, House house) {

    }

    @Override
    public void update(Player player, House house) {
        helloAction.houseHelloNoMansion(house);
        while (true) {
            if (!serviceAction.houseUpdateService(player, house))
                break;
        }
    }

    public void refresh(Player player, House house) {
        player.getHouses().remove(house);
        Utility.updateHouse(house, house.getCost(), null, house.getLevel(), house.getProfit(), house.getSymbol());
        house.setState(house.getNoOwner());
    }

    @Override
    public void pay(Player player, House house) {
        helloAction.houseHelloOthers(house);
        serviceAction.houseIntrudeService(player, house);
    }

    @Override
    public void buy_GUI(JPanel panel, Player player, House house) {

    }

    @Override
    public void update_GUI(JPanel panel, Player player, House house) {
        HousePanel housePanel = new HousePanel();
        housePanel.setHouse(house);
        housePanel.update();
        ServicePanel servicePanel = new ServicePanel(housePanel);
        housePanel.setServicePanel(servicePanel);
        int x = (Stage.getUniqueInstance().getWidth() - servicePanel.getWidth()) / 2;
        int y = (Stage.getUniqueInstance().getHeight() - servicePanel.getHeight()) / 2;
        Stage.getUniqueInstance().getLayeredPane().add(servicePanel, new Integer(Integer.MAX_VALUE));
        servicePanel.setBounds(x, y, servicePanel.getWidth(),servicePanel.getHeight());
    }

    @Override
    public void pay_GUI(JPanel panel, Player player, House house) {
        HousePanel housePanel = new HousePanel();
        housePanel.setHouse(house);
        housePanel.pay();
        ServicePanel servicePanel = new ServicePanel(housePanel);
        housePanel.setServicePanel(servicePanel);
        int x = (Stage.getUniqueInstance().getWidth() - servicePanel.getWidth()) / 2;
        int y = (Stage.getUniqueInstance().getHeight() - servicePanel.getHeight()) / 2;
        Stage.getUniqueInstance().getLayeredPane().add(servicePanel, new Integer(Integer.MAX_VALUE));
        servicePanel.setBounds(x, y, servicePanel.getWidth(),servicePanel.getHeight());
    }
}
