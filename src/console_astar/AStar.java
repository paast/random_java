package console_astar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {

    private PriorityQueue<Node> fringe;
    private HashSet<Node> seen;

    private int pathLength;
    private int nodesChecked;
    private int iterations;

    private Map map;
    public boolean done;

    public AStar(Map map) {
        this.map = map;
        done = false;

        fringe = new PriorityQueue<>();
        fringe.add(map.getStartNode());

        seen = new HashSet<>();
        seen.add(map.getStartNode());
        pathLength = 0;
        nodesChecked = 1;
        iterations = 0;
    }

    /**
     * Performs all actions that accompany adding a node
     * to the fringe.
     *
     * @param node
     * @param prev
     */
    public void addToFringe(Node node, Node prev) {
        node.setDistTraveled(prev.getDistTraveled() + 1);
        node.setPrev(prev);
        fringe.add(node);
        map.reDraw(node.getX(), node.getY(), '*');
        //System.out.println("Node added to fringe: " + node.getDistTraveled());
    }

    /**
     * Technically not an accurate method name, as the removal is done
     * 8at the poll() call - however this performs all related work that
     * should be done when a node is polled from the fringe.
     *
     *@param node
     */
    public void removeFromFringe(Node node) {
        map.reDraw(node.getX(), node.getY(), '.');
        //seen.add(node);
        //System.out.println("Node removed from fringe: " + node.getDist() + " || " + node.getDistTraveled());
    }

    public void update() {
        iterations++;
        if (fringe.size() > 0) {
            Node cur = fringe.poll();
            System.out.println("(" + cur.getX() + ", " + cur.getY() + ")");
            removeFromFringe(cur);
            if (cur == map.getEndNode()) {
                done = true;
                rewind(cur);
                return;
            }
            ArrayList<Node> adjNodes = map.getAdj(cur.getX(), cur.getY());
            for (Node node : adjNodes) {
                if (!seen.contains(node)) {
                    addToFringe(node,cur);
                    seen.add(node);
                    nodesChecked++;
                } else if (node.getDistTraveled() < cur.getPrev().getDistTraveled()) {
                    cur.setPrev(node);
                }
            }
        } else {
            done = true;
            return;
        }
    }

    private void rewind(Node node) {
        map.reDraw(node.getX(), node.getY(), 'o');
        pathLength += 1;
        while ((node = node.getPrev()) != null) {
            map.reDraw(node.getX(), node.getY(), 'o');
            pathLength += 1;
        }
    }

    public void dump() {
        System.out.print("aStar fringe dump:\n");
        for (Node node: fringe) {
            System.out.print("\t- (" + node.getX() + ", " + node.getY() + ") :: (" +
                    node.getDistTraveled() + ", " + node.getDistRemaining() + ", " + node.getDist() + ")\n");
        }
    }

    public String stats() {
        return "Nodes Checked: " + nodesChecked +
                "\nPath Length: " + pathLength +
                "\nIterations: " + iterations;
    }

}
