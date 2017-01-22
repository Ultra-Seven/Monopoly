package View;

import BizImpl.InitBizImpl;
import Util.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by lenovo on 2016/6/4.
 * The setting panel help player complete their information
 */
public class Setting extends JFrame{
    private Background back = new Background();
    public Setting() {
        setLayout(null);
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Monopoly");
        add(back);
        back.addMenu(this.getWidth());
        back.addLeft(this.getHeight());
        back.getMenu().getStart().addActionListener(e -> {
            ArrayList<String> strings = back.getLeft().hasNoEmptyName();
            if(strings != null && strings.size() > 1) {
                if (!hasSameName(strings)) {
                    back.getLeft().initializer();
                    back.getMenu().getPositionController().setDown(false);
                    Thread t = new Thread(back.getMenu().getPositionController());
                    t.start();
                    SetVisibleFalse set = new SetVisibleFalse(Stage.getUniqueInstance());
                    new Thread(set).start();
                }
                else
                    JOptionPane.showMessageDialog(null, "No same names!");
            }
            else
                JOptionPane.showMessageDialog(null, "please finish your information!");
        });
        back.getMenu().getRet().addActionListener(e -> {
            back.getMenu().getPositionController().setDown(false);
            Thread t = new Thread(back.getMenu().getPositionController());
            t.start();
            SetVisibleFalse set = new SetVisibleFalse(new Preface());
            new Thread(set).start();
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                new Thread(back.getMenu().getPositionController()).start();
            }
        });
    }
    class SetVisibleFalse implements Runnable {
        private JFrame newFrame;
        SetVisibleFalse(JFrame frame) {
            newFrame = frame;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setVisible(false);
            newFrame.setVisible(true);
        }
    }
    private boolean hasSameName(ArrayList<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            for (int j = i + 1; j < strings.size(); j++) {
                if (strings.get(i).equals(strings.get(j)))
                    return true;
            }
        }
        return false;
    }
}
//jpanel used as a background
class Background extends JPanel {
    Image background = new ImageIcon("pics/background.jpg").getImage();
    private Menu_Setting menu = new Menu_Setting();
    private LeftSetting left = new LeftSetting();
    Background() {
        setLayout(null);
        setSize(1200, 700);
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    public void addMenu(int width) {
        int x = width - menu.getWidth();
        this.add(menu);
        menu.setBounds(x, 0, menu.getWidth(), menu.getHeight());
    }
    public void addLeft(int height) {
        int y = (height - left.getHeight()) / 2;
        this.add(left);
        left.setBounds(0, y, left.getWidth(), left.getHeight());
    }
    public Menu_Setting getMenu() {
        return menu;
    }

    public LeftSetting getLeft() {
        return left;
    }
}
//left panel used as setting input
class LeftSetting extends JPanel {
    private Image back =new ImageIcon("pics/setting.png").getImage();
    private ButtonGroup time = new ButtonGroup();
    private ButtonGroup money = new ButtonGroup();
    private JPanel time_panel = new JPanel();
    private JPanel money_panel = new JPanel();
    private JPanel player_panel = new JPanel();
    private JPanel[] player = new JPanel[4];
    private String[] type = {"Optional", "Demon Hunter", "Malfurion Stormrage", "Dark Ranger", "Arthas", "Grom Hellscream"};
    private ImageIcon[] image = {new ImageIcon("pics/players0.jpg"), new ImageIcon("pics/players1.jpg"), new ImageIcon("pics/players2.jpg"), new ImageIcon("pics/players3.jpg"),
            new ImageIcon("pics/players4.jpg"), new ImageIcon("pics/players5.jpg")};
    private JLabel[] player_image = new JLabel[4];
    private JComboBox[] player_type = new JComboBox[4];
    private JTextField[] player_name = new JTextField[4];
    private Font font = new Font("Times New Roman", Font.BOLD, 18);
    public LeftSetting() {
        setLayout(null);
        setSize(800, 600);
        setOpaque(false);
        Utility.initComponent(this, time_panel,600, 50, 100, 50, Color.WHITE);
        Utility.initComponent(this, money_panel,600, 50, 100, 100, Color.WHITE);
        initComponent(money_panel, 600, 50, 100, 100);
        addTime();
        addMoney();
        initComponent(player_panel, 700, 350, 50, 200);
        addPlayer();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(back, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    private void addTime() {
        JLabel label = new JLabel("Time Boundary: ");
        Utility.initComponent(this, label, 200, 30, 100, 50, Color.WHITE);
        JRadioButton month = new JRadioButton("a month", true);
        JRadioButton halfY = new JRadioButton("half year");
        JRadioButton year = new JRadioButton("a year");
        time.add(month); Utility.initComponent(time_panel, month, 50, 30, 0, 0, Color.WHITE);
        time.add(halfY);Utility.initComponent(time_panel, halfY, 50, 30, 0, 0, Color.WHITE);
        time.add(year);Utility.initComponent(time_panel, year, 50, 30, 0, 0, Color.WHITE);
    }
    private void addMoney() {
        JLabel label = new JLabel("Money Boundary: ");
        Utility.initComponent(this, label, 200, 30, 100, 100, Color.WHITE);
        JRadioButton money1 = new JRadioButton("1000", true);
        JRadioButton money2 = new JRadioButton("5000");
        JRadioButton money3 = new JRadioButton("10000");
        money.add(money1);Utility.initComponent(money_panel, money1, 50, 30, 0, 0, Color.WHITE);
        money.add(money2);Utility.initComponent(money_panel, money2, 50, 30, 0, 0, Color.WHITE);
        money.add(money3);Utility.initComponent(money_panel, money3, 50, 30, 0, 0, Color.WHITE);
    }
    private void addPlayer() {
        player_panel.setLayout(new GridLayout(4, 1, 20, 10));
        player_panel.setOpaque(false);

        for (int i = 0; i < player.length; i++) {
            player[i] = new JPanel();player_image[i] = new JLabel();player_type[i] = new JComboBox(type);player_name[i] = new JTextField(10);
            player[i].setOpaque(false);
            player[i].setFont(font);
            player[i].setForeground(Color.white);
            player_image[i].setIcon(image[0]);
            player[i].add(player_image[i]);
            player[i].add(player_type[i]);
            player[i].add(player_name[i]);
            player_panel.add(player[i]);
            final int finalI = i;
            player_type[i].addActionListener(e -> {
                int index = player_type[finalI].getSelectedIndex();
                player_image[finalI].setIcon(image[index]);
            });
        }
    }
    public ArrayList<String> hasNoEmptyName() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < player_type.length; i++) {
            if(!player_type[i].getSelectedItem().equals("Optional")) {
                if (player_name[i].getText().equals(""))
                    return null;
                else
                    strings.add(player_name[i].getText());
            }
        }
        return strings;
    }
    public void initializer() {
        InitBizImpl initBiz = new InitBizImpl();
        initBiz.getPlayer(getNumber());
        ArrayList<String> names = hasNoEmptyName();
        ArrayList<ImageIcon> imageIcons = getImage();
        for (int i = 0; i < names.size(); i++) {
            initBiz.inputName(i, names.get(i));
            initBiz.setIcon(i, imageIcons.get(i));
        }
        initBiz.setPlayerAssert(getAsset());
        initBiz.setTime(getTime());
    }
    private void initComponent(JComponent component, int width, int height, int x, int y) {
        add(component);
        component.setSize(width, height);
        component.setBounds(x, y, component.getWidth(), component.getHeight());
        component.setOpaque(false);
    }
    private int getNumber() {
        int num = 0;
        for (JComboBox aPlayer_type : player_type) {
            if (!aPlayer_type.getSelectedItem().equals("Optional"))
                num++;
        }
        return num;
    }
    private int getAsset() {
        Enumeration<AbstractButton> radio = money.getElements();
        while (radio.hasMoreElements()) {
            AbstractButton button = radio.nextElement();
            if (button.isSelected())
                return Integer.parseInt(button.getText());
        }
        return 0;
    }
    private int getTime() {
        Enumeration<AbstractButton> radio = time.getElements();
        int time = 0;
        while (radio.hasMoreElements()) {
            AbstractButton button = radio.nextElement();
            if (button.isSelected()) {
                String text = button.getText();
                switch (text) {
                    case "a month":
                        time = 31;
                        break;
                    case "half year":
                        time = 183;
                        break;
                    default:
                        time = 365;
                        break;
                }
                break;
            }
        }
        return time;
    }
    private ArrayList<ImageIcon> getImage() {
        ArrayList<ImageIcon> imageIcons = new ArrayList<>();
        for (JComboBox aPlayer_type : player_type) {
            if (!aPlayer_type.getSelectedItem().equals("Optional")) {
                int index = aPlayer_type.getSelectedIndex();
                String path = "pics/player" + index + ".jpg";
                imageIcons.add(new ImageIcon(path));
            }
        }
        return imageIcons;
    }
}
class Menu_Setting extends JPanel implements IMenuPosition{
    private JButton start = new JButton();
    private JButton ret = new JButton();
    private Image menu = new ImageIcon("pics/menu.png").getImage();
    private int height;
    private PositionController positionController;
    public Menu_Setting() {
        setLayout(null);
        setSize(300, 600);
        positionController = new PositionController(this.getHeight(), this);
        this.height = this.getHeight() * (-1);
        start.setIcon(new ImageIcon("pics/confirm.png"));
        ret.setIcon(new ImageIcon("pics/return.png"));
        menu = new ImageIcon("pics/menu.png").getImage();
        setOpaque(false);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(menu, 0, height, this.getWidth(), this.getHeight(), this);
    }
    @Override
    public void addButton() {
        add(start);
        add(ret);
        start.setOpaque(false);
        start.setBorder(null);
        start.setBounds(90, 300, 146,35);
        ret.setBorder(null);
        ret.setOpaque(false);
        ret.setBounds(90, 450, 146, 35);
    }

    @Override
    public void setButtonOut() {
        start.setVisible(false);
        ret.setVisible(false);
    }

    @Override
    public void repainting(int height) {
        this.height = height;
        this.repaint();
    }

    public PositionController getPositionController() {
        return positionController;
    }

    public JButton getStart() {
        return start;
    }

    public JButton getRet() {
        return ret;
    }
}