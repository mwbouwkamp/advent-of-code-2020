package day12;

public class Ship {
    int orientation;
    long x;
    long y;
    long xWaypoint;
    long yWaypoint;
    boolean hasWaypoint;

    public Ship() {
        this.orientation = 90;
        int x = 0;
        int y = 0;
    }

    public Ship(long xWaypoint, long yWaypoint) {
        this.xWaypoint = xWaypoint;
        this.yWaypoint = yWaypoint;
        hasWaypoint = true;
    }

    public void rotateLeft(int degrees) {
        orientation = (360 + orientation - degrees) % 360;
    }

    public void rotateRight(int degrees) {
        orientation = (orientation + degrees) % 360;
    }

    public void rotateWayPointLeft(int degrees) {
        for (int i = 0; i < degrees / 90; i++) {
            long xWaypointOld = xWaypoint;
            xWaypoint = yWaypoint;
            yWaypoint = -xWaypointOld;
        }
    }

    public void rotateWayPointRight(int degrees) {
        for (int i = 0; i < degrees / 90; i++) {
            long xWaypointOld = xWaypoint;
            xWaypoint = -yWaypoint;
            yWaypoint = xWaypointOld;
        }
    }

    public void moveWithWayPoint(String direction, int stepSize) {
        if (direction.equals("F")) {
            x += stepSize * (xWaypoint);
            y += stepSize * (yWaypoint);
        } else {
            moveWaypointNESW(direction, stepSize);
        }
    }

    public void moveWithoutWayPoint(String direction, int stepSize) {
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
        moveNESW(direction, stepSize);
    }

    private void moveNESW(String direction, int stepSize) {
        switch (direction) {
            case "N":
                y -= stepSize;
                break;
            case "E":
                x += stepSize;
                break;
            case "S":
                y += stepSize;
                break;
            case "W":
                x -= stepSize;
                break;
            default:
                throw new IllegalArgumentException("Direction not allowed: " + direction);
        }
    }

    private void moveWaypointNESW(String direction, int stepSize) {
        switch (direction) {
            case "N":
                yWaypoint -= stepSize;
                break;
            case "E":
                xWaypoint += stepSize;
                break;
            case "S":
                yWaypoint += stepSize;
                break;
            case "W":
                xWaypoint -= stepSize;
                break;
            default:
                throw new IllegalArgumentException("Direction not allowed: " + direction);
        }
    }

    public long getDistance() {
        return Math.abs(x) + Math.abs(y);
    }
}
