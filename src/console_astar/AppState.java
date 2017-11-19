package console_astar;

import java.util.Scanner;
import java.lang.System;

public class AppState {

    private Map map;
    private float framerate;

    public AppState() {

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        // user input & load resources
        System.out.print("Welcome to the A* Inn, please enjoy your stay :).\n");
        System.out.print("[enter name of map to load]: ");
        String mapName = scanner.nextLine();

        map = new Map();
        while (!map.loadMap(mapName)) {
            System.out.print("Invalid file name, please try again " +
                    "(must exactly match file name: ");
            mapName = scanner.nextLine();
        }

        System.out.printf("\nMap '%s' successfully loaded: \n", mapName);
        map.printStats(1);

        System.out.print("\nEnter desired framerate (seconds/frame [0.5 = 2 fps]): ");
        while (!scanner.hasNextFloat()) {
            scanner.next();
            System.out.print("Invalid framerate (try some digits, yo): ");
        }
        framerate = scanner.nextFloat(); // nextFloat() takes float, leaves rest of line (including \n char).
        scanner.nextLine(); // So we add this to clear the scanner.

        System.out.print("\nFramerate: " + (1 / framerate) + " fps.");
        System.out.print("\n\nHit enter when ready.");
        scanner.nextLine();
        scanner.close();

        AStar aStar = new AStar(map);
        long lastTime = System.currentTimeMillis();
        long thisTime = 0;

        while(!aStar.done) {

            thisTime = System.currentTimeMillis();
            while (thisTime - lastTime < (framerate * 1000)) {
                thisTime = System.currentTimeMillis();
            }
            lastTime = thisTime;

            aStar.update();

            System.out.println(Utils.clear() + map.render());
        }
        map.reDrawSE();
        System.out.println(Utils.clear() + map.render());
        System.out.println(aStar.stats());
    }





}
