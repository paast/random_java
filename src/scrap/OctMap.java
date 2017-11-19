package scrap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OctMap {

    private int width;
    private int height;
    private Node[][] map;

    private Node startNode;
    private Node endNode;

    public OctMap(String path) {
        loadMap(path);
    }

    private void loadMap(String path) {

        ArrayList<String> temp = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                temp.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Failed to load: " + path);
            e.printStackTrace();
        }

        height = temp.size();
        width = temp.get(0).length();
        map = new Node[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char ch = temp.get(y).charAt(x);
                switch (ch) {
                    /*case ('S'): startPoint = new scrap.Point(x, y);
                        break;
                    case ('E'): endPoint = new scrap.Point(x, y);
                        break;
                    case ('#'): map[(y * width) + x] = true;
                        break;
                    default: map[(y * width) + x] = false;
                        break;*/
                }
            }
        }
    }

    public void printMapDetails() {
        System.out.println("MAP DETAILS:");
        System.out.println("------------");
        System.out.println("Width: " + width + " || Height: " + height);
        System.out.println("Start: (" + startNode.getX() + ", " + startNode.getY() + ")");
        System.out.println("End: (" + endNode.getX() + ", " + endNode.getY() + ")");
    }
}
