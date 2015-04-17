package com.codingame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Player {

    private List<Bike> bikes = new ArrayList<>();
    private int numberPlayers;
    private int myNumberPlayer;
    private int[][] map = new int[20][30];

    private static String LEFT = "LEFT";
    private static String RIGHT = "RIGHT";
    private static String DOWN = "DOWN";
    private static String UP = "UP";

    private static String last = "";
    private static int forceRandom = 0;

    public static void main(String args[]) {
        Player player = new Player();
        player.run();
    }

    private void run() {
        Scanner in = new Scanner(System.in);

        while (true) {
            bikes.clear();
            initialise(in);
            updateBikePositions(map, bikes);
            String result = process(map, bikes, myNumberPlayer);
            System.out.println(result);
        }
    }

    protected void updateBikePositions(int[][] map, List<Bike> bikes) {
        for (Bike bike : bikes) {
            if (bike.getX() != -1) {
                // position init
                map[bike.getInitX()][bike.getInitY()] = bike.getBikeNumber();
                // current position
                map[bike.getX()][bike.getY()] = bike.getBikeNumber();
            } else {
                for (int j = 0; j < 30; j++) {
                    for (int i = 0; i < 20; i++) {
                        if (map[i][j] == bike.getBikeNumber()) {
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
    }

    protected String process(int[][] map, List<Bike> bikes, int myNumberPlayer) {

        for (int j = 0; j < 30; j++) {
            for (int i = 0; i < 20; i++) {
                System.err.print(map[i][j]);
            }
            System.err.println("");
        }

        Bike myBike = bikes.get(myNumberPlayer);
        int x = myBike.getX();
        int y = myBike.getY();

        boolean isLeftFree = false;
        boolean isRightFree = false;
        boolean isUpFree = false;
        boolean isDownFree = false;

        if (x - 1 >= 0) {
            isUpFree = map[x - 1][y] == 0;
        }
        if (x + 1 < 20) {
            isDownFree = map[x + 1][y] == 0;
        }
        if (y - 1 >= 0) {
            isLeftFree = map[x][y - 1] == 0;
        }
        if (y + 1 < 30) {
            isRightFree = map[x][y + 1] == 0;
        }

        List<String> possible = new ArrayList<>();
        if (isRightFree) {
            possible.add(RIGHT);
        }
        if (isLeftFree) {
            possible.add(LEFT);
        }
        if (isDownFree) {
            possible.add(DOWN);
        }
        if (isUpFree){
            possible.add(UP);
        }

        String result;
        forceRandom++;

        Random rand = new Random();
        int magic = rand.nextInt(7) + 7;

        if (forceRandom % magic == 0) {
            int sizePossible = possible.size();
            Random random = new Random();
            result = possible.get(random.nextInt(sizePossible));
        }
        else if (possible.contains(last)) {
            result = last;
        } else {
            int sizePossible = possible.size();
            Random random = new Random();
            result = possible.get(random.nextInt(sizePossible));
        }

        System.err.println("my position x : " + x + " y : " + y);
        System.err.println("right  x : " + x + " y : " + (y+1));
        System.err.println("left   x : " + x + " y : " + (y - 1));
        System.err.println("up     x : " + (x-1) + " y : " + y);
        System.err.println("down   x : " + (x+1) + " y : " + y);
        System.err.println(isLeftFree + " " + isRightFree + " " + isDownFree + " " + isUpFree);

        last = result;
        return result;
    }

    private void initialise(Scanner in) {
        numberPlayers = in.nextInt(); // total number of players (2 to 4).
        myNumberPlayer = in.nextInt(); // your player number (0 to 3).
        for (int i = 0; i < numberPlayers; i++) {
            int initY = in.nextInt(); // starting X coordinate of lightcycle (or -1)
            int initX = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
            int y = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
            int x = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)
            Bike bike = new Bike(i + 1, initX, initY, x, y);
            bikes.add(bike);
        }
    }

}

class Bike {
    private int bikeNumber;
    private int initX;
    private int initY;
    private int x;
    private int y;

    public Bike(int i, int initX, int initY, int x, int y) {
        this.bikeNumber = i;
        this.initX = initX;
        this.initY = initY;
        this.x = x;
        this.y = y;
    }

    public int getBikeNumber() {
        return bikeNumber;
    }

    public int getInitX() {
        return initX;
    }

    public void setBikeNumber(int bikeNumber) {
        this.bikeNumber = bikeNumber;
    }

    public void setInitX(int initX) {
        this.initX = initX;
    }

    public int getInitY() {
        return initY;
    }

    public void setInitY(int initY) {
        this.initY = initY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}