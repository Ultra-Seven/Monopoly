package BizImpl;

import Biz.IStepBiz;
import Entity.Player;
import Entity.Point;
import SuperEntity.Stuff;
import Util.*;
import View.PlayerStep;
import View.Stage;

import javax.swing.*;
import java.util.ArrayList;


/**
 * Created by lenovo on 2016/4/4.
 */
public class StepBizImpl implements IStepBiz {
    private HouseBizImpl houseBiz = new HouseBizImpl();
    public StepBizImpl() {

    }
    @Override
    public ArrayList<Point> getReactPoint(Player player, int start, int end) {
        ArrayList<Point> points = Map.getUniqueInstance().getPoints();
        ArrayList<Point> reactors = new ArrayList<>();
        int mask = player.isDirection() ? 1 : -1;
        int index = start;
        int size = points.size();
        Stuff service;
        Point point;
        int end_step = (end + mask + size) % size;
        do {
            point = points.get(index);
            service = point.getStuff().get(0);
            if (isStop(point)) {
                reactors.add(point);
                break;
            }
            if(index != end && index != start && service.getType() == PointType.Bank) {
                reactors.add(point);
            }
            index =  (index + mask + size) % size;
        }while (index != end_step);
        if (reactors.size() == 0 || reactors.get(reactors.size()-1) != point) {
            reactors.add(point);
        }
        player.setPosition(service.getPosition());
        points.get(start).getStuff().remove(player);
        points.get(service.getPosition()).getStuff().add(player);
        return reactors;
    }

    @Override
    public void cleanBarrier(Point point) {
        int index = 0;
        while (index < point.getItems().size()) {
            ItemType type = point.getItems().get(index);
            if (type == ItemType.StopCard || type == ItemType.Barrier) {
                point.getItems().remove(index);
                continue;
            }
            index++;
        }
    }

    @Override
    public boolean isStop(Point point) {
        return point.getItems().stream().filter(e->(e == ItemType.StopCard || e == ItemType.Barrier)).count() > 0;
    }

    @Override
    public void removePlayer(Player player) {
        houseBiz.refresh(player);
        int position = player.getPosition();
        Point point = Map.getUniqueInstance().getPoints().get(position);
        point.getStuff().remove(player);
        Logic.getUniqueInstance().getPlayers().remove(player);
        Stage.getUniqueInstance().refreshPoint(position);
    }
    public void react_GUI(Player player, int start, int end, ArrayList<JLabel> points) throws InterruptedException {
        int index = start;
        int size = Map.getUniqueInstance().getSize();
        int march = player.isDirection() ? 1 : (size - 1);
        ArrayList<Point> points1 = Map.getUniqueInstance().getPoints();
        Stuff service;
        do {
            if (index == start && points1.get(index).getItems().stream().filter(e->(e == ItemType.StopCard)).count() > 0) {
                JOptionPane.showMessageDialog(null, "Oops! You must stay here!");
                service = points1.get(index).getStuff().get(0);
                service.service(Stage.getUniqueInstance().getMapDisplay(), player);
                cleanBarrier(points1.get(index));
                break;
            }
            points1.get(index).getStuff().remove(player);
            int last = points1.get(index).getStuff().size() - 1;
            points.get(index).setIcon(Utility.getImageIcon(points1.get(index).getStuff().get(last).getImage(), points.get(index).getWidth(), points.get(index).getHeight()));
            index = (index + march) % size;
            player.setPosition(index);
            points1.get(index).getStuff().add(player);
            points.get(index).setIcon(Utility.getImageIcon(player.getImage(), points.get(index).getWidth(), points.get(index).getHeight()));
            Point point = points1.get(index);
            service = point.getStuff().get(0);
            if(index != start && index!= end && !isStop(point) && service.getType() == PointType.Bank) {
                PlayerStep.getInstance().setCondition(false);
                service.service(Stage.getUniqueInstance().getMapDisplay(), player);
                synchronized (PlayerStep.getInstance()) {
                    while (!PlayerStep.getInstance().isCondition()) {
                        PlayerStep.getInstance().wait();
                    }
                }
                continue;
            }
            if (point.getItems().stream().filter(e->(e == ItemType.Barrier)).count() > 0) {
                JOptionPane.showMessageDialog(null, "Oops! You must stay here because of barrier!");
                service.service(Stage.getUniqueInstance().getMapDisplay(), player);
                cleanBarrier(point);
                break;
            }
            if(index == end) {
                service.service(Stage.getUniqueInstance().getMapDisplay(), player);
                break;
            }
            Thread.sleep(400);
        }while (index != end);
    }
}

