import java.util.Random;

public class Board {
    GameController controller;
    Tile[][] boardTiles;
    int rowLength;
    int colLength;
    int mineCount;

    public Board(int rowLength, int colLength, int mineCount) {
        controller = new GameController();
        this.boardTiles = new Tile[rowLength][colLength];
        this.rowLength = rowLength;
        this.colLength = colLength;
        this.mineCount = mineCount;

        fillBoard();
        mineBoard();
        assignAdjacentTiles();
    }

    private void fillBoard() {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                boardTiles[i][j] = new SafeTile();
            }
        }
    }

    private void assignAdjacentTiles() {
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                for (int k = 0; k < 8; k++) {
                    int newRow = i + rowOffsets[k];
                    int newCol = j + colOffsets[k];

                    if (isInBounds(newRow, newCol)) {
                        boardTiles[i][j].addAdjacentTile(boardTiles[newRow][newCol]);
                    }
                }
            }
        }
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < rowLength && col >= 0 && col < colLength;
    }

    public void printBoard() {
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (boardTiles[i][j].mined == false) {
                    System.out.print("0");
                } else {
                    System.out.print("1");
                }

            }
            System.out.println("");
        }
    }

    public void printAdjacent() {
        System.out.println(boardTiles[4][4].getAdjacentTiles());
    }

    public void mineBoard() {
        Random rand = new Random();

        int randomRow = rand.nextInt(rowLength);
        int randomCol = rand.nextInt(colLength);

        int count = 0;

        while (count < mineCount) {
            if (boardTiles[randomRow][randomCol].mined == false) {
                boardTiles[randomRow][randomCol] = new MineTile();
                boardTiles[randomRow][randomCol].mined = true;
                count++;
            } else {
                randomRow = rand.nextInt(rowLength);
                randomCol = rand.nextInt(colLength);
            }
        }
    }

}
