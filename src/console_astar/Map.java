package console_astar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private int width, height;

    private char[][] drawMap;
    private Node[][] map;
    private Node startNode, endNode;

    public Map() {
    }

    public boolean loadMap(String path) {
        ArrayList<String> temp = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("rsc/" + path));
            String line = reader.readLine();

            while (line != null) {
                temp.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            return false;
        }

        height = temp.size();
        width = temp.get(0).length();
        map = new Node[width][height];
        drawMap = new char[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char ch = temp.get(y).charAt(x);
                switch (ch) {
                    case ('#'):
                        drawMap[x][y] = '#';
                        break;
                    case ('S'):
                        drawMap[x][y] = 'S';
                        startNode = new Node(x, y);
                        map[x][y] = startNode;
                        break;
                    case ('E'):
                        drawMap[x][y] = 'E';
                        endNode = new Node(x, y);
                        map[x][y] = endNode;
                        break;
                    default:
                        drawMap[x][y] = ' ';
                        map[x][y] = new Node(x, y);
                        break;
                }
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map[x][y] != null) {
                    map[x][y].setDistRemaining(Math.abs(endNode.getX() - x) + Math.abs(endNode.getY() - y));
                }
            }
        }
        return true;
    }

    public void reDraw(int x, int y, char ch) {
        drawMap[x][y] = ch;
    }

    public Node get(int x, int y) {
        if (x < 0 || x > (width - 1) || y < 0 || y > (height - 1)) { return null; }
        return map[x][y];
    }

    public ArrayList<Node> getAdj(int x, int y) {
        ArrayList<Node> nodes = new ArrayList<>();

        Node adj;

        adj = this.get(x - 1, y);
        if (adj != null) { nodes.add(adj); }
        adj = this.get(x + 1, y);
        if (adj != null) { nodes.add(adj); }
        adj = this.get(x, y + 1);
        if (adj != null) { nodes.add(adj); }
        adj = this.get(x, y - 1);
        if (adj != null) { nodes.add(adj); }

        return nodes;
    }

    public void printStats(int tabs) {
        String tabBlock = new String(new char[tabs]).replace("\0", "\t");
        System.out.println(tabBlock + "- Map Width: " + width);
        System.out.println(tabBlock + "- Map Height: " + height);
        System.out.println(tabBlock + "- Start Position: (" + startNode.getX() + ", " + startNode.getY() + ")");
        System.out.println(tabBlock + "- End Position: (" + endNode.getX() + ", " + endNode.getY() + ")");
    }

    public String render() {

        String print = new String();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                print += drawMap[x][y];
            }
            print += "\n";
        }
        return print;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

}
