package Util;

import Entity.Items.*;
import Entity.Player;
import SuperEntity.Item;

import javax.swing.*;

/**
 * Created by lenovo on 2016/4/7.
 */
public enum ItemType {
    AverageCard,Barrier, ControlDice, RobCard, SteeringCard, StopCard, TaxCard;
    public static Item getItem(ItemType type) {
        Item item = null;
        switch (type) {
            case AverageCard:{
                item = new AverageCard();
                break;
            }
            case Barrier:{
                item = new AverageCard();
                break;
            }
            case ControlDice:{
                item = new ControlDice();
                break;
            }
            case RobCard:{
                item = new RobCard();
                break;
            }
            case SteeringCard:{
                item = new SteeringCard();
                break;
            }
            case StopCard:{
                item = new StopCard();
                break;
            }
            case TaxCard:{
                item = new TaxCard();
                break;
            }
        }
        return item;
    }
    public static ImageIcon getImage(ItemType type) {
        ImageIcon imageIcon = null;
        switch (type) {
            case AverageCard:{
                imageIcon = new ImageIcon("pics/tool1.png");
                break;
            }
            case Barrier:{
                imageIcon = new ImageIcon("pics/tool2.png");
                break;
            }
            case ControlDice:{
                imageIcon = new ImageIcon("pics/tool3.png");
                break;
            }
            case RobCard:{
                imageIcon = new ImageIcon("pics/tool4.png");
                break;
            }
            case SteeringCard:{
                imageIcon = new ImageIcon("pics/tool5.png");
                break;
            }
            case StopCard:{
                imageIcon = new ImageIcon("pics/tool6.png");
                break;
            }
            case TaxCard:{
                imageIcon = new ImageIcon("pics/tool7.png");
                break;
            }
        }
        return imageIcon;
    }
}
