package Util;

import Entity.Point;
import Entity.Service.*;
import SuperEntity.Stuff;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/4/4.
 * Read data from the data.txt:
 * 0:House() 1:item shop 2:bank 3:news 4:item spot 5:ticket spot 6:vacancy
 * template: [row] [column] type name symbol attributes
 */
public class Map {
    //the points of map
    private ArrayList<Point> points = new ArrayList<Point>();
    //the information of street
    private ArrayList<ArrayList<House>> streets = new ArrayList<ArrayList<House>>();
    private static Map uniqueInstance;
    /*
    the information of map size
     */
    private int width;
    private int height;
    private int size;
    private Map() {
        try {
            readDataFromTxt("data/points");
            size = points.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Map getUniqueInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Map();
        }
        return uniqueInstance;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }

    private Stuff newStuff(String string) {
        switch (string) {
            case "0": return new House();
            case "1": return new ItemShop();
            case "2": return new Bank();
            case "3": return new News();
            case "4": return new ItemSpot();
            case "5": return new TicketSpot();
            case "7": return new Lottery();
            case "8": return new Hospital();
           default:return new Vacancy();
        }
    }
    //read data from file and init the map
    private void readDataFromTxt(String filePath) throws IOException {
        String encoding="UTF-8";
        File file=new File(filePath);
        if(file.isFile() && file.exists()){ //判断文件是否存在
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            int row = Integer.parseInt(bufferedReader.readLine());
            int col = Integer.parseInt(bufferedReader.readLine());
            height = row;
            width = col;
            String lineTxt = bufferedReader.readLine();
            int number = Integer.parseInt(lineTxt);
            for (int i = 0; i < number; i++) {
                lineTxt = bufferedReader.readLine();
                handleStr(lineTxt, i);
            }
            read.close();
        }else{
            System.out.println("No such a file");
        }
    }
    private void handleStr(String str, int position) {
        Point point = new Point();
        String[] strSplit = str.split(" ");
        int x = Integer.parseInt(strSplit[0]);
        int y = Integer.parseInt(strSplit[1]);
        point.setIndex_x(x);
        point.setIndex_y(y);
        String type = strSplit[2];
        String name = strSplit[3];
        String sym = strSplit[4];
        Stuff service = newStuff(type).setName(name).setSymbol(sym);
        if(service instanceof House) {
            ((House) service).setOriginalCost(Integer.parseInt(strSplit[5]));
            ((House) service).setCost(Integer.parseInt(strSplit[5]));
            ((House) service).setProfit(Integer.parseInt(strSplit[6]));
            if (Integer.parseInt(strSplit[7]) == 1) {
                addStreet((House) service);
            }
        }
        service.setImage(PointType.getImage(service.getType()));
        service.setPosition(position);
        point.getStuff().add(service);
        points.add(point);

    }
    public Point getPoint(int x, int y) {
        return points.stream().filter(item->(item.getIndex_x()==x&&item.getIndex_y()==y)).findFirst().orElse(null);
    }
    private void addStreet(House house) {
        if(streets.size() > 0 && points.size() > 0) {
            Stuff stuff = points.get(points.size() - 1).getStuff().get(0);
            ArrayList<House> street = streets.get(streets.size() - 1);
            House preHouse = street.get(street.size() - 1);
            if (stuff instanceof House && stuff == preHouse) {
                street.add(house);
            } else {
                ArrayList<House> newStreet = new ArrayList<>();
                newStreet.add(house);
                streets.add(newStreet);
            }
        }
        else {
            ArrayList<House> newStreet = new ArrayList<>();
            newStreet.add(house);
            streets.add(newStreet);
        }
    }
    public ArrayList<ArrayList<House>> getStreets() {
        return streets;
    }
    public String getStreetName() {
        int index = 0;
        for (int i = 0; i < streets.size(); i++) {
            for (int j = 0; j < streets.get(i).size(); j++) {
                if (streets.get(i).get(j).getPosition() >= streets.get(i).get(0).getPosition() && streets.get(i).get(j).getPosition() <= streets.get(i).get(streets.get(i).size()-1).getPosition()) {
                    index = i;
                    break;
                }
            }
        }
        return StreetName.values()[index].toString();
    }
    public int getX(int index) {
        Point point = points.get(index);
        return point.getIndex_x() * 40 + point.getIndex_x() * 8;
    }
    public int getY(int index) {
        Point point = points.get(index);
        return point.getIndex_y() * 40 + point.getIndex_y() * 11;
    }
}
