package ru.stqa.sandbox;

import org.junit.Assert;
import org.junit.Test;

public class PointTests {

    @Test
    public void firstTest() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        Assert.assertEquals(0, p1.distance(p2), 0.1);
    }

    @Test
    public void secondTest() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Assert.assertEquals(1.5, p1.distance(p2), 0.1);
    }

    @Test
    public void thirdTest() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(1, 1);
        Assert.assertEquals(1.5, p1.distance(p2), 0.1);
    }
}
