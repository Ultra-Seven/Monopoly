package SuperEntity;

import ActionImpl.HelloActionImpl;
import ActionImpl.ServiceActionImpl;
import Entity.Player;
import Util.PointType;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/3.
 */
public abstract class Stuff {
    //name
    private String name = "";
    //symbol
    private String symbol = "";
    //type
    private PointType type;
    //position in array list
    private int position;
    //Icon
    private ImageIcon image;
    //service function
    public abstract void service(Player player);
    public abstract void service(JPanel panel, Player player);
    //print function
    public abstract void printInformation();
    protected HelloActionImpl helloAction = new HelloActionImpl();
    protected ServiceActionImpl serviceAction = new ServiceActionImpl();
    public String getName() {
        return name;
    }

    public Stuff setName(String name) {
        this.name = name;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Stuff setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public PointType getType() {
        return type;
    }

    public void setType(PointType type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
    //get the information of the point
    public abstract String getInformation();
}
