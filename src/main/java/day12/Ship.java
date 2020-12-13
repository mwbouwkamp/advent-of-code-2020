package day12;

public class Ship {
    int orientation;
    int x;
    int y;

    public Ship() {
        this.orientation = 90;
        int x = 0;
        int y = 0;
    }

    public void rotateLeft(int degrees) {
        orientation = (360 + orientation - degrees) % 360;
    }

    public void rotateRight(int degrees) {
        orientation = (orientation + degrees) % 360;
    }

    public void move(String direction, int stepSize) {
        if (direction.equals("F")) {
            switch (orientation) {
                case 0:
                    direction = "N";
                    break;
                case 90:
                    direction = "E";
                    break;
                case 180:
                    direction = "S";
                    break;
                case 270:
                    direction = "W";
                    break;
                default:
                    throw new IllegalArgumentException("orientation not allowed: " + direction);
            }
        }
        switch (direction) {
            case "N":
                y += stepSize;
                break;
            case "E":
                x += stepSize;
                break;
            case "S":
                y -= stepSize;
                break;
            case "W":
                x -= stepSize;
                break;
            default:
                throw new IllegalArgumentException("Direction not allowed: " + direction);
        }
    }

    public int getDistance() {
        return Math.abs(x) + Math.abs(y);
    }
}
