package Entity;

import SuperEntity.Stuff;
import Util.ItemType;


import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/3.
 * point in the map
 */
public class Point {
    //coordination
    private int index_x;
    private int index_y;
    //stuff in the point
    private ArrayList<Stuff> stuff = new ArrayList<Stuff>();
    //stop or barrier
    private ArrayList<ItemType> items = new ArrayList<ItemType>();
    public Point() {

    }

    public int getIndex_x() {
        return index_x;
    }

    public void setIndex_x(int index_x) {
        this.index_x = index_x;
    }

    public int getIndex_y() {
        return index_y;
    }

    public void setIndex_y(int index_y) {
        this.index_y = index_y;
    }

    public ArrayList<Stuff> getStuff() {
        return stuff;
    }

    public ArrayList<ItemType> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemType> items) {
        this.items = items;
    }
}
