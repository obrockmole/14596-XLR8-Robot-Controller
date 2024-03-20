package org.firstinspires.ftc.teamcode.Systems.Geometry;

public class Vector2d {
    public double x;
    public double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    public Vector2d multiply(Vector2d other) {
        return new Vector2d(x * other.x, y * other.y);
    }

    public Vector2d divide(Vector2d other) {
        return new Vector2d(x / other.x, y / other.y);
    }

    public Vector2d scale(double scalar) {
        return new Vector2d(x * scalar, y * scalar);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2d normalize() {
        return scale(1 / magnitude());
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    public Vector2d rotate(double angle) {
        return new Vector2d(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle));
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
