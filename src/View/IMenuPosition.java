package View;

/**
 * Created by lenovo on 2016/6/4.
 * The thread for suspending and lifting the panel
 */
public interface IMenuPosition {
    void addButton();
    void setButtonOut();
    void repainting(int height);
}
class PositionController implements Runnable {
    private boolean down = true;
    private int originHeight;
    private int height;
    private IMenuPosition iMenuPosition;
    public PositionController(int originHeight, IMenuPosition iMenuPosition) {
        this.originHeight = originHeight;
        this.height = originHeight * (-1);
        this.iMenuPosition = iMenuPosition;
    }
    @Override
    public void run() {
        if (!down)
            iMenuPosition.setButtonOut();
        try {
            Thread.sleep(200);
            int granular = 5;
            int times = originHeight / granular;
            int downMask = down ? 1 : -1;
            while (true) {
                for (int i = 0; i < times; i++) {
                    height = height + granular * downMask;
                    int sleep = (int) (10 * ((double)i / times));
                    Thread.sleep(sleep);
                    iMenuPosition.repainting(height);
                }
                break;
            }
            Thread.sleep(100);
            if (down)
                iMenuPosition.addButton();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}