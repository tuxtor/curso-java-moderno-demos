package com.vorozco.modernjava;

public class JEP440 {

    public static void main(String[] args) {
        Point point = new Point(3, 4);
        Line line = new Line(new Point(0, 0), new Point(3, 4));

        System.out.println(oldPointDescribe(point));
        System.out.println(newPointDescribe(point));

        System.out.println(oldLineDescribe(line));
        System.out.println(newLineDescribe(line));
    }

    /**
     * Before JEP 440, extracting a record's components after an instanceof
     * check meant calling its accessors manually, one by one.
     */
    private static String oldPointDescribe(Object obj) {
        if (obj instanceof Point p) {
            int x = p.x();
            int y = p.y();
            return "(old) Point(" + x + ", " + y + ")";
        }
        return "(old) not a Point";
    }

    /**
     * JEP 440 lets the instanceof pattern deconstruct the record directly,
     * binding its components to variables in one step - no accessor calls
     * needed.
     */
    private static String newPointDescribe(Object obj) {
        if (obj instanceof Point(int x, int y)) {
            return "(new) Point(" + x + ", " + y + ")";
        }
        return "(new) not a Point";
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

    /**
     * Record patterns nest, so a Line made of two Points can be fully
     * deconstructed - start and end, then each Point's x/y - in a single
     * switch pattern.
     */
    private static String newLineDescribe(Object obj) {
        return switch (obj) {
            case Line(Point(var x1, var y1), Point(var x2, var y2)) ->
                    "(new) Line from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")";
            default -> "(new) not a Line";
        };
    }

    private record Point(int x, int y) {
    }

    private record Line(Point start, Point end) {
    }
}
