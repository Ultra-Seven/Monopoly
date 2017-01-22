package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by lenovo on 2016/6/2.
 * The first panel of the game
 * just a preface of the game
 */
public class Preface extends JFrame {
    PrefaceStage panel = new PrefaceStage();
    public Preface() {
        originSetting();
        add(panel);
        panel.addTitle(this.getWidth(), this.getHeight());
        panel.addMenu(this.getWidth());
        panel.getMenu().getStart().addActionListener(e -> {
            panel.getMenu().getPositionController().setDown(false);
            Thread t = new Thread(panel.getMenu().getPositionController());
            t.start();
            new Thread(new SetVisibleFalse()).start();
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(panel.getTitle().getLoadInController()).start();
                new Thread(panel.getMenu().getPositionController()).start();
            }
        });

    }
    public void originSetting () {
        setLayout(null);
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Monopoly");
        setResizable(false);
    }

    class SetVisibleFalse implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Setting setting = new Setting();
            setVisible(false);
            setting.setVisible(true);
        }
    }
}

class PrefaceStage extends JPanel {
    private Image background;
    private Title title;
    private Menu menu ;
    public PrefaceStage() {
        setLayout(null);
        setSize(1200, 700);
        title = new Title();
        menu = new Menu();
        background = new ImageIcon("pics/background.png").getImage();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    public Title getTitle() {
        return title;
    }

    public Menu getMenu() {
        return menu;
    }

    public void addTitle(int width, int height) {
        int x = (width - title.getWidth()) / 8;
        int y = height / 9;
        this.add(title);
        title.setBounds(x, y, title.getWidth(), title.getHeight());
    }
    public void addMenu(int width) {
        int x = width - menu.getWidth();
        this.add(menu);
        menu.setBounds(x, 0, menu.getWidth(), menu.getHeight());
    }
}
class Title extends JPanel {
    private LoadInController loadInController;
    private Image title;
    private float alpha = 0.0f;
    Title() {
        setSize(300, 60);
        title = new ImageIcon("pics/monopoly.png").getImage();
        loadInController = new LoadInController();
        setOpaque(false);
    }
    class LoadInController implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(500);
                while (true) {
                    for (int i = 0; i < 50; i++) {
                        alpha = (float)i / 50;
                        Thread.sleep(20);
                        repaint();
                    }
                    break;
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(composite);
        g2d.drawImage(title, 0, 0, this.getWidth(), this.getHeight(),this);
    }
    public LoadInController getLoadInController() {
        return loadInController;
    }
}
class Menu extends JPanel implements IMenuPosition{
    private JButton start = new JButton();
    private JButton end = new JButton();
    private Image menu;
    private int height;
    private PositionController positionController;
    Menu() {
        setLayout(null);
        setSize(300, 600);
        positionController = new PositionController(this.getHeight(), this);
        this.height = this.getHeight() * (-1);
        start.setIcon(new ImageIcon("pics/st.png"));
        end.setIcon(new ImageIcon("pics/exit.png"));
        menu = new ImageIcon("pics/menu.png").getImage();
        setOpaque(false);
        end.addActionListener(e -> System.exit(0));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(menu, 0, height, this.getWidth(), this.getHeight(), this);
    }

    public void setButtonOut() {
        start.setVisible(false);
        end.setVisible(false);
    }

    @Override
    public void repainting(int height) {
        this.height = height;
        repaint();
    }

    public void addButton() {
        add(start);
        add(end);
        start.setOpaque(false);
        start.setBorder(null);
        end.setOpaque(false);
        end.setBorder(null);
        start.setBounds(90, 300, 146,35);
        end.setBounds(90, 450, 146, 35);
    }
    public PositionController getPositionController() {
        return positionController;
    }

    public JButton getStart() {
        return start;
    }
}