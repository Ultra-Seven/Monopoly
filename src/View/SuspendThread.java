package View;

/**
 * Created by lenovo on 2016/6/10.
 * Thread handling the animation of the item panel
 */
public class SuspendThread extends Thread {
    private volatile boolean exit = true;
    private volatile boolean down = true;
    private ItemShopPanel itemShopPanel;
    private static SuspendThread uniqueInstance;
    private SuspendThread() {}
    public void run() {
        exit = false;
        int granular = 5;
        int originHeight = itemShopPanel.getHeight();
        int y = itemShopPanel.getY();
        int downMask = down ? 1 : -1;
        int times = 0;
        if (down)
            times = Math.abs(y) / granular;
        else
            times = (originHeight - Math.abs(y)) / granular;
        for (int i = 0; i < times; i++) {
            if(exit)
                break;
            y = y + granular * downMask;
            int sleep = (int) (10 * ((double)i / times));
            itemShopPanel.setBounds(itemShopPanel.getX(), y, itemShopPanel.getWidth(), itemShopPanel.getHeight());
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exit = true;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public ItemShopPanel getItemShopPanel() {
        return itemShopPanel;
    }

    public void setItemShopPanel(ItemShopPanel itemShopPanel) {
        this.itemShopPanel = itemShopPanel;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public static SuspendThread getUniqueInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new SuspendThread();
        }
        return uniqueInstance;
    }
}
