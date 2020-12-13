package aivd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Opgave9 {
    private static class Block {
        private final String[][] block;
        private final int width;
        private final int height;

        public Block(String input) {
            String[] lines = input.split(",");
            this.width = lines[0].length();
            this.height = lines.length;
            this.block = new String[height][width];
            for (int y = 0; y < height; y++) {
                block[y] = lines[y].split("");
            }
        }

        public String get(int x, int y) {
            return block[y][x];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("\n");
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    builder.append(block[y][x]);
                }
                builder.append("\n");
            }
            return builder.toString();
        }
    }

    private static class Board {
        private final String[][] board;
        private int numFilled = 0;
        private final List<Integer> blocksUsed;
        private int linesFilled = 0;
        private final int width = 10;
        private final int height = 20;

        public Board() {
            this.board = new String[height][width];
            this.blocksUsed = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                board[y] = "..........".split("");
            }
        }

        public Board(Board board, List<Integer> blocksUsed) {
            this.board = new String[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    this.board[y][x] = board.get(x, y);
                }
            }
            this.blocksUsed = blocksUsed;
        }

        public String get(int x, int y) {
            return board[y][x];
        }

        public boolean isFull() {
            return numFilled == height * width;
        }

        public boolean isUsed(int key) {
            return blocksUsed.contains(key);
        }

        public Board getChild(int key, Block block) {
            List<Integer> newBlocksUsed = new ArrayList<>(blocksUsed);
            newBlocksUsed.add(key);
            Board childBoard = new Board(this, newBlocksUsed);
            int xAdd = -1;
            int yAdd = -1;
            FOUND:
            for (int y = linesFilled; y < height - block.height; y++) {
                for (int x = 0; x < width - block.width; x++) {
                    int free = 0;
                    for (int w = 0; w < block.width; w++) {
                        for (int h = 0; h < block.height; h++) {
                            if (board[y+h][x+h].equals(".")) {
                                free++;
                            }
                            if (block.get(w, h).equals(".")) {
                                free++;
                            }
                        }
                    }
                    if (free == block.width * block.height) {
                        xAdd = x;
                        yAdd = y;
                        break FOUND;
                    }
                }
            }
            if (xAdd != -1 && yAdd != -1) {
                childBoard.add(xAdd, yAdd, block);
                //set linesfilled!!!
                return childBoard;
            }
            return null;
        }

        public void add(int x, int y, Block block) {
            for (int h = 0; h < block.height; h++) {
                for (int w = 0; w < block.width; w++) {
                    board[y + h][x + w] = block.get(h, w);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("\n");
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    builder.append(board[y][x]);
                }
                builder.append("\n");
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        Map<Integer,Block> blocks = new HashMap<>();
        int i = 0;
        blocks.put(i++, new Block(".EB,AA."));
        blocks.put(i++, new Block(".E,EW,K."));
        blocks.put(i++, new Block("NEZ,..V"));
        blocks.put(i++, new Block(".E,BN,.O"));
        blocks.put(i++, new Block(".R.,TIZ"));
        blocks.put(i++, new Block(".S,FE,.N"));
        blocks.put(i++, new Block("C..,INC"));
        blocks.put(i++, new Block("R.,N.,IZ"));
        blocks.put(i++, new Block("OOG,..A"));
        blocks.put(i++, new Block("E.,EV,N."));
        blocks.put(i++, new Block("S.,EN,.J"));
        blocks.put(i++, new Block("M..,TIU"));
        blocks.put(i++, new Block(".IZ,EZ."));
        blocks.put(i++, new Block("K..,KOO"));
        blocks.put(i++, new Block(".O,NO,E."));
        blocks.put(i++, new Block("WELK"));
        blocks.put(i++, new Block("MH,EN"));
        blocks.put(i++, new Block(".E.,TIH"));
        blocks.put(i++, new Block("A,E,E,J"));
        blocks.put(i++, new Block("N.,IR,.E"));
        blocks.put(i++, new Block(".E,NN,.G"));
        blocks.put(i++, new Block("AR,LT"));
        blocks.put(i++, new Block("HT.,.MO"));
        blocks.put(i++, new Block("N..,EN?"));
        blocks.put(i++, new Block("AP,PS"));
        blocks.put(i++, new Block("KO,EN"));
        blocks.put(i++, new Block("JUEE"));
        blocks.put(i++, new Block("EE.,.LV"));
        blocks.put(i++, new Block(".NP,MS."));
        blocks.put(i++, new Block("KO,ET"));
        blocks.put(i++, new Block("N.,ZI,R."));
        blocks.put(i++, new Block("PGN,..E"));
        blocks.put(i++, new Block("NR,.A,.S"));
        blocks.put(i++, new Block(".Z,AA,.E"));
        blocks.put(i++, new Block("EN.,.EN"));
        blocks.put(i++, new Block("EN.,.OO"));
        blocks.put(i++, new Block("..T,LNI"));
        blocks.put(i++, new Block("LESV"));
        blocks.put(i++, new Block("E,E,N,D"));
        blocks.put(i++, new Block("NR.,.TJ"));
        blocks.put(i++, new Block("KL,.E,.G"));
        blocks.put(i++, new Block("EKK,.M."));

        Board board = new Board();
        List<Board> fringe = new ArrayList<>();
        fringe.add(board);
        while (fringe.size() > 0 ) {
            Board toCheck = fringe.remove(fringe.size() - 1);
            if (toCheck.isFull()) {
                System.out.println(toCheck);
            } else {
                for (int j = 0; j < blocks.size(); j++) {
                    if (!toCheck.isUsed(j)) {
                        Board childBoard = toCheck.getChild(j, blocks.get(j));
                        System.out.println(childBoard);
                        if (null != childBoard) {
                            fringe.add(childBoard);
                        }
                    }
                }
            }

        }



    }
}
