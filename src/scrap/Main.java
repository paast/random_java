package scrap;

public class Main {

    private static final String MAP_URL = "rsc/map_1.oct";

    public static void main(String[] args) {

        // read file in
        OctMap map = new OctMap(MAP_URL);
        map.printMapDetails();
    }
}
