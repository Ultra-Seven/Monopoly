package Util;

/**
 * Created by lenovo on 2016/4/3.
 * The single pattern of Dice in logic
 */
public class Dice {
    private static Dice uniqueInstance;
    //the number of the dice
    private int dice = 0;
    //whether to use control dice card
    private boolean isControlled = false;
    private Dice() {
    }
    public static Dice getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Dice();
        }
        return uniqueInstance;
    }
    public int getDice() {
        if (!isControlled)
            setDice();
        isControlled = false;
        return this.dice;
    }
    private void setDice() {
        int randomNumber = (int)(Math.random() * 6 + 1);
        dice = randomNumber;
    }
    public void setDiceByControl(int dice) {
        this.dice = dice;
    }

    public boolean isControlled() {
        return isControlled;
    }

    public void setControlled(boolean controlled) {
        isControlled = controlled;
    }
}
