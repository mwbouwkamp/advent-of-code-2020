package day20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private Tile[][] board;
    private int nextX = 0;
    private int nextY = 0;
    private List<Tile> remainingTiles;

    public Board(List<Tile> tiles) {
        int dimension = (int) Math.round(Math.sqrt(tiles.size() / 6));
        this.board = new Tile[dimension][dimension];
        this.remainingTiles = tiles;
    }

    public Board(Board board, Tile newTile) {
        int dimension = board.getBoard().length;
        this.board = new Tile[dimension][dimension];
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                this.board[y][x] = board.getBoard()[y][x];
            }
        }
        this.nextX = board.getNextX();
        this.nextY = board.getNextY();
        this.board[nextY][nextX] = newTile;
        if (nextX == dimension - 1) {
            this.nextX = 0;
            this.nextY++;
        } else {
            this.nextX++;
        }
        this.remainingTiles = new ArrayList<>(board.remainingTiles);
        this.remainingTiles.remove(newTile);
    }

    public int countTiles() {
        int count = 0;
        for (Tile[] row : board) {
            for (int x = 0; x < board.length; x++) {
                if (row[x] == null) {
                    return count;
                } else {
                    count++;
                }
            }
        }
        return count;
    }
    public List<Board> getChildren() {
        return remainingTiles.stream()
                .filter(t -> t.isValidTile(getConstraints()))
                .filter(t -> !containsTile(t))
                .map(t -> new Board(this, t))
                .collect(Collectors.toList());
    }

    public boolean containsTile(Tile tile) {
        for (Tile[] row : board) {
            for (int x = 0; x < board.length; x++) {
                if (row[x] != null && tile.getIndex() == row[x].getIndex()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fullBoard() {
        for (Tile[] row : board) {
            for (int x = 0; x < board.length; x++) {
                if (row[x] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public long getResult() {
        return (long) board[0][0].getIndex()
                * (long) board[0][board.length - 1].getIndex()
                * (long) board[board.length - 1][0].getIndex()
                * (long) board[board.length - 1][board.length - 1].getIndex();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Tile[] row : board) {
            builder.append("\n");
            for (int x = 0; x < board.length; x++) {
                if (row[x] != null) {
                    builder
                            .append(row[x].getIndex())
                            .append(" ");
                } else {
                    builder.append(".... ");
                }
            }
        }
        return builder.toString();
    }

    public Map<Integer, String> getConstraints() {
        Map<Integer, String> constraints = new HashMap<>();
        if (nextX > 0) {
            String edge = board[nextY][nextX - 1].getEdge(1);
            constraints.put(3, edge);
        }
        if (nextY > 0) {
            String edge = board[nextY - 1][nextX].getEdge(2);
            constraints.put(0, edge);
        }
        return constraints;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int getNextX() {
        return nextX;
    }

    public int getNextY() {
        return nextY;
    }

}
