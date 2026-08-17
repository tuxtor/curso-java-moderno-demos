package com.vorozco.modernjava;

public class JEP440 {

    public static void main(String[] args) {
        Point point = new Point(3, 4);
        Line line = new Line(new Point(0, 0), new Point(3, 4));

        System.out.println(oldPointDescribe(point));
        System.out.println(oldLineDescribe(line));
    }

    private static String oldPointDescribe(Object obj) {
        if (obj instanceof Point p) {
            int x = p.x();
            int y = p.y();
            return "(old) Point(" + x + ", " + y + ")";
        }
        return "(old) not a Point";
    }

    private static String oldLineDescribe(Object obj) {
        if (obj instanceof Line line) {
            Point start = line.start();
            Point end = line.end();
            int x1 = start.x();
            int y1 = start.y();
            int x2 = end.x();
            int y2 = end.y();
            return "(old) Line from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")";
        }
        return "(old) not a Line";
    }

    private record Point(int x, int y) {
    }

    private record Line(Point start, Point end) {
    }
}
