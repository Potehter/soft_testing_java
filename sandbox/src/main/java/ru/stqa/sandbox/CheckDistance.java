package ru.stqa.sandbox;

public class CheckDistance {
    public static void main(String[] args) {
        Point p1 = new Point(1,1);
        Point p2 = new Point(2, 2);
        System.out.println("First point : " + p1.x + ", " + p1.y);
        System.out.println("Second point : " + p2.x + ", " + p2.y);
        System.out.println("Distance: " + distance(p1, p2));
    }

    public static double distance (Point p1, Point p2) {
        //расстояние между точками в пространстве считаем по гипотенузе
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) +
                Math.pow((p1.y - p2.y), 2));
    }
}
