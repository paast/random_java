package console_astar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {

    private PriorityQueue<Node> fringe;
    private HashSet<Node> seen;

    private Map map;
    public boolean done;

    public AStar(Map map) {
        this.map = map;
        done = false;

        fringe = new PriorityQueue<>();
        fringe.add(map.getStartNode());

        seen = new HashSet<>();
    }

    public void update() {
        if (fringe.size() > 0) {
            Node cur = fringe.poll();
            map.reDraw(cur.getX(), cur.getY(), '.');
            seen.add(cur);
            if (cur == map.getEndNode()) {
                done = true;
                return;
            }
            ArrayList<Node> adjNodes = map.getAdj(cur.getX(), cur.getY());
            for (Node node : adjNodes) {
                if (!seen.contains(node)) {
                    node.setDistTraveled(cur.getDistTraveled() + 1);
                    fringe.add(node);
                    map.reDraw(node.getX(), node.getY(), '*');
                }
            }
        } else {
            done = true;
            return;
        }
    }

    public String render() {
        return map.render();
    }

    public void dump() {
        System.out.print("aStar fringe dump:\n");
        for (Node node: fringe) {
            System.out.print("\t- (" + node.getX() + ", " + node.getY() + ") :: (" +
                    node.getDistTraveled() + ", " + node.getDistRemaining() + ", " + node.getDist() + ")\n");
        }
    }

}
