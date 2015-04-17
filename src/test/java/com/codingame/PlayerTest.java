package com.codingame;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {
    @Test
    public void testUpdateBikePositions1_1() throws Exception {
        Player p = new Player();

        int[][] map = new int[3][3];
        List<Bike> bikes = new ArrayList<>();
        Bike b = new Bike(1, 1, 1, 1, 1);
        bikes.add(b);

        p.updateBikePositions(map, bikes);

        int[][] expected = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        Assertions.assertThat(map).isEqualTo(expected);
    }

    @Test
    public void testUpdateBikePositions1_2() throws Exception {
        Player p = new Player();

        int[][] map = new int[3][3];
        List<Bike> bikes = new ArrayList<>();
        Bike b = new Bike(1, 2,1, 2, 1);
        bikes.add(b);

        p.updateBikePositions(map, bikes);

        int[][] expected = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 1, 0}};
        Assertions.assertThat(map).isEqualTo(expected);
    }

    @Test
    public void testUpdateBikePositions1_2_with_init_1_1() throws Exception {
        Player p = new Player();

        int[][] map = new int[3][3];
        List<Bike> bikes = new ArrayList<>();
        Bike b = new Bike(1, 1,1, 2, 1);
        bikes.add(b);

        p.updateBikePositions(map, bikes);

        int[][] expected = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 1, 0}};
        Assertions.assertThat(map).isEqualTo(expected);
    }

    @Test
    public void testUpdateBikePositionsWith2Bikes() throws Exception {
        Player p = new Player();

        int[][] map = new int[3][3];
        List<Bike> bikes = new ArrayList<>();
        Bike b1 = new Bike(1, 1,1, 2, 1);
        Bike b2 = new Bike(2, 0,0, 1, 0);
        bikes.add(b1);
        bikes.add(b2);

        p.updateBikePositions(map, bikes);

        int[][] expected = new int[][]{{2, 2, 0}, {0, 1, 0}, {0, 1, 0}};
        Assertions.assertThat(map).isEqualTo(expected);
    }

}
