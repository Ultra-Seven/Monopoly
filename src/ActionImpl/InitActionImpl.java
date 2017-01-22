package ActionImpl;

import Action.IInitAction;
import BizImpl.InitBizImpl;
import Util.Logic;
import Util.Utility;

/**
 * Created by lenovo on 2016/4/29.
 */
public class InitActionImpl implements IInitAction {
    InitBizImpl initBiz = new InitBizImpl();
    @Override
    public void welcome() {
        System.out.println("Welcome to the world of Monopoly!");
    }

    @Override
    public void setPlayer() {
        while (true) {
            System.out.println("Please input the number of the players participating in the world:");
            String input = Utility.input();
            if (Utility.validate(input, 4, 2)) {
                initBiz.getPlayer(Integer.parseInt(input));
                break;
            } else {
                System.out.println("Please input a proper number!(2-4)");
            }
        }
    }

    @Override
    public void inputName() {
        int size = Logic.getUniqueInstance().getPlayers().size();
        for (int i = 0; i < size; i++) {
            while (true) {
                System.out.println("Please input the name of Player " + (i+1) + ":");
                String name = Utility.input();
                if (!name.equals("")) {
                    initBiz.inputName(i, name);
                    break;
                }
                else
                    System.out.println("Please input a name!");
            }
        }
    }

    @Override
    public void setTime() {
        while (true) {
            System.out.println("Please choose a time boundary:\n" + "1. a month\t" + "2. half year\t" + "3. a year");
            String time = Utility.input();
            if (time.equals("1")) {
                initBiz.setTime(31);
                break;
            }
            else if (time.equals("2")) {
                initBiz.setTime(182);
                break;
            }
            else if (time.equals("3")) {
                initBiz.setTime(365);
                break;
            }
            else {
                System.out.println("Please input a proper choice!");
            }
        }
    }

    @Override
    public void setPlayerAssert() {
        while (true) {
            System.out.println("Please choose a initial cash:\n" + "1. 5000\t" + "2. 10000\t" + "3. 50000");
            String choice = Utility.input();
            if (choice.equals("1")) {
                initBiz.setPlayerAssert(5000);
                break;
            }
            else if (choice.equals("2")) {
                initBiz.setPlayerAssert(10000);
                break;
            }
            else if (choice.equals("3")) {
                initBiz.setPlayerAssert(50000);
                break;
            }
            else {
                System.out.println("Please input a proper choice!");
            }
        }
    }
}
