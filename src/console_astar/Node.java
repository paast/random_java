package console_astar;

public class Node implements Comparable<Node>{

    private int x, y, distTraveled, distRemaining;

    private Node prev;

    public Node (int x, int y) {
        this.x = x;
        this.y = y;
        prev = null;
    }

    public Node(int x, int y, Node prev) {
        this.x = x;
        this.y = y;
        distTraveled = 0;
    }

    public Node getPrev() { return prev; }

    public void setPrev(Node prev) { this.prev = prev; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDist() {
        return distRemaining + distTraveled;
    }

    public int getDistRemaining() {
        return distRemaining;
    }

    public void setDistRemaining(int dist) {
        distRemaining = dist;
    }

    public int getDistTraveled() {
        return distTraveled;
    }

    public void setDistTraveled(int dist) {
        distTraveled = dist;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.getDist(), o.getDist());
    }
}
